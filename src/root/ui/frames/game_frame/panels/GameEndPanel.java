package root.ui.frames.game_frame.panels;

import root.ui.frames.menu_frame.CustomButton;
import root.ui.graphics.GraphicsManager;
import root.ui.game_flow.GameResult;

import javax.swing.*;
import java.awt.*;

public class GameEndPanel extends JPanel {

    private final GraphicsManager graphicsManager;
    private GameResult gameResult;

    public GameEndPanel(GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
        this.setBounds(graphicsManager.getEndPanelBounds());
        this.setOpaque(true);
        this.setVisible(false);
        addMenuButton();
    }

    @Override
    public void paint(Graphics g) {
        if (gameResult != null) {
            int arcSize = graphicsManager.getSquareSize() / 2;
            g.setColor(Color.WHITE);
            g.fillRoundRect(0, 0, getBounds().width, getBounds().height, arcSize, arcSize);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString("Game Over", getBounds().x + (int) (getBounds().width / 3.5), getBounds().y + getBounds().height / 4);
            String result = getMessageFromResult();
            g.drawString(result, getBounds().x + (int) (getBounds().width / 3.5), getBounds().y + getBounds().height / 2);
        }
    }

    private String getMessageFromResult() {
        switch (gameResult) {
            case PLAYER_WHITE_WON_BY_CHECKMATE:
                return "Player white won by a checkmate!";
            case PLAYER_BLACK_WON_BY_CHECKMATE:
                return "Player black won by a checkmate!";
            default:
                return "Draw!";
        }
    }

    private void addMenuButton() {
        JPanel buttonsPanel = new JPanel();

        Dimension size = graphicsManager.getTextButtonDimension();
        buttonsPanel.setPreferredSize(size);
        buttonsPanel.setBounds(0, 0, size.width, size.height);

        buttonsPanel.setVisible(true);
        //buttonsPanel.setOpaque(false);

        CustomButton menuReturnButton = new CustomButton("menuButtonReleased", graphicsManager, size);
        menuReturnButton.setPreferredSize(size);
        buttonsPanel.add(menuReturnButton);

        buttonsPanel.validate();
        add(buttonsPanel);
        validate();
    }

    public void showPanel(GameResult gameResult) {
        this.gameResult = gameResult;
        setVisible(true);
    }
}
