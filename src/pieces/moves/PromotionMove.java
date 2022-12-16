package pieces.moves;

import pieces.Position;

/**
 * If the pawn has reached the last tile. While not attacking any pieces.
 */
public class PromotionMove extends Move {
    public PromotionMove(Position startPosition, Position endPosition) {
        super(startPosition, endPosition);
    }
}
