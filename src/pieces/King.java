package pieces;

import BoardPackage.Board;
import pieces.moves.AttackMove;
import pieces.moves.Move;
import pieces.moves.RelocationMove;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {
    private boolean hasMoved;

    // Used by calculateMoves to make sure piece is not taking its own color, and it is inside the board.
    private boolean canMoveTo(Position endPosition, Board board) {
        return (endPosition.isInsideBoard() &&
                (board.getPieceAt(endPosition) == null || board.getPieceAt(endPosition).getPieceColor() != this.getPieceColor()));
    }

    private Move newMove(Position endPosition, Board board, Position start)
    {
        if (board.getPieceAt(endPosition) == null) return new RelocationMove(board.getPieceAt(start), start, endPosition);
        return new AttackMove(board.getPieceAt(start), board.getPieceAt(endPosition), start, endPosition);
    }

    @Override
    public void calculateMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        Position endPosition = new Position(this.getPosition(), 1, 0);
        if (canMoveTo(endPosition, board)) moves.add(newMove(endPosition, board, this.getPosition()));
        endPosition = new Position(this.getPosition(), 1, 1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(endPosition, board, this.getPosition()));

        endPosition = new Position(this.getPosition(), 1, -1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(endPosition, board, this.getPosition()));
        endPosition = new Position(this.getPosition(), -1, 0);
        if (canMoveTo(endPosition, board)) moves.add(newMove(endPosition, board, this.getPosition()));

        endPosition = new Position(this.getPosition(), -1, 1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(endPosition, board, this.getPosition()));
        endPosition = new Position(this.getPosition(), -1, -1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(endPosition, board, this.getPosition()));

        endPosition = new Position(this.getPosition(), 0, 1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(endPosition, board, this.getPosition()));
        endPosition = new Position(this.getPosition(), 0, -1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(endPosition, board, this.getPosition()));

        this.setMoves(moves);
    }

    public King(Position position, PieceColor color, boolean hasMoved) {
        this.pieceColor = color;
        this.position = position;
        this.hasMoved = hasMoved;
    }

    public King(Position position, PieceColor color) {
        this.pieceColor = color;
        this.position = position;
        this.hasMoved = false;
    }

    @Override
    public ChessPiece copy() {
        return new King(this);
    }

    public King(King king) {
        this.moves = new ArrayList<>(king.moves);
        this.position = Position.copyOf(king.position);
        this.pieceColor = king.pieceColor;
        this.hasMoved = king.hasMoved;
    }
}
