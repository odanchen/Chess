package chessRoot.user_interface.game_flow;

import chessRoot.user_interface.frames.game_frame.GameFrame;
import chessRoot.logic.Board;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.logic.moves.Move;

import static chessRoot.logic.pieces.PieceColor.WHITE;
import static chessRoot.user_interface.game_flow.GameStates.PLAYER_BLACK_TURN;
import static chessRoot.user_interface.game_flow.GameStates.PLAYER_WHITE_TURN;

public class GameControl {
    private GameFrame gameFrame;
    private final GameStatus gameStatus;

    public GameControl(Board board, PieceColor movingSide) {
        GameStates state = movingSide == WHITE ? PLAYER_WHITE_TURN : PLAYER_BLACK_TURN;
        gameStatus = new GameStatus(state, board);
    }

    public void performMove(Move move) {
        gameStatus.getBoard().makeMove(move);
    }

    /**
     * Default way to run the game. Is usable at any starting position.
     */
    public void runTheGame() {
        gameFrame = new GameFrame(gameStatus, this);
    }
}
