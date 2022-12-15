package pieces.moves;

import pieces.Position;

public abstract class Move {
    protected final Position startPosition;
    protected final Position endPosition;

    public Position getStartPosition() {
        return this.startPosition;
    }

    public Position getEndPosition() {
        return this.endPosition;
    }

    public Move(Position startPosition, Position endPosition)
    {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
}
