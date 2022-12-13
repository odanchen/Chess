package pieces;

import java.util.List;

public abstract class ChessPiece {

    protected char pieceColor;
    protected Coordinate position;
    public Coordinate getPosition()
    {
        return this.position;
    }
    public char getPieceColor()
    {
        return this.pieceColor;
    }
    public void setPieceColor(char color)
    {
        this.pieceColor = color;
    }
    public void setPosition(Coordinate position)
    {
        this.position = position;
    }

    abstract List<Coordinate> calculateMoves();
}
