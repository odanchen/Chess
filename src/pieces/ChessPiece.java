package pieces;

import java.util.List;

public abstract class ChessPiece {

    protected PieceColor pieceColor;
    protected Position position;
    public Position getPosition()
    {
        return this.position;
    }
    public PieceColor getPieceColor()
    {
        return this.pieceColor;
    }
    public void setPieceColor(PieceColor color)
    {
        this.pieceColor = color;
    }
    public void setPosition(Position position)
    {
        this.position = position;
    }

    abstract List<Position> calculateMoves();
}
