package pieces;

import BoardPackage.Board;
import pieces.moves.AttackMove;
import pieces.moves.Move;
import pieces.moves.RelocationMove;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {
    private boolean hasMoved;

    @Override
    public void calculateMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Position endPosition;
        int colorCoef = (this.getPieceColor() == PieceColor.BLACK) ? -1 : 1;

        endPosition = new Position(this.getPosition(), 0, colorCoef);
        if (board.getPieceAt(endPosition) == null) {
            moves.add(new RelocationMove(this, this.getPosition(), endPosition));

            endPosition = new Position(this.getPosition(), 0, 2 * colorCoef);
            if (board.getPieceAt(endPosition) == null && !this.hasMoved)
                moves.add(new RelocationMove(this, this.getPosition(), endPosition));
        }

        endPosition = new Position(this.getPosition(), 1, colorCoef);
        if (board.getPieceAt(endPosition) != null && board.getPieceAt(endPosition).getPieceColor() != this.getPieceColor())
            moves.add(new AttackMove(this, board.getPieceAt(endPosition), this.getPosition(), endPosition));
        endPosition = new Position(this.getPosition(), -1, colorCoef);
        if (board.getPieceAt(endPosition) != null && board.getPieceAt(endPosition).getPieceColor() != this.getPieceColor())
            moves.add(new AttackMove(this, board.getPieceAt(endPosition), this.getPosition(), endPosition));

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

    @Override
    public ChessPiece copy() {
        return new Pawn(this);
    }

    public Pawn(Pawn pawn) {
        this.moves = new ArrayList<>(pawn.moves);
        this.position = Position.copyOf(pawn.position);
        this.pieceColor = pawn.pieceColor;
        this.hasMoved = pawn.hasMoved;
    }
}
