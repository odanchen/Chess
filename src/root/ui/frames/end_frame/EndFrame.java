package root.ui.frames.end_frame;

import root.logic.pieces.properties.PieceColor;
import root.ui.GameManager;
import root.ui.frames.components.BaseFrame;
import root.ui.frames.components.CustomButton;
import root.ui.frames.end_frame.panels.BoardStatePanel;
import root.ui.game_flow.GameResult;
import root.ui.game_flow.GameStatus;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class EndFrame extends BaseFrame {

    private final GameStatus gameStatus;
    private final GameResult gameResult;

    public EndFrame(GameManager gameManager, GraphicsManager graphicsManager, GameStatus gameStatus) {
        super(gameManager, graphicsManager);
        this.gameStatus = gameStatus;
        this.gameResult = getGameResult();
        addMenuButton();
        addGmeEndBoard();
    }

    private GameResult getGameResult() {
        if (gameStatus.isCheckmate(PieceColor.WHITE)) {
            return GameResult.PLAYER_BLACK_WON_BY_CHECKMATE;
        } else if (gameStatus.isCheckmate(PieceColor.BLACK)) {
            return GameResult.PLAYER_WHITE_WON_BY_CHECKMATE;
        } else if (gameStatus.isStalemate()) {
            return GameResult.STALEMATE;
        }
        return null;
    }

    private void addGmeEndBoard() {
        BoardStatePanel boardStatePanel = new BoardStatePanel(graphicsManager, gameStatus);
        add(boardStatePanel);
        validate();
    }

    private void addMenuButton() {
        JPanel buttonsPanel = new JPanel();

        Dimension size = graphicsManager.getTextButtonDimension();
        int buttonY = (int) (graphicsManager.getGameBounds().getSize().getHeight() * 17 / 20);
        buttonsPanel.setBounds(graphicsManager.getCenterOfScreenX(size.width), buttonY, size.width, (int) (size.height * 1.5));
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
