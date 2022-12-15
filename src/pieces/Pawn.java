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

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    public Pawn(Position position, PieceColor color, boolean hasMoved) {
        super(position, color);
        this.hasMoved = hasMoved;
    }

    public Pawn(Position position, PieceColor color) {
        super(position, color);
        this.hasMoved = false;
    }

    public Pawn(Pawn pawn) {
        super(Position.copyOf(pawn.getPosition()), pawn.getPieceColor());
        this.moves = new ArrayList<>(pawn.getMoves());
        this.hasMoved = pawn.getHasMoved();
    }

    @Override
    public ChessPiece copy() {
        return new Pawn(this);
    }
}
