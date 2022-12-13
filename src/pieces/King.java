package pieces;

import java.util.List;

public class King extends ChessPiece {
    @Override
    List<Position> calculateMoves()
    {
        return null;
    }
    public King(Position position, PieceColor color)
    {
        this.pieceColor = color;
        this.position = position;
    }
}
