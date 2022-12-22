package chessRoot.pieces;

import chessRoot.Board;
import chessRoot.pieces.moves.AttackMove;
import chessRoot.pieces.moves.Move;
import chessRoot.pieces.moves.RelocationMove;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {

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

        endPosition = new Position(this.getPosition(), 2, 1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));
        endPosition = new Position(this.getPosition(), 2, -1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));

        endPosition = new Position(this.getPosition(), -2, 1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));
        endPosition = new Position(this.getPosition(), -2, -1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));

        endPosition = new Position(this.getPosition(), 1, 2);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));
        endPosition = new Position(this.getPosition(), -1, 2);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));

        endPosition = new Position(this.getPosition(), 1, -2);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));
        endPosition = new Position(this.getPosition(), -1, -2);
        if (canMoveTo(endPosition, board)) moves.add(newMove(this.getPosition(), endPosition, board));

        return moves;
    }

    public Knight(Position position, PieceColor color) {
        super(position, color);
    }

    public Knight(Knight knight) {
        super(Position.copyOf(knight.getPosition()), knight.getPieceColor());
    }

    @Override
    public ChessPiece copy() {
        return new Knight(this);
    }

    @Override
    public String getPieceLetter() {
        return "n";
    }
}
