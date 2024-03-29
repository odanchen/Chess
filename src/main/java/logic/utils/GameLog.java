package logic.utils;

import logic.Board;
import logic.moves.Move;
import logic.pieces.properties.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class GameLog {
    private final List<String> gameLogString;
    private final Board board;

    public GameLog(Board board) {
        this.board = board;
        gameLogString = new ArrayList<>();
    }

    public String getFullString() {
        StringBuilder fullString = new StringBuilder();
        for (int i = 0; i < gameLogString.size(); i++) {
            if (i % 2 == 0) {
                if (i != 0) fullString.append("\n");
                fullString.append(i / 2 + 1).append(". ");
            }
            fullString.append(gameLogString.get(i));
            if (i % 2 == 0) fullString.append(", ");
        }
        return fullString.toString();
    }

    public void addMove(Move move) {
        gameLogString.add(handleMating(move.toString(board)));
    }

    private String handleMating(String moveString) {
        if (board.isCheckmate(PieceColor.BLACK) || board.isCheckmate(PieceColor.WHITE)) moveString += "#";
        else if (board.isCheck(PieceColor.BLACK) || board.isCheck(PieceColor.WHITE)) moveString += "+";
        return moveString;
    }
}
