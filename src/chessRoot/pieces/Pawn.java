package chessRoot.pieces;

import chessRoot.board_package.Board;
import chessRoot.pieces.moves.AttackMove;
import chessRoot.pieces.moves.Move;
import chessRoot.pieces.moves.RelocationMove;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {
    private boolean hasMoved;

    @Override
    public List<Move> calculatePotentialMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Position endPosition;
        int direction = (this.getPieceColor() == PieceColor.BLACK) ? -1 : 1;

        endPosition = new Position(this.getPosition(), 0, direction);
        if (endPosition.isInsideBoard() && board.getPieceAt(endPosition) == null) {
            moves.add(new RelocationMove(this.getPosition(), endPosition));

            endPosition = new Position(this.getPosition(), 0, 2 * direction);
            if (board.getPieceAt(endPosition) == null && !this.hasMoved)
                moves.add(new RelocationMove(this.getPosition(), endPosition));
        }

        endPosition = new Position(this.getPosition(), 1, direction);
        if (canAttack(endPosition, board))
            moves.add(new AttackMove(this.getPosition(), endPosition, endPosition));
        endPosition = new Position(this.getPosition(), -1, direction);
        if (canAttack(endPosition, board))
            moves.add(new AttackMove(this.getPosition(), endPosition, endPosition));

        return moves;
    }

    private boolean canAttack(Position endPosition, Board board)
    {
        return endPosition.isInsideBoard() &&
                board.getPieceAt(endPosition) != null &&
                this.notSameColorAs(board.getPieceAt(endPosition));
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
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
        this.hasMoved = pawn.getHasMoved();
    }

    @Override
    public ChessPiece copy() {
        return new Pawn(this);
    }

    @Override
    public String getPieceLetter() {
        return "p";
    }
}
