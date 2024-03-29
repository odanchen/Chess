package logic.moves;

import logic.pieces.ChessPiece;
import logic.Board;
import logic.pieces.properties.Position;

/**
 * Abstract move class. Includes a starting position and ending position.
 */
public abstract class Move {
    protected final Position startPosition;
    protected final Position endPosition;

    public Move(Position startPosition, Position endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public Position getStartPosition() {
        return this.startPosition;
    }

    public Position getEndPosition() {
        return this.endPosition;
    }

    public ChessPiece getPieceAtStart(Board board) {
        return board.getPieceAt(startPosition);
    }

    public ChessPiece getPieceAtEnd(Board board) {
        return board.getPieceAt(endPosition);
    }

    public abstract String toString(Board board);

    public abstract boolean isPossible(Board board);
}
