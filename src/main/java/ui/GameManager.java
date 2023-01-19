package ui;

import logic.Board;
import ui.frames.end_frame.EndFrame;
import ui.frames.game_frame.GameFrame;
import ui.frames.menu_frame.MenuFrame;
import ui.game_flow.GameStatus;
import ui.graphics.GraphicsManager;

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
