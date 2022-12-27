package chessRoot.logic.moves;

import chessRoot.logic.Board;
import chessRoot.logic.pieces.ChessPiece;
import chessRoot.logic.pieces.Position;

/**
 * If the pawn has reached the last tile. While not attacking any chessRoot.logic.pieces.
 */
public class PromotionAttackMove extends Move {
    private final ChessPiece newPiece;
    private final AttackMove attackMove;

    public PromotionAttackMove(Position startPosition, Position endPosition, ChessPiece newPiece, Position attackPos) {
        super(startPosition, endPosition);
        this.newPiece = newPiece;
        this.attackMove = new AttackMove(startPosition, endPosition, attackPos);
    }

    public AttackMove getAttackMove() {
        return attackMove;
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
}
