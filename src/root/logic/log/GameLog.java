package root.logic.log;

import root.logic.Board;
import root.logic.moves.Move;
import root.logic.pieces.properties.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class GameLog {
    private List<Move> gameLog;
    private List<String> gameLogString;
    private final Board board;

    public GameLog(Board board) {
        this.board = board;
        gameLog = new ArrayList<>();
        gameLogString = new ArrayList<>();
    }

    public List<String> getString() {
        return gameLogString;
    }

    public void addMove(Move move) {
        gameLog.add(move);
        gameLogString.add(handleMating(move.toString(board)));
    }

    private String handleMating(String moveString) {
        if (board.isCheckmate(PieceColor.BLACK) || board.isCheckmate(PieceColor.WHITE))
            moveString += "#";
        else if (board.isCheck(PieceColor.BLACK) || board.isCheck(PieceColor.WHITE))
            moveString += "+";
        return moveString;
    }

    public List<Move> getGameLog() {
        return gameLog;
    }
}
