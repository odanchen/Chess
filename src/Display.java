import board_package.Board;
import game_frame.GameFrame;
import pieces.*;
import pieces.moves.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Display {
    private int boardSideSize = 512;
    private final Board board;
    private final GameFrame gameFrame;

    /*
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
            gameRuner.runTheGame(new Board()/*I have no clue what to put here but when I find out I will put it here :)*//*, PieceColor.WHITE);
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
*/
    public void updatePieces() {
        gameFrame.updatePieces();
    }

    private int squareSize() {
        return this.boardSideSize / 8;
    }

    public Move pickTheMove(PieceColor movingSide) throws IOException {
        return gameFrame.listenUserMove(movingSide);
    }

    public Display(Board board) {
        this.board = board;
        this.gameFrame = new GameFrame(50, 100, boardSideSize, board);

        this.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gameFrame.setVisible(true);
    }
    public static void main(String[] args)  {
    }
}




