import assets.board_colors.BoardColors;
import assets.board_colors.ColorPair;
import board_package.Board;
import pieces.*;
import pieces.moves.Move;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

public class Display {
    private final double SQUARE_TO_PIECE_RATIO = 0.875;
    private String pieceTextureFolder = "cburnett";
    private int boardSideSize = 512;
    private ColorPair boardColors = BoardColors.OPTION1;
    private PieceColor bottomSideColor = PieceColor.WHITE;
    private final Board board;
    ChessPiece selected = null;

    public Display(Board board) {
        this.board = board;
    }

    public void MainMenu() {

        JLabel lTitle = new JLabel();
        lTitle.setText("Chess Game");
        lTitle.setFont(new Font("Serif", Font.PLAIN, 20));

        JPanel title = new JPanel();
        title.setBounds(220, 150, 150, 50);
        //title.setBackground(Color.green);
        title.add(lTitle);

        Dimension BtnSize = new Dimension(130, 50);

        JButton start = new JButton("   Start   ");

        start.addActionListener(e -> { // uncomment the call for draw board for the start button to work
            try {
                drawBoard();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("You started the game");
            GameControl gameRuner = new GameControl();
            gameRuner.runTheGame(new Board()/*I have no clue what to put here but when I find out I will put it here :)*/, PieceColor.WHITE);
        });

        start.setPreferredSize(BtnSize);
        start.setBorderPainted(false);
        start.setFocusPainted(false);
        start.setContentAreaFilled(false);

        JButton settings = new JButton("Settings");
        settings.setPreferredSize(BtnSize);
        settings.setBorderPainted(false);
        settings.setFocusPainted(false);
        settings.setContentAreaFilled(false);

        JButton quit = new JButton("    Quit    ");
        quit.setPreferredSize(BtnSize);
        quit.setBorderPainted(false);
        quit.setFocusPainted(false);
        quit.setContentAreaFilled(false);

        JPanel buttons = new JPanel();
        buttons.setBounds(230, 250, 130, 170);
        //buttons.setBackground(Color.cyan);

        buttons.add(start);
        buttons.add(settings);
        buttons.add(quit);

        JFrame f = new JFrame("Chess");
        f.setSize(600, 600);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
        f.add(buttons);
        f.add(title);


    }

    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }

    private String getImageName(ChessPiece piece) {
        String color = (piece.getPieceColor() == PieceColor.WHITE) ? "w" : "b";

        return (color + piece.getPieceLetter() + ".png");
    }

    private BufferedImage getTextureOfPiece(ChessPiece piece) throws IOException {
        String root = Paths.get("").toAbsolutePath().toString();
        String[] fullPath = {root, "src", "assets", "pieces_textures", this.pieceTextureFolder, getImageName(piece)};

        return ImageIO.read(new File(String.join(File.separator, fullPath)));
    }

    private int getSquareSize() {
        return this.boardSideSize / 8;
    }

    private int getPieceSize() {
        return (int) (getSquareSize() * this.SQUARE_TO_PIECE_RATIO);
    }

    private int getPieceCoordinate(int idx) {
        return (int) (getSquareSize() * (idx + (1 - this.SQUARE_TO_PIECE_RATIO)));
    }

    private Position getPositionOnTheBoard(int leftCornerX, int leftCornerY, int x, int y) {
        int row = (y - leftCornerY) / getSquareSize();
        int col = (x - leftCornerX) / getSquareSize();

        return new Position((char) (col + 'a'), (8 - row));
    }

    private boolean isClickInsideBoard(int leftCornerX, int leftCornerY, int x, int y) {
        return (x - leftCornerX >= 0 && x - leftCornerX <= boardSideSize) && (y - leftCornerY >= 0 && y - leftCornerY <= boardSideSize);
    }

    public void drawBoard() throws IOException {


        JFrame frame = new JFrame();
        frame.setBounds(10, 10, boardSideSize, boardSideSize);
        frame.setUndecorated(true);
        JPanel pn = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (white) {
                            g.setColor(boardColors.getWhiteCell());
                        } else {
                            g.setColor(boardColors.getBlackCell());
                        }
                        g.fillRect(x * getSquareSize(), y * getSquareSize(), getSquareSize(), getSquareSize());
                        white = !white;
                    }
                    white = !white;
                }
                for (ChessPiece piece : board.getAllPieces()) {
                    int row = Math.abs(piece.getPosition().getRow() - 8);
                    int col = (int) piece.getPosition().getCol() - 'a';

                    try {
                        BufferedImage image = getTextureOfPiece(piece);
                        image = toBufferedImage(image.getScaledInstance(getPieceSize(), getPieceSize(), Image.SCALE_SMOOTH));
                        g.drawImage(image, getPieceCoordinate(col), getPieceCoordinate(row), null);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        frame.add(pn);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selected == null) {
                    if (board.getPieceAt(getPositionOnTheBoard(0, 0, e.getX(), e.getY())) != null) {
                        selected = board.getPieceAt(getPositionOnTheBoard(0, 0, e.getX(), e.getY()));
                        System.out.println("selected");
                    }
                } else {
                    for (var move : selected.calculateMoves(board)) {
                        System.out.println(move.getEndPosition().getCol() + move.getEndPosition().getRow());
                    }
                    for (var move : selected.calculateMoves(board)) {
                        if (getPositionOnTheBoard(0, 0, e.getX(), e.getY()).equals(move.getEndPosition())) {
                            System.out.println("moved");
                            board.makeMove(move);
                            //drawBoard();
                            break;
                        }
                    }
                    selected = null;
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {


            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("you clicked annd dragged");
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                GameControl game = new GameControl();
            }
        });

    }

    public static void main(String[] args) throws IOException {
        Board board = new Board();
        board.fillStandardBoard();
        ChessPiece piece = board.getPieceAt(new Position('e', 2));
        List<Move> moves = piece.calculatePotentialMoves(board);


        System.out.print("i");
    }
}