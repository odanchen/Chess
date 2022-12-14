package pieces;

import BoardPackage.Board;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {
    private boolean hasMoved;
    private boolean canMoveTo(Position position, Board board)
    {
        return (position.isInsideBoard() &&
                (board.getPieceAt(position) == null || board.getPieceAt(position).getPieceColor() != this.getPieceColor()));
    }
    @Override
    public void calculateMoves(Board board)
    {
        List<Position> moves = new ArrayList<>();

        Position direction = new Position(this.getPosition(), 1, 0);
        if (canMoveTo(direction, board)) moves.add(direction);
        direction = new Position(this.getPosition(), 1, 1);
        if (canMoveTo(direction, board)) moves.add(direction);

        direction = new Position(this.getPosition(), 1, -1);
        if (canMoveTo(direction, board)) moves.add(direction);
        direction = new Position(this.getPosition(), -1, 0);
        if (canMoveTo(direction, board)) moves.add(direction);

        direction = new Position(this.getPosition(), -1, 1);
        if (canMoveTo(direction, board)) moves.add(direction);
        direction = new Position(this.getPosition(), -1, -1);
        if (canMoveTo(direction, board)) moves.add(direction);

        direction = new Position(this.getPosition(), 0, 1);
        if (canMoveTo(direction, board)) moves.add(direction);
        direction = new Position(this.getPosition(), 0, -1);
        if (canMoveTo(direction, board)) moves.add(direction);

        this.setMoves(moves);
    }
    public King(Position position, PieceColor color, boolean hasMoved)
    {
        this.pieceColor = color;
        this.position = position;
        this.hasMoved = hasMoved;
    }
    public King(Position position, PieceColor color)
    {
        this.pieceColor = color;
        this.position = position;
        this.hasMoved = false;
    }
}
