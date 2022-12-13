package pieces;

import java.util.List;

public class Knight extends ChessPiece {
    @Override
    List<Position> calculateMoves()
    {
        return null;
    }
    public Knight(Position position, PieceColor color)
    {
        this.pieceColor = color;
        this.position = position;
    }
}
