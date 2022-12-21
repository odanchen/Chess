package chessRoot.pieces.moves;

import chessRoot.pieces.Position;

/**
 * If the pawn has reached the last tile. While not attacking any chessRoot.pieces.
 */
public class PromotionMove extends Move {
    public PromotionMove(Position startPosition, Position endPosition) {
        super(startPosition, endPosition);
    }
}
