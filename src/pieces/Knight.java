package pieces;

import java.util.List;

public class Knight extends ChessPiece {
    @Override
    List<Coordinate> calculateMoves()
    {
        return null;
    }
    public Knight(Coordinate position, char color)
    {
        this.pieceColor = color;
        this.position = position;
    }
}
