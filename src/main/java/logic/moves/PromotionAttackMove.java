package logic.moves;

import logic.pieces.ChessPiece;
import logic.Board;

/**
 * If the pawn has reached the last tile. While not attacking any chessRoot.logic.pieces.
 */
public class PromotionAttackMove extends AttackMove {
    private final ChessPiece newPiece;

    public PromotionAttackMove(AttackMove move, ChessPiece newPiece) {
        super(move.getStartPosition(), move.getEndPosition(), move.getAttackedPosition());
        this.newPiece = newPiece;
    }

    public ChessPiece getNewPiece() {
        return newPiece;
    }

    @Override
    public boolean isPossible(Board board) {
        Board copyBoard = new Board(board);
        copyBoard.makeMove(this);
        return !copyBoard.isCheck(copyBoard.getPieceAt(this.getEndPosition()).getPieceColor());
    }

    @Override
    public String toString(Board board) {
        return getStartPosition().getCol() + "x" + getEndPosition().toString() + "=" + getNewPiece().getNotationSignature();
    }
}
