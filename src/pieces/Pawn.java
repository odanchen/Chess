package pieces;

import java.util.List;

public class Pawn extends ChessPiece {
    private boolean hasMoved;
    @Override
    List<Position> calculateMoves()
    {
        return null;
    }
    public Pawn(Position position, PieceColor color, boolean hasMoved)
    {
        this.pieceColor = color;
        this.position = position;
        this.hasMoved = hasMoved;
    }
}
