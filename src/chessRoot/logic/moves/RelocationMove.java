package chessRoot.logic.moves;

import chessRoot.logic.pieces.Position;

/**
 * A standard piece where a chess piece is moving from one place to another.
 */
public class RelocationMove extends Move {
    public RelocationMove(Position startPosition, Position endPosition) {
        super(startPosition, endPosition);
    }
}
