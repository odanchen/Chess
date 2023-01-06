//
//package chessRoot.user_interface.frames;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class Display {
//
//
//    public void MainMenu() {
//
//        JLabel lTitle = new JLabel();
//        lTitle.setText("Chess Game");
//        lTitle.setFont(new Font("Serif", Font.PLAIN, 20));
//
//        JPanel title = new JPanel();
//        title.setBounds(220, 150, 150, 50);
//        //title.setBackground(Color.green);
//        title.add(lTitle);
//
//        Dimension BtnSize = new Dimension(130, 50);
//
//        JButton start = new JButton("   Start   ");
//
//        start.addActionListener(e -> { // uncomment the call for draw board for the start button to work
//
//        });
//
//        start.setPreferredSize(BtnSize);
//        start.setBorderPainted(false);
//        start.setFocusPainted(false);
//        start.setContentAreaFilled(false);
//
//        JButton settings = new JButton("Settings");
//        settings.setPreferredSize(BtnSize);
//        settings.setBorderPainted(false);
//        settings.setFocusPainted(false);
//        settings.setContentAreaFilled(false);
//
//        JButton quit = new JButton("    Quit    ");
//        quit.setPreferredSize(BtnSize);
//        quit.setBorderPainted(false);
//        quit.setFocusPainted(false);
//        quit.setContentAreaFilled(false);
//
//        JPanel buttons = new JPanel();
//        buttons.setBounds(230, 250, 130, 170);
//        //buttons.setBackground(Color.cyan);
//
//        buttons.add(start);
//        buttons.add(settings);
//        buttons.add(quit);
//
//        JFrame f = new JFrame("Chess");
//        f.setSize(600, 600);
//
//        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        f.setLayout(null);
//        f.setVisible(true);
//        f.setResizable(false);
//        f.add(buttons);
//        f.add(title);
//
//
//    }
//
//}
//
//
//
//
