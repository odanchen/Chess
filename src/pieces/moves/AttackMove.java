package pieces.moves;

import pieces.Position;

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
