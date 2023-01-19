package ui.frames.end_frame.panels;

import ui.game_flow.GameResult;
import ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EndMessagePanel extends JPanel {
    private final GraphicsManager graphicsManager;
    private final GameResult gameResult;

    public EndMessagePanel(GraphicsManager graphicsManager, GameResult gameResult) {
        this.graphicsManager = graphicsManager;
        this.gameResult = gameResult;
        Dimension size = graphicsManager.getFrameDimension();
        this.setBounds((int) (size.width * 14 / 20.25), size.height / 4, graphicsManager.getEndDimension().width, graphicsManager.getEndDimension().height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getMessageImage(), 0, 0, null);
    }

    private BufferedImage getMessageImage() {
        String folderName = "";
        switch (gameResult) {
            case PLAYER_WHITE_WON_BY_CHECKMATE:
                folderName = "pwwcm";
                break;
            case PLAYER_BLACK_WON_BY_CHECKMATE:
                folderName = "pbwcm";
                break;
            case PLAYER_WHITE_WON_BY_RESIGNATION:
                folderName = "pwwrs";
                break;
            case PLAYER_BLACK_WON_BY_RESIGNATION:
                folderName = "pbwrs";
                break;
            case STALEMATE:
                folderName = "dsm";
                break;
            case PLAYER_BLACK_WON_BY_TIME:
                folderName = "pbwto";
                break;
            case PLAYER_WHITE_WON_BY_TIME:
                folderName = "pwwto";
                break;
        }
        return graphicsManager.getMessageTexture(folderName);
    }
}
