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
    //private final GameEndPanel gameEndPanel;

    public EndFrame(GameManager gameManager, GraphicsManager graphicsManager, GameResult gameResult) {
        super(gameManager,graphicsManager);
        this.gameResult = gameResult;
        addMenuButton();
        //this.gameEndPanel = new GameEndPanel(graphicsManager);
    }
    private void addMenuButton() {
        JPanel buttonsPanel = new JPanel();

        Dimension size = graphicsManager.getTextButtonDimension();
        buttonsPanel.setPreferredSize(size);
        buttonsPanel.setBounds(0, 0, size.width, size.height + 10);

        buttonsPanel.setVisible(true);
        //buttonsPanel.setOpaque(false);

        CustomButton menuReturnButton = new CustomButton("menuButtonReleased", graphicsManager, size);
        menuReturnButton.setPreferredSize(size);
        buttonsPanel.add(menuReturnButton);

        buttonsPanel.validate();
        add(buttonsPanel);
        validate();
    }
}
