package chessRoot.user_interface;

import chessRoot.logic.Board;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.user_interface.game_flow.GameControl;
import chessRoot.user_interface.game_flow.MenuControl;

public class GameManager {
    private GameControl gameControl;
    private  MenuControl menuControl;
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
