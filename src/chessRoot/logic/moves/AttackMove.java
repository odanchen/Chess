package chessRoot.logic.moves;

import chessRoot.logic.pieces.Position;

/**
 * A type of move where the enemy piece is being taken. Includes an attacked position.
 */
public class AttackMove extends Move {
    private final Position attackedPosition;

    public Position getAttackedPosition() {
        return this.attackedPosition;
    }

    public AttackMove(Position startPosition, Position endPosition, Position attackedPosition) {
        super(startPosition, endPosition);
        this.attackedPosition = attackedPosition;
    }
}
