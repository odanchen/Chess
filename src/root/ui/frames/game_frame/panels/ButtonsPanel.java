package root.ui.frames.game_frame.panels;

import root.ui.frames.components.CustomButton;
import root.ui.game_flow.GameResult;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;

import java.awt.*;

import static root.ui.game_flow.GameResult.PLAYER_BLACK_WON_BY_RESIGNATION;
import static root.ui.game_flow.GameResult.PLAYER_WHITE_WON_BY_RESIGNATION;

public class ButtonsPanel extends JPanel {
    private final GamePanel gamePanel;
    private final GraphicsManager graphicsManager;
    public ButtonsPanel(GamePanel gamePanel, GraphicsManager graphicsManager) {
       this.gamePanel = gamePanel;
       this.graphicsManager = graphicsManager;
       this.setBounds((int) (graphicsManager.getGamePanelBounds().x + graphicsManager.getGamePanelBounds().getWidth()),graphicsManager.getGamePanelBounds().y,graphicsManager.getButtonDimensions().width,700);
       this.setOpaque(false);
       addButtons();
       setLayout(new GridLayout(3,1,0,(int) (graphicsManager.getSquareSize()*2.8)));
    }
    private void addButtons() {
        CustomButton topButton = new CustomButton("resignButtonReleased", graphicsManager, graphicsManager.getButtonDimensions());
        CustomButton botButton = new CustomButton("resignButtonReleased", graphicsManager, graphicsManager.getButtonDimensions());
        JButton flipButton = new CustomButton("flipButtonReleased", graphicsManager, graphicsManager.getButtonDimensions());
        flipButton.addActionListener(e -> gamePanel.flipPanel());

        add(topButton);
        add(flipButton);
        add(botButton);
        addResignButtonActions(topButton, botButton);
        validate();
    }

    private void addResignButtonActions(CustomButton topButton, CustomButton botButton) {
        topButton.addActionListener(e -> topButtonPressed());
        botButton.addActionListener(e -> botButtonPressed());
    }

    private void topButtonPressed() {
        GameResult result = (graphicsManager.isFlipped()) ? PLAYER_BLACK_WON_BY_RESIGNATION : PLAYER_WHITE_WON_BY_RESIGNATION;
        gamePanel.endGame(result);
    }

    private void botButtonPressed() {
        GameResult result = (graphicsManager.isFlipped()) ? PLAYER_WHITE_WON_BY_RESIGNATION : PLAYER_BLACK_WON_BY_RESIGNATION;
        gamePanel.endGame(result);
    }
}
