package root.ui.frames.end_frame;

import root.ui.GameManager;
import root.ui.frames.BaseFrame;
import root.ui.frames.menu_frame.CustomButton;
import root.ui.game_flow.GameResult;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class EndFrame extends BaseFrame {

    private final GameResult gameResult;

    public EndFrame(GameManager gameManager, GraphicsManager graphicsManager, GameResult gameResult) {
        super(gameManager, graphicsManager);
        this.gameResult = gameResult;
        addMenuButton();
    }

    private void addMenuButton() {
        JPanel buttonsPanel = new JPanel();

        Dimension size = graphicsManager.getTextButtonDimension();
        buttonsPanel.setBounds(graphicsManager.getCenterOfScreenX(size.width), graphicsManager.getCenterOfScreenY(size.height), size.width, (int) (size.height * 1.5));
        buttonsPanel.setOpaque(false);

        CustomButton menuReturnButton = new CustomButton("menuButtonReleased", graphicsManager, size);
        menuReturnButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor((JComponent) e.getSource()).dispose();
            gameManager.runMenu();
        });

        buttonsPanel.add(menuReturnButton);
        add(buttonsPanel);
        validate();
    }
}
