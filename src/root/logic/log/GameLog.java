package root.logic.log;

import root.logic.Board;
import root.logic.moves.Move;
import root.logic.pieces.properties.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class GameLog {
    private final List<Move> gameLog;
    private final List<String> gameLogString;
    private final Board board;

    public GameLog(Board board) {
        this.board = board;
        gameLog = new ArrayList<>();
        gameLogString = new ArrayList<>();
    }

    public String getFullString() {
        StringBuilder fullString = new StringBuilder();
        for (int i = 0; i < gameLogString.size(); i++) {
            if (i % 2 == 0) {
                fullString.append(i / 2 + 1).append(". ");
            }
            fullString.append(gameLogString.get(i)).append(" ");
        }
        return fullString.toString();
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

}
