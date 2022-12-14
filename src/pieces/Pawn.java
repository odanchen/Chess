package pieces;

import BoardPackage.Board;

import java.util.List;

public class Pawn extends ChessPiece {
    private boolean hasMoved;
    @Override
    public void calculateMoves(Board board)
    {

    }
    public Pawn(Position position, PieceColor color, boolean hasMoved)
    {
        this.pieceColor = color;
        this.position = position;
        this.hasMoved = hasMoved;
    }
    public Pawn(Position position, PieceColor color)
    {
        this.pieceColor = color;
        this.position = position;
        this.hasMoved = false;
    }
}
