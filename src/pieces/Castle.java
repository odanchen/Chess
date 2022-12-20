package pieces;

import board_package.Board;
import pieces.moves.AttackMove;
import pieces.moves.Move;
import pieces.moves.RelocationMove;

import java.util.ArrayList;
import java.util.List;

public class Castle extends ChessPiece {
    private boolean hasMoved;

    // This method is used by the calculateMoves method. It calculates the moves for one line of direction of a castle.
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

        moves.addAll(checkLine(this.getPosition(), 0, 1, board));
        moves.addAll(checkLine(this.getPosition(), 1, 0, board));
        moves.addAll(checkLine(this.getPosition(), 0, -1, board));
        moves.addAll(checkLine(this.getPosition(), -1, 0, board));

        return moves;
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public Castle(Position position, PieceColor color, boolean hasMoved) {
        super(position, color);
        this.hasMoved = hasMoved;
    }

    public Castle(Position position, PieceColor color) {
        super(position, color);
        this.hasMoved = false;
    }

    public Castle(Castle castle) {
        super(Position.copyOf(castle.getPosition()), castle.getPieceColor());
        this.hasMoved = castle.getHasMoved();
    }

    @Override
    public ChessPiece copy() {
        return new Castle(this);
    }
}
