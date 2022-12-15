package pieces.moves;

import pieces.ChessPiece;
import pieces.Position;

public class RelocationMove extends Move{
    public RelocationMove(ChessPiece startPiece, Position startPosition, Position endPosition)
    {
        this.startPiece = startPiece;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
}
