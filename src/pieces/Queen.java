package pieces;

import java.util.List;

public class Queen extends ChessPiece {
    @Override
    List<Coordinate> calculateMoves()
    {
        return null;
    }
    public Queen(Coordinate position, char color)
    {
        this.pieceColor = color;
        this.position = position;
    }
}
