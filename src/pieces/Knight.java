package pieces;

import BoardPackage.Board;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {
    private boolean canMoveTo(Position position, Board board) {
        return (position.isInsideBoard() &&
                (board.getPieceAt(position) == null || board.getPieceAt(position).getPieceColor() != this.getPieceColor()));
    }

    @Override
    public void calculateMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        Position direction = new Position(this.getPosition(), 2, 1);
        if (canMoveTo(direction, board)) moves.add(direction);
        direction = new Position(this.getPosition(), 2, -1);
        if (canMoveTo(direction, board)) moves.add(direction);

        direction = new Position(this.getPosition(), -2, 1);
        if (canMoveTo(direction, board)) moves.add(direction);
        direction = new Position(this.getPosition(), -2, -1);
        if (canMoveTo(direction, board)) moves.add(direction);

        direction = new Position(this.getPosition(), 1, 2);
        if (canMoveTo(direction, board)) moves.add(direction);
        direction = new Position(this.getPosition(), -1, 2);
        if (canMoveTo(direction, board)) moves.add(direction);

        direction = new Position(this.getPosition(), 1, -2);
        if (canMoveTo(direction, board)) moves.add(direction);
        direction = new Position(this.getPosition(), -1, -2);
        if (canMoveTo(direction, board)) moves.add(direction);

        this.setMoves(moves);
    }

    public Knight(Position position, PieceColor color) {
        this.pieceColor = color;
        this.position = position;
    }

    @Override
    public ChessPiece copy() {
        return new Knight(this);
    }

    public Knight(Knight knight) {
        this.moves = new ArrayList<>(knight.moves);
        this.position = Position.copyOf(knight.position);
        this.pieceColor = knight.pieceColor;
    }
}
