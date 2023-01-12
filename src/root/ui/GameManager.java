package root.ui;

import root.logic.Board;
import root.ui.frames.end_frame.EndFrame;
import root.ui.frames.game_frame.GameFrame;
import root.ui.frames.menu_frame.MenuFrame;
import root.ui.game_flow.GameStatus;
import root.ui.graphics.GraphicsManager;

public class GameManager {
    private final GraphicsManager graphicsManager;

    public GameManager() {
        graphicsManager = new GraphicsManager();
    }

    public void runChess() {
        Board board = new Board();
        board.fillStandardBoard();
        new GameFrame(this, graphicsManager);
    }

    public void swapToEndFrame(GameStatus gameStatus) {
        new EndFrame(this, graphicsManager, gameStatus);
    }

    public void runMenu() {
        new MenuFrame(this, graphicsManager);
    }
}
