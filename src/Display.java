import board_package.Board;
import game_frame.GameFrame;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Display {
    private int boardSideSize = 512;
    private final Board board;
    ChessPiece selected = null;
    private final GameFrame gameFrame;

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
                listenToTurns();
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

    private int squareSize() {
        return this.boardSideSize / 8;
    }

    private Position getPositionOnTheBoard(int x, int y) {
        int row = y / squareSize();
        int col = x / squareSize();

        return new Position((char) (col + 'a'), (8 - row));
    }

    public void listenToTurns() throws IOException {
        this.gameFrame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selected == null) {
                    if (board.getPieceAt(getPositionOnTheBoard(e.getX(), e.getY())) != null) {
                        selected = board.getPieceAt(getPositionOnTheBoard(e.getX(), e.getY()));
                        System.out.println("selected");
                    }
                } else {
                    for (var move : selected.calculateMoves(board)) {
                        if (getPositionOnTheBoard(e.getX(), e.getY()).equals(move.getEndPosition())) {
                            System.out.println("moved");
                            board.makeMove(move);
                            gameFrame.updatePieces();
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
        gameFrame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("you clicked annd dragged");
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //GameControl game = new GameControl();
            }
        });

    }

    public Display(Board board) {
        this.board = board;
        this.gameFrame = new GameFrame(50, 100, boardSideSize, board);

        this.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gameFrame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Board board = new Board();
        board.fillStandardBoard();
        Display display = new Display(board);

        display.listenToTurns();

    }
}