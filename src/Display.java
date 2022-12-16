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

        buttons.add(start);
        buttons.add(settings);
        buttons.add(quit);

    }
}
