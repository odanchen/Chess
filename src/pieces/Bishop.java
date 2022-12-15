package pieces;

import BoardPackage.Board;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    // This method is used by the calculateMoves method. It calculates the moves for one line of direction of a bishop.
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

        this.setMoves(moves);
    }

    public Bishop(Position position, PieceColor color) {
        this.pieceColor = color;
        this.position = position;
    }

    @Override
    public ChessPiece copy() {
        return new Bishop(this);
    }

    public Bishop(Bishop bishop) {
        this.moves = new ArrayList<>(bishop.moves);
        this.position = Position.copyOf(bishop.position);
        this.pieceColor = bishop.pieceColor;
    }
}
