package root.ui.frames;

import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public abstract class BaseFrame extends JFrame {

    @Override
    public Dimension getPreferredSize() {
        return graphicsManager.getGameBounds().getSize();
    }

    private void addIcon() {
        ImageIcon img = new ImageIcon(String.join(File.separator, graphicsManager.getPathToTexture("bk")));
        setIconImage(img.getImage());

        if (System.getProperty("os.name").contains("Mac")) {
            Taskbar.getTaskbar().setIconImage(img.getImage());
        }
    }

    protected GraphicsManager graphicsManager;
    public BaseFrame(GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
        addIcon();
        setTitle("Chess");
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        setBounds(graphicsManager.getGameBounds());
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
