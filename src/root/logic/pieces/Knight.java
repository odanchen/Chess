package root.logic.pieces;

import root.logic.Board;
import root.logic.moves.AttackMove;
import root.logic.moves.Move;
import root.logic.moves.RelocationMove;
import root.logic.pieces.properties.PieceColor;
import root.logic.pieces.properties.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {

    public Knight(Position position, PieceColor color) {
        super(position, color);
    }

    public Knight(Knight knight) {
        super(Position.copyOf(knight.getPosition()), knight.getPieceColor());
    }

    private boolean canMoveTo(Position endPosition, Board board) {
        return (endPosition.insideBoard() &&
                (board.isEmptyAt(endPosition) || differentColorFrom(board.getPieceAt(endPosition))));
    }

    private Move newMove(Position startPosition, Position endPosition, Board board) {
        if (board.isEmptyAt(endPosition)) return new RelocationMove(startPosition, endPosition);
        return new AttackMove(startPosition, endPosition, endPosition);
    }

    @Override
    public List<Move> calculatePotentialMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Position endPosition;

        endPosition = new Position(getPosition(), 2, 1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));
        endPosition = new Position(getPosition(), 2, -1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));

        endPosition = new Position(getPosition(), -2, 1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));
        endPosition = new Position(getPosition(), -2, -1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));

        endPosition = new Position(getPosition(), 1, 2);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));
        endPosition = new Position(getPosition(), -1, 2);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));

        endPosition = new Position(getPosition(), 1, -2);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));
        endPosition = new Position(getPosition(), -1, -2);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));

        return moves;
    }

    @Override
    public ChessPiece copy() {
        return new Knight(this);
    }

    @Override
    public String getPieceSignature() {
        return pieceColor.getColorSign() + "n";
    }

    @Override
    public String getNotationSignature() {
        return "N";
    }
}
