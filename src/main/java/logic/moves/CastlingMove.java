package logic.moves;

import logic.Board;
import logic.pieces.properties.PieceColor;
import logic.pieces.properties.Position;

/**
 * Standard castling rules apply to this type of move.
 */
public class CastlingMove extends Move {
    private final RelocationMove castleMove;

    public CastlingMove(Position startPosition, Position endPosition, Position castleStart, Position castleEnd) {
        super(startPosition, endPosition);
        castleMove = new RelocationMove(castleStart, castleEnd);
    }

    public RelocationMove getCastleMove() {
        return castleMove;
    }

    public RelocationMove getKingMove() {
        return new RelocationMove(startPosition, endPosition);
    }

    @Override
    public boolean isPossible(Board board) {
        PieceColor sideColor = board.getPieceAt(startPosition).getPieceColor();
        if (board.isUnderAttack(startPosition, sideColor)) return false;
        Board copy = new Board(board);
        copy.makeMove(this);
        return !(copy.isUnderAttack(endPosition, sideColor) || copy.isUnderAttack(castleMove.endPosition, sideColor));
    }

    @Override
    public String toString(Board board) {
        if (endPosition.getCol() == 'g') {
            return "O-O";
        } else {
            return "O-O-O";
        }
    }
}
