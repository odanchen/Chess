package chessRoot.user_interface.game_flow;

import chessRoot.user_interface.frames.game_frame.GameFrame;
import chessRoot.logic.Board;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.logic.moves.Move;

import java.util.List;

public class GameControl {
    private Board board;
    private List<Move> gameLog;
    private GameStates gameStatus;
    private GameFrame gameFrame;

    public GameControl(Board board) {
        this.board = board;
    }

    public GameStates getGameStatus()
    {
        return this.gameStatus;
    }

    private void performMove(Move move)
    {
        board.makeMove(move);
    }

    /**
     * Default way to run the game. Is usable at any starting position.
     * @param movingSide The side moving first.
     * @return Enum for different outcomes of game.
     */
    public GameResult runTheGame(PieceColor movingSide) {
        gameStatus = movingSide == PieceColor.WHITE ? GameStates.PLAYER_WHITE_TURN : GameStates.PLAYER_BLACK_TURN;
        gameFrame = new GameFrame(board, this);


        return GameResult.PLAYER_WHITE_WON_BY_CHECKMATE;
    }
}
