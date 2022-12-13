package pieces;

import java.util.List;

public class Pawn extends ChessPiece {
    private boolean hasMoved;
    @Override
    List<Coordinate> calculateMoves()
    {
        return null;
    }
    public Pawn(Coordinate position, char color, boolean hasMoved)
    {
        this.pieceColor = color;
        this.position = position;
        this.hasMoved = hasMoved;
    }
}
