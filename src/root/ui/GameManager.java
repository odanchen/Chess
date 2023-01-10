package root.ui;

import root.logic.Board;
import root.logic.pieces.properties.PieceColor;
import root.ui.frames.BaseFrame;
import root.ui.game_flow.GameControl;
import root.ui.game_flow.MenuControl;
import root.ui.graphics.GraphicsManager;

public class GameManager {
    private GameControl gameControl;
    private MenuControl menuControl;
    private BaseFrame currentFrame;
    private final GraphicsManager graphicsManager;

    public GameManager() {
        graphicsManager = new GraphicsManager();
    }

    public GraphicsManager getGraphicsManager() {
        return graphicsManager;
    }

    public void runChess() {
        Board board = new Board();
        board.fillStandardBoard();
        gameControl = new GameControl(board, PieceColor.WHITE, this);
    }

    public void runChess(Board board, PieceColor firstMove) {
        gameControl = new GameControl(board, firstMove, this);
    }

    public void runMenu() {
        menuControl = new MenuControl(this);
    }
}
