package pieces;

import java.util.List;

public class Bishop extends ChessPiece {
    @Override
    List<Coordinate> calculateMoves()
    {
        return null;
    }

    public Bishop(Coordinate position, char color)
    {
        this.pieceColor = color;
        this.position = position;
    }
}
