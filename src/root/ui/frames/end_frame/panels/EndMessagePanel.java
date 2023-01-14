package root.ui.frames.end_frame.panels;

import root.ui.game_flow.GameResult;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class EndMessagePanel extends JPanel {
    private final GraphicsManager graphicsManager;
    private final GameResult gameResult;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String folderName = "";
        switch (gameResult) {
            case PLAYER_WHITE_WON_BY_CHECKMATE: folderName = "pwwcm"; break;
            case PLAYER_BLACK_WON_BY_CHECKMATE: folderName = "pbwcm"; break;
            case STALEMATE: folderName = "dsm"; break;
        }
        g.drawImage(graphicsManager.getMessageTexture(folderName), 0, 0, null);
    }

    public EndMessagePanel(GraphicsManager graphicsManager, GameResult gameResult) {
        this.graphicsManager = graphicsManager;
        this.gameResult = gameResult;
        Dimension size = graphicsManager.getFrameDimension();
        this.setBounds((int)(size.width * 14 / 20.25), size.height / 4, graphicsManager.getEndDimension().width, graphicsManager.getEndDimension().height);
    }
}
