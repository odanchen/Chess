package pieces;

import BoardPackage.Board;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {
    private boolean hasMoved;
    @Override
    public void calculateMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        Position direction;
        int colorCoef = (this.getPieceColor() == PieceColor.BLACK) ? -1 : 1;

        direction = new Position(this.getPosition(), 0, colorCoef);
        if (board.getPieceAt(direction) == null) {
            moves.add(direction);

            direction = new Position(this.getPosition(), 0, 2 * colorCoef);
            if (board.getPieceAt(direction) == null && !this.hasMoved) moves.add(direction);
        }

        direction = new Position(this.getPosition(), 1, colorCoef);
        if (board.getPieceAt(direction) != null && board.getPieceAt(direction).getPieceColor() != this.getPieceColor())
            moves.add(direction);
        direction = new Position(this.getPosition(), -1, colorCoef);
        if (board.getPieceAt(direction) != null && board.getPieceAt(direction).getPieceColor() != this.getPieceColor())
            moves.add(direction);

        this.setMoves(moves);
    }

    public Pawn(Position position, PieceColor color, boolean hasMoved) {
        this.pieceColor = color;
        this.position = position;
        this.hasMoved = hasMoved;
    }

    public Pawn(Position position, PieceColor color) {
        this.pieceColor = color;
        this.position = position;
        this.hasMoved = false;
    }
}
