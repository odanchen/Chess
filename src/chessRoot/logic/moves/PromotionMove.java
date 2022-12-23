package chessRoot.logic.moves;

import chessRoot.logic.Board;
import chessRoot.logic.pieces.Position;

/**
 * If the pawn has reached the last tile. While not attacking any chessRoot.logic.pieces.
 */
public class PromotionMove extends Move {
    public PromotionMove(Position startPosition, Position endPosition) {
        super(startPosition, endPosition);
    }

    @Override
    public boolean isPossible(Board board) {
        Board copyBoard = new Board(board);
        copyBoard.makeMove(this);
        return !copyBoard.isCheck(copyBoard.getPieceAt(this.getEndPosition()).getPieceColor());
    }
}
