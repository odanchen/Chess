package chessRoot.logic.moves;

import chessRoot.logic.Board;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.logic.pieces.Position;

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
}
