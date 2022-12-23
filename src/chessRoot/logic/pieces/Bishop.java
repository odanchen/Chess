package chessRoot.logic.pieces;

import chessRoot.logic.Board;
import chessRoot.logic.moves.AttackMove;
import chessRoot.logic.moves.Move;
import chessRoot.logic.moves.RelocationMove;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    // This method is used by the calculateMoves method. It calculates the moves for one line of direction of a bishop.
    private List<Move> checkLine(Position startPosition, int colDifference, int rowDifference, Board board) {
        List<Move> moves = new ArrayList<>();
        Position endPosition = new Position(getPosition(), colDifference, rowDifference);

        while (endPosition.insideBoard() && board.isEmptyAt(endPosition)) {
            moves.add(new RelocationMove(startPosition, endPosition));
            endPosition = new Position(endPosition, colDifference, rowDifference);
        }

        if (endPosition.insideBoard() && this.differentColorFrom(board.getPieceAt(endPosition)))
            moves.add(new AttackMove(startPosition, endPosition, endPosition));

        return moves;
    }

    @Override
    public List<Move> calculatePotentialMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        moves.addAll(checkLine(getPosition(), 1, 1, board));
        moves.addAll(checkLine(getPosition(), -1, 1, board));
        moves.addAll(checkLine(getPosition(), 1, -1, board));
        moves.addAll(checkLine(getPosition(), -1, -1, board));

        return moves;
    }

    public Bishop(Position position, PieceColor color) {
        super(position, color);
    }

    public Bishop(Bishop bishop) {
        super(Position.copyOf(bishop.getPosition()), bishop.getPieceColor());
    }

    @Override
    public ChessPiece copy() {
        return new Bishop(this);
    }

    @Override
    public String getPieceLetter() {
        return "b";
    }
}
