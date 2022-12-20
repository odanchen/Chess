package pieces;

import board_package.Board;
import pieces.moves.AttackMove;
import pieces.moves.Move;
import pieces.moves.RelocationMove;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    // Returns a line out of the 8 lines of possible movements for the queen.
    private List<Move> checkLine(Position startPosition, int colDifference, int rowDifference, Board board) {
        List<Move> moves = new ArrayList<>();
        Position endPosition = new Position(this.getPosition(), colDifference, rowDifference);

        while (endPosition.isInsideBoard() && board.getPieceAt(endPosition) == null) {
            moves.add(new RelocationMove(startPosition, endPosition));
            endPosition = new Position(endPosition, colDifference, rowDifference);
        }

        if (endPosition.isInsideBoard() && this.notSameColorAs(board.getPieceAt(endPosition)))
            moves.add(new AttackMove(startPosition, endPosition, endPosition));

        return moves;
    }

    @Override
    public List<Move> calculatePotentialMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        moves.addAll(checkLine(this.getPosition(), 1, 1, board));
        moves.addAll(checkLine(this.getPosition(), -1, 1, board));
        moves.addAll(checkLine(this.getPosition(), 1, -1, board));
        moves.addAll(checkLine(this.getPosition(), -1, -1, board));

        moves.addAll(checkLine(this.getPosition(), 0, 1, board));
        moves.addAll(checkLine(this.getPosition(), 1, 0, board));
        moves.addAll(checkLine(this.getPosition(), 0, -1, board));
        moves.addAll(checkLine(this.getPosition(), -1, 0, board));

        return moves;
    }

    public Queen(Queen queen) {
        super(Position.copyOf(queen.getPosition()), queen.getPieceColor());
    }

    public Queen(Position position, PieceColor color) {
        super(position, color);
    }

    @Override
    public ChessPiece copy() {
        return new Queen(this);
    }

    @Override
    public String getPieceLetter() {
        return "n";
    }
}
