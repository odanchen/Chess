package chessRoot.logic.moves;

import chessRoot.logic.Board;
import chessRoot.logic.pieces.ChessPiece;
import chessRoot.logic.pieces.Position;

/**
 * A type of move where the enemy piece is being taken. Includes an attacked position.
 */
public class AttackMove extends Move {
    private final Position attackedPosition;

    public ChessPiece getAttackedPiece(Board board) {
        return board.getPieceAt(attackedPosition);
    }

    public AttackMove(Position startPosition, Position endPosition, Position attackedPosition) {
        super(startPosition, endPosition);
        this.attackedPosition = attackedPosition;
    }

    public boolean isAttackedPosition(Position position) {
        return attackedPosition.equals(position);
    }
}