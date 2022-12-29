package chessRoot.user_interface;

import chessRoot.logic.Board;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.user_interface.game_flow.GameControl;

public class GameManager {
    private GameControl gameControl;
    private final GraphicsManager graphicsManager;

    public GameManager() {
        graphicsManager = new GraphicsManager();
    }

    public GraphicsManager getGraphicsManager() {
        return graphicsManager;
    }

    public void runTheGame(Board board, PieceColor firstMove) {
        gameControl = new GameControl(board, firstMove, this);
    }
}