package logic.pieces;

import logic.Board;
import logic.moves.Move;
import logic.pieces.properties.PieceColor;
import logic.pieces.properties.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    public Queen(Queen queen) {
        super(Position.copyOf(queen.getPosition()), queen.getPieceColor());
    }

    public Queen(Position position, PieceColor color) {
        super(position, color);
    }

    @Override
    public List<Move> calculatePotentialMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        moves.addAll(checkLine(getPosition(), 1, 1, board));
        moves.addAll(checkLine(getPosition(), -1, 1, board));
        moves.addAll(checkLine(getPosition(), 1, -1, board));
        moves.addAll(checkLine(getPosition(), -1, -1, board));

        moves.addAll(checkLine(getPosition(), 0, 1, board));
        moves.addAll(checkLine(getPosition(), 1, 0, board));
        moves.addAll(checkLine(getPosition(), 0, -1, board));
        moves.addAll(checkLine(getPosition(), -1, 0, board));

        return moves;
    }

    @Override
    public ChessPiece copy() {
        return new Queen(this);
    }

    @Override
    public String getPieceSignature() {
        return pieceColor.getColorSign() + "q";
    }

    @Override
    public String getNotationSignature() {
        return "Q";
    }
}
