package pieces;

import java.util.List;

public class King extends ChessPiece {
    @Override
    List<Coordinate> calculateMoves()
    {
        return null;
    }
    public King(Coordinate position, char color)
    {
        this.pieceColor = color;
        this.position = position;
    }
}
