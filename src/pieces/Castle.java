package pieces;

import java.util.List;

public class Castle extends ChessPiece {
    @Override
    List<Position> calculateMoves()
    {
        return null;
    }
    public Castle(Position position, PieceColor color)
    {
        this.pieceColor = color;
        this.position = position;
    }
}
