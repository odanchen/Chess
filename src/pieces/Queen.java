package pieces;

import BoardPackage.Board;
import pieces.moves.AttackMove;
import pieces.moves.Move;
import pieces.moves.RelocationMove;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    // Returns a line out of the 8 lines of possible movements for the queen.
    private List<Move> checkLine(Board board, int colDifference, int rowDifference, Position start) {
        List<Move> moves = new ArrayList<>();
        Position endPosition = new Position(this.getPosition(), colDifference, rowDifference);

        while (endPosition.isInsideBoard() && board.getPieceAt(endPosition) == null) {
            moves.add(new RelocationMove(board.getPieceAt(start), start, endPosition));
            endPosition = new Position(endPosition, colDifference, rowDifference);
        }

        if (endPosition.isInsideBoard() && board.getPieceAt(endPosition).getPieceColor() != this.getPieceColor())
            moves.add(new AttackMove(board.getPieceAt(start), board.getPieceAt(endPosition), start, endPosition));

        return moves;
    }

    @Override
    public void calculateMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        moves.addAll(checkLine(board, 1, 1, this.getPosition()));
        moves.addAll(checkLine(board, -1, 1, this.getPosition()));
        moves.addAll(checkLine(board, 1, -1, this.getPosition()));
        moves.addAll(checkLine(board, -1, -1, this.getPosition()));

        moves.addAll(checkLine(board, 1, 0, this.getPosition()));
        moves.addAll(checkLine(board, 0, 1, this.getPosition()));
        moves.addAll(checkLine(board, -1, 0, this.getPosition()));
        moves.addAll(checkLine(board, 0, -1, this.getPosition()));

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
