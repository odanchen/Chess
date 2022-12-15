package pieces;

import BoardPackage.Board;
import pieces.moves.AttackMove;
import pieces.moves.Move;
import pieces.moves.RelocationMove;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    // This method is used by the calculateMoves method. It calculates the moves for one line of direction of a bishop.
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

        moves.addAll(checkLine(board, 1, 1, this.getPosition()));
        moves.addAll(checkLine(board, -1, 1, this.getPosition()));
        moves.addAll(checkLine(board, 1, -1, this.getPosition()));
        moves.addAll(checkLine(board, -1, -1, this.getPosition()));

        this.setMoves(moves);
    }

    public Bishop(Position position, PieceColor color) {
        this.pieceColor = color;
        this.position = position;
    }

    @Override
    public ChessPiece copy() {
        return new Bishop(this);
    }

    public Bishop(Bishop bishop) {
        this.moves = new ArrayList<>(bishop.moves);
        this.position = Position.copyOf(bishop.position);
        this.pieceColor = bishop.pieceColor;
    }
}
