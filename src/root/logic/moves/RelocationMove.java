package root.logic.moves;

import root.logic.Board;
import root.logic.pieces.properties.Position;

/**
 * A standard piece where a chess piece is moving from one place to another.
 */
public class RelocationMove extends Move {
    public RelocationMove(Position startPosition, Position endPosition) {
        super(startPosition, endPosition);
    }

    @Override
    public boolean isPossible(Board board) {
        Board copyBoard = new Board(board);
        copyBoard.makeMove(this);
        return !copyBoard.isCheck(copyBoard.getPieceAt(this.getEndPosition()).getPieceColor());
    }

    @Override
    public String toString(Board board) {
        return getPieceAtEnd(board).getNotationSignature() + getEndPosition().toString();
    }
}
