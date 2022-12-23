package chessRoot.logic.moves;

import chessRoot.logic.Board;
import chessRoot.logic.pieces.ChessPiece;
import chessRoot.logic.pieces.Position;

/**
 * Abstract move class. Includes a starting position and ending position.
 */
public abstract class Move {
    protected final Position startPosition;
    protected final Position endPosition;

    public Position getStartPosition() {
        return this.startPosition;
    }

    public Position getEndPosition() {
        return this.endPosition;
    }

    public Move(Position startPosition, Position endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public ChessPiece getPieceAtStart(Board board) {
        return board.getPieceAt(startPosition);
    }

    public ChessPiece getPieceAtEnd(Board board) {
        return board.getPieceAt(endPosition);
    }
}