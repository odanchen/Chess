package pieces.moves;

import pieces.ChessPiece;
import pieces.Position;

public class AttackMove extends Move {
    ChessPiece attackedPiece;
    public ChessPiece getAttackedPiece()
    {
        return this.attackedPiece;
    }
    public AttackMove(ChessPiece startPiece, ChessPiece attackedPiece, Position startPosition, Position endPosition)
    {
        this.startPiece = startPiece;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.attackedPiece = attackedPiece;
    }
}
