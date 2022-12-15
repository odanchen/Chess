package pieces;

import BoardPackage.Board;
import pieces.moves.AttackMove;
import pieces.moves.Move;
import pieces.moves.RelocationMove;

import java.util.ArrayList;
import java.util.List;

public class Castle extends ChessPiece {
    private boolean hasMoved;

    // This method is used by the calculateMoves method. It calculates the moves for one line of direction of a castle.
    private List<Move> checkLine(Board board, int colDifference, int rowDifference, Position start) {
        List<Move> moves = new ArrayList<>();
        Position endPosition = new Position(this.getPosition(), colDifference, rowDifference);

        while (endPosition.isInsideBoard() && board.getPieceAt(endPosition) == null) {
            moves.add(new RelocationMove(board.getPieceAt(start), start, endPosition));
            endPosition = new Position(endPosition, colDifference, rowDifference);
        }

        if (endPosition.isInsideBoard() && board.getPieceAt(endPosition).getPieceColor() != this.getPieceColor())
            moves.add(new AttackMove(board.getPieceAt(start), board.getPieceAt(endPosition), start, endPosition));

        return moves;
    }

    @Override
    public void calculateMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        moves.addAll(checkLine(board, 1, 0, this.getPosition()));
        moves.addAll(checkLine(board, 0, 1, this.getPosition()));
        moves.addAll(checkLine(board, -1, 0, this.getPosition()));
        moves.addAll(checkLine(board, 0, -1, this.getPosition()));

        this.setMoves(moves);
    }

    public Castle(Position position, PieceColor color, boolean hasMoved) {
        this.pieceColor = color;
        this.position = position;
        this.hasMoved = hasMoved;
    }

    public Castle(Position position, PieceColor color) {
        this.pieceColor = color;
        this.position = position;
        this.hasMoved = false;
    }

    @Override
    public ChessPiece copy() {
        return new Castle(this);
    }

    public Castle(Castle castle) {
        this.moves = new ArrayList<>(castle.moves);
        this.position = Position.copyOf(castle.position);
        this.pieceColor = castle.pieceColor;
        this.hasMoved = castle.hasMoved;
    }
}
