package chessRoot.user_interface.game_flow;

import chessRoot.user_interface.GameManager;
import chessRoot.user_interface.frames.game_frame.GameMainFrame;
import chessRoot.logic.Board;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.logic.moves.Move;

import static chessRoot.logic.pieces.PieceColor.WHITE;
import static chessRoot.user_interface.game_flow.GameStates.BLACK_TURN;
import static chessRoot.user_interface.game_flow.GameStates.WHITE_TURN;

public class GameControl {
    private final GameMainFrame mainFrame;
    private final GameStatus gameStatus;
    private final GameManager gameManager;

    public GameControl(Board board, PieceColor movingSide, GameManager gameManager) {
        GameStates state = movingSide == WHITE ? WHITE_TURN : BLACK_TURN;
        this.gameStatus = new GameStatus(board, state);
        this.gameManager = gameManager;
        this.mainFrame = new GameMainFrame(gameStatus, this, gameManager.getGraphicsManager());
    }

    public void performMove(Move move) {
        mainFrame.getGameFrame().makeMove(move);
    }
}
