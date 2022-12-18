<<<<<<< HEAD
import javax.swing.*;
import java.awt.*;

public class Display {
    public static void main(String[] args) {
        JButton start = new JButton("   Start   ");
        JButton settings = new JButton("Settings");
        JButton quit = new JButton ("   Quit    ");

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.cyan);
        buttons.setBounds(150,100,130,150);

        JFrame frame = new JFrame("Chess");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(3);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(buttons);

=======


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
>>>>>>> origin/master
        buttons.add(start);
        buttons.add(settings);
        buttons.add(quit);

<<<<<<< HEAD
=======
        JFrame f = new JFrame("Chess");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(3);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
        f.add(buttons);
        f.add(title);


    }

    public static void main(String[] args) {
        MainMenu();
>>>>>>> origin/master
    }
}
