package chessRoot.user_interface.frames.menu_frame;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String text) {
        super(text);
        setPreferredSize(new Dimension(130, 50));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }


}
