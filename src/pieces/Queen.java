package pieces;

import BoardPackage.Board;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    // Returns a line out of the 8 lines of possible movements for the queen.
    private List<Position> checkLine(Board board, int colDifference, int rowDifference) {
        List<Position> moves = new ArrayList<>();

        Position curPosition = new Position(this.getPosition(), colDifference, rowDifference);
        while (curPosition.isInsideBoard() && board.getPieceAt(curPosition) == null) {
            moves.add(curPosition);
            curPosition = new Position(curPosition, colDifference, rowDifference);
        }
        if (curPosition.isInsideBoard() && board.getPieceAt(curPosition).getPieceColor() != this.getPieceColor())
            moves.add(curPosition);

        return moves;
    }

    @Override
    public void calculateMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        moves.addAll(checkLine(board, 1, 1));
        moves.addAll(checkLine(board, -1, 1));
        moves.addAll(checkLine(board, 1, -1));
        moves.addAll(checkLine(board, -1, -1));

        moves.addAll(checkLine(board, 1, 0));
        moves.addAll(checkLine(board, 0, 1));
        moves.addAll(checkLine(board, -1, 0));
        moves.addAll(checkLine(board, 0, -1));

        this.setMoves(moves);
    }

    @Override
    public ChessPiece copy() {
        return new Queen(this);
    }

    public Queen(Queen queen) {
        this.moves = new ArrayList<>(queen.moves);
        this.position = Position.copyOf(queen.position);
        this.pieceColor = queen.pieceColor;
    }

    public Queen(Position position, PieceColor color) {
        this.pieceColor = color;
        this.position = position;
    }
}
