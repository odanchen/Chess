package root.ui.frames.game_frame;

import root.logic.Board;
import root.logic.log.GameLog;
import root.logic.pieces.properties.PieceColor;
import root.ui.GameManager;
import root.ui.frames.components.BaseFrame;
import root.ui.frames.game_frame.panels.GamePanel;
import root.ui.frames.game_frame.panels.SidePanel;
import root.ui.game_flow.GameResult;
import root.ui.graphics.GraphicsManager;
import root.ui.frames.components.CustomButton;
import root.ui.game_flow.GameStatus;

import javax.swing.*;

import static root.ui.game_flow.GameResult.PLAYER_BLACK_WON_BY_RESIGNATION;
import static root.ui.game_flow.GameResult.PLAYER_WHITE_WON_BY_RESIGNATION;

public class GameFrame extends BaseFrame {
    private final GraphicsManager graphicsManager;
    private final GamePanel gamePanel;
    private final SidePanel sidePanel;

    private void addResignButtons() {
        int xCor = (int) (graphicsManager.getGamePanelBounds().x + graphicsManager.getGamePanelBounds().getWidth());
        int yCor = graphicsManager.getGamePanelBounds().y;
        int size = graphicsManager.getFlipButtonBounds().width;
        CustomButton topButton = new CustomButton("resignButtonReleased", graphicsManager, graphicsManager.getFlipButtonBounds().getSize());
        CustomButton botButton = new CustomButton("resignButtonReleased", graphicsManager, graphicsManager.getFlipButtonBounds().getSize());
        topButton.setBounds(xCor, yCor, size, size);
        botButton.setBounds(xCor, yCor + graphicsManager.getPlayAreaBounds().height, size, size);
        add(topButton);
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

    private Board createBoard() {
        Board board = new Board();
        board.fillStandardBoard();
        return board;
    }

    public void swapToEndFrame() {
        gameManager.swapToEndFrame(gamePanel.getGameStatus());
        this.dispose();
    }

    public GameFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        super(gameManager, graphicsManager);
        this.graphicsManager = graphicsManager;
        GameStatus gameStatus = new GameStatus(createBoard(), PieceColor.WHITE);
        this.gamePanel = new GamePanel(gameStatus, graphicsManager, this);
        this.sidePanel = new SidePanel(gameStatus, graphicsManager, gamePanel);
        getContentPane().add(sidePanel);
        getContentPane().add(gamePanel);
        addFlipButton();
        addResignButtons();
        addBackgroundPanel("gameBackground");
    }
}
