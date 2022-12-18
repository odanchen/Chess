import board_package.Board;
import pieces.PieceColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display {

    public static void MainMenu() {

        JLabel Ltitle = new JLabel();
        Ltitle.setText("Chess Game");
        Ltitle.setFont(new Font("Serif", Font.PLAIN, 20));

        JPanel title = new JPanel();
        title.setBounds(220, 150, 150, 50);
        //title.setBackground(Color.green);
        title.add(Ltitle);

        Dimension BtnSize = new Dimension(130, 50);

        JButton start = new JButton("   Start   ");

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You started the game");
               GameControl gameRuner = new GameControl();
               gameRuner.runTheGame(new Board()/*i have no clue what to put here but when i find out i will put it here :)*/, PieceColor.WHITE);
            }
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
        f.setDefaultCloseOperation(3);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
        f.add(buttons);
        f.add(title);


    }

    public void drawBoard(){

    }

    public static void main(String[] args) {
        MainMenu();
    }
}
