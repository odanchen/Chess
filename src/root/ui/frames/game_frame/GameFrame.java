package root.ui.frames.game_frame;

import root.assets.settings.IOSettings;
import root.logic.Board;
import root.logic.pieces.properties.PieceColor;
import root.logic.utils.TimerPair;
import root.ui.GameManager;
import root.ui.frames.components.BaseFrame;
import root.ui.frames.game_frame.panels.ButtonsPanel;
import root.ui.frames.game_frame.panels.GamePanel;
import root.ui.frames.game_frame.panels.LogPanel;
import root.ui.frames.game_frame.panels.TimerPanel;
import root.ui.game_flow.GameStatus;
import root.ui.graphics.GraphicsManager;

public class GameFrame extends BaseFrame {
    private final GamePanel gamePanel;
    private final LogPanel logPanel;
    private TimerPanel timerPanel;
    private final ButtonsPanel buttonsPanel;
    private final GameStatus gameStatus;


    public GameFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        super(gameManager, graphicsManager);
        if (new IOSettings().getGameLength().equals("Infinite"))
            gameStatus = new GameStatus(createBoard(), PieceColor.WHITE);
        else gameStatus = new GameStatus(createBoard(), PieceColor.WHITE, new TimerPair(getGameLength()));
        this.gamePanel = new GamePanel(gameStatus, graphicsManager, this);
        this.logPanel = new LogPanel(gameStatus, graphicsManager);
        this.buttonsPanel = new ButtonsPanel(gamePanel, graphicsManager);
        if (gameStatus.timerPresent()) this.timerPanel = new TimerPanel(graphicsManager, gameStatus, gamePanel);
        addPanels();
        addBackgroundPanel("gameBackground");
    }

    private int getGameLength() {
        switch (new IOSettings().getGameLength()) {
            case "Long":
                return 10;
            case "Medium":
                return 5;
            default:
                return 3;
        }
    }

    private void addPanels() {
        if (gameStatus.timerPresent()) add(timerPanel);
        add(logPanel);
        add(gamePanel);
        add(buttonsPanel);
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
}
