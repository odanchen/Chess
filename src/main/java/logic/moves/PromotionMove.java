package logic.moves;

import logic.pieces.ChessPiece;
import logic.Board;
import logic.pieces.properties.Position;

/**
 * If the pawn has reached the last tile. While not attacking any chessRoot.logic.pieces.
 */
public class PromotionMove extends RelocationMove {
    private final ChessPiece newPiece;

    public PromotionMove(RelocationMove move, ChessPiece newPiece) {
        super(Position.copyOf(move.getStartPosition()), Position.copyOf(move.getEndPosition()));
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
        return getEndPosition().toString() + "=" + getNewPiece().getNotationSignature();
    }
}
