package pieces;

import java.util.List;

public class Castle extends ChessPiece {
    @Override
    List<Coordinate> calculateMoves()
    {
        return null;
    }
    public Castle(Coordinate position, char color)
    {
        this.pieceColor = color;
        this.position = position;
    }
}
