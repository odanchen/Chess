package root.ui;

import root.logic.Board;
import root.ui.frames.BaseFrame;
import root.ui.frames.game_frame.GameFrame;
import root.ui.frames.menu_frame.MenuFrame;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;

public class GameManager {
    private BaseFrame currentFrame;
    private final GraphicsManager graphicsManager;

    public GameManager() {
        graphicsManager = new GraphicsManager();
    }

    public void runChess() {
        Board board = new Board();
        board.fillStandardBoard();
        currentFrame = new GameFrame(this, graphicsManager);
    }

    public void runMenu() {
        currentFrame = new MenuFrame(this, graphicsManager);
    }
}
