package chessRoot.logic.pieces;

import chessRoot.logic.Board;
import chessRoot.logic.moves.AttackMove;
import chessRoot.logic.moves.Move;
import chessRoot.logic.moves.RelocationMove;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {
    private boolean hasMoved;

    // Used by calculateMoves to make sure piece is not taking its own color, and it is inside the board.
    private boolean canMoveTo(Position endPosition, Board board) {
        return (endPosition.isInsideBoard() &&
                (board.getPieceAt(endPosition) == null || this.notSameColorAs(board.getPieceAt(endPosition))));
    }

    private Move newMove(Position startPosition, Position endPosition, Board board) {
        if (board.getPieceAt(endPosition) == null) return new RelocationMove(startPosition, endPosition);
        return new AttackMove(startPosition, endPosition, endPosition);
    }

    @Override
    public List<Move> calculatePotentialMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Position endPosition;

        endPosition = new Position(this.getPosition(), 1, 0);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));
        endPosition = new Position(this.getPosition(), 1, 1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));

        endPosition = new Position(this.getPosition(), 1, -1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));
        endPosition = new Position(this.getPosition(), -1, 0);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));

        endPosition = new Position(this.getPosition(), -1, 1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));
        endPosition = new Position(this.getPosition(), -1, -1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));

        endPosition = new Position(this.getPosition(), 0, 1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));
        endPosition = new Position(this.getPosition(), 0, -1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));

        return moves;
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public King(Position position, PieceColor color, boolean hasMoved) {
        super(position, color);
        this.hasMoved = hasMoved;
    }

    public King(Position position, PieceColor color) {
        super(position, color);
        this.hasMoved = false;
    }

    public King(King king) {
        super(Position.copyOf(king.getPosition()), king.getPieceColor());
        this.hasMoved = king.getHasMoved();
    }

    @Override
    public ChessPiece copy() {
        return new King(this);
    }

    @Override
    public String getPieceLetter() {
        return "k";
    }
}
