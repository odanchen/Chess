package chessRoot.logic.moves;

import chessRoot.logic.Board;
import chessRoot.logic.pieces.Position;

import static chessRoot.logic.pieces.PieceColor.WHITE;

/**
 * Standard castling rules apply to this type of move.
 */
public class CastlingMove extends Move {
    public CastlingMove(Position startPosition, Position endPosition) {
        super(startPosition, endPosition);
    }

    @Override
    public boolean isPossible(Board board) {
        if (getPieceAtStart(board).isWhite() && endPosition.equals(Position.at("g1"))) {
            return (!board.isUnderAttack(startPosition, WHITE) &&
                    !board.isUnderAttack(Position.at("f1"), WHITE) &&
                    !board.isUnderAttack(Position.at("g1"), WHITE));
        } else if (getPieceAtStart(board).isWhite() && endPosition.equals(Position.at("c1"))) {
            return (board.isEmptyAt(Position.at("b1")) && board.isEmptyAt(Position.at("c1")) &&
                    board.isEmptyAt(Position.at("d1")) &&
                    !board.isUnderAttack(startPosition, WHITE) &&
                    !board.isUnderAttack(Position.at("c1"), WHITE) &&
                    !board.isUnderAttack(Position.at("d1"), WHITE));
        } else if (getPieceAtStart(board).isBlack() && endPosition.equals(Position.at("g8"))) {
            return (board.isEmptyAt(Position.at("g8")) && board.isEmptyAt(Position.at("f8")) &&
                    !board.isUnderAttack(startPosition, WHITE) &&
                    !board.isUnderAttack(Position.at("f8"), WHITE) &&
                    !board.isUnderAttack(Position.at("g8"), WHITE));
        } else if (getPieceAtStart(board).isBlack() && endPosition.equals(Position.at("c8"))) {
            return (board.isEmptyAt(Position.at("b8")) && board.isEmptyAt(Position.at("c8")) &&
                    board.isEmptyAt(Position.at("d8")) &&
                    !board.isUnderAttack(startPosition, WHITE) &&
                    !board.isUnderAttack(Position.at("c8"), WHITE) &&
                    !board.isUnderAttack(Position.at("d8"), WHITE));
        }
        return false;
    }
}
