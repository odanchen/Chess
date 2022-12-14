package pieces;

import BoardPackage.Board;

import java.util.List;

public class Knight extends ChessPiece {
    @Override
    public void calculateMoves(Board board)
    {

    }
    public Knight(Position position, PieceColor color)
    {
        this.pieceColor = color;
        this.position = position;
    }
}
