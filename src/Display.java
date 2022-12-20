import assets.board_colors.BoardColors;
import assets.board_colors.ColorPair;
import board_package.Board;
import pieces.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;

public class Display {
    private final double SQUARE_TO_PIECE_RATIO = 0.8;
    private String chessPieceTextureFolder = "cburnett";
    private int boardSideSize = 800;
    private ColorPair boardColors = BoardColors.OPTION1;
    private PieceColor bottomSideColor = PieceColor.WHITE;
    private final Board board;

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

    private BufferedImage getTextureOfPiece(ChessPiece piece) throws IOException {

        String root = ("src\\assets\\pieces_textures\\" + this.chessPieceTextureFolder + "\\");

        String color = (piece.getPieceColor() == PieceColor.WHITE) ? "w" : "b";
        String pieceLetter = "";

        return ImageIO.read(new File(root + color + piece.getPieceLetter() + ".png"));
    }

    public void drawBoard() throws IOException {


        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 528, 550);
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
                        g.fillRect(x * 64, y * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }
                for (ChessPiece piece : board.getAllPieces())
                {
                    int row = Math.abs(piece.getPosition().getRow() - 8);
                    int col = (int) piece.getPosition().getCol() - 'a';

                    //x = col * squareSize;
                    //y = row * squareSize;
                    try {
                        BufferedImage image = getTextureOfPiece(piece);
                        g.drawImage(image, col *64 , row *64, null);
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
        Display display = new Display(board);
        display.drawBoard();
    }
}