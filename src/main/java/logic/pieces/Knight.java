package logic.pieces;

import logic.Board;
import logic.moves.Move;
import logic.pieces.properties.PieceColor;
import logic.pieces.properties.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {

    public Knight(Position position, PieceColor color) {
        super(position, color);
    }

    public Knight(Knight knight) {
        super(Position.copyOf(knight.getPosition()), knight.getPieceColor());
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
