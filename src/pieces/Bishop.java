package pieces;

import java.util.List;

public class Bishop extends ChessPiece {
    @Override
    List<Position> calculateMoves()
    {
        return null;
    }

    public Bishop(Position position, PieceColor color)
    {
        this.pieceColor = color;
        this.position = position;
    }
}
