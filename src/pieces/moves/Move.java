package pieces.moves;

import pieces.ChessPiece;
import pieces.Position;

public abstract class Move {
    protected ChessPiece startPiece;
    protected Position startPosition;
    protected Position endPosition;

    public Position getStartPosition() {
        return this.startPosition;
    }

    public Position getEndPosition() {
        return this.endPosition;
    }

    public ChessPiece getStartPiece() {
        return this.startPiece;
    }
}
