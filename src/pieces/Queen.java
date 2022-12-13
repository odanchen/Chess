package pieces;

import java.util.List;

public class Queen extends ChessPiece {
    @Override
    List<Position> calculateMoves()
    {
        return null;
    }
    public Queen(Position position, PieceColor color)
    {
        this.pieceColor = color;
        this.position = position;
    }
}
