package logic.pieces;

import logic.Board;
import logic.moves.AttackMove;
import logic.moves.Move;
import logic.pieces.properties.PieceColor;
import logic.pieces.properties.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {
    private boolean hasMoved;
    private boolean justMovedTwo = false;

    public Pawn(Position position, PieceColor color) {
        super(position, color);
        this.hasMoved = false;
    }

    public Pawn(Pawn pawn) {
        super(Position.copyOf(pawn.getPosition()), pawn.getPieceColor());
        this.hasMoved = pawn.getHasMoved();
        this.justMovedTwo = pawn.getHasMovedTwo();
    }

    @Override
    public List<Move> calculatePotentialMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Position endPosition;
        Position attackedPosition;
        int direction = (isBlack()) ? -1 : 1;

        endPosition = new Position(getPosition(), 0, direction);
        if (endPosition.insideBoard() && board.isEmptyAt(endPosition)) {
            moves.add(newMove(getPosition(), endPosition, board));

            endPosition = new Position(getPosition(), 0, 2 * direction);
            if (endPosition.insideBoard() && board.isEmptyAt(endPosition) && !hasMoved)
                moves.add(newMove(getPosition(), endPosition, board));
        }

        endPosition = new Position(getPosition(), 1, direction);
        if (canAttack(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));

        endPosition = new Position(getPosition(), -1, direction);
        if (canAttack(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));

        // En Passant
        endPosition = new Position(getPosition(), -1, direction);
        attackedPosition = new Position(getPosition(), -1, 0);
        if (canAttack(attackedPosition, board) && board.getPieceAt(attackedPosition) instanceof Pawn && ((Pawn) board.getPieceAt(attackedPosition)).getHasMovedTwo()) {
            moves.add(new AttackMove(getPosition(), endPosition, attackedPosition));
        }

        endPosition = new Position(getPosition(), 1, direction);
        attackedPosition = new Position(getPosition(), 1, 0);
        if (canAttack(attackedPosition, board) && board.getPieceAt(attackedPosition) instanceof Pawn && ((Pawn) board.getPieceAt(attackedPosition)).getHasMovedTwo()) {
            moves.add(new AttackMove(getPosition(), endPosition, attackedPosition));
        }

        return moves;
    }

    private boolean canAttack(Position endPosition, Board board) {
        return endPosition.insideBoard() && board.isNotEmptyAt(endPosition) && differentColorFrom(board.getPieceAt(endPosition));
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean getHasMovedTwo() {
        return this.justMovedTwo;
    }

    public void setHasMovedTwo(boolean justMovedTwo) {
        this.justMovedTwo = justMovedTwo;
    }

    @Override
    public ChessPiece copy() {
        return new Pawn(this);
    }

    @Override
    public String getPieceSignature() {
        return pieceColor.getColorSign() + "p";
    }

    @Override
    public String getNotationSignature() {
        return "";
    }
}
