package logic.pieces;

import logic.Board;
import logic.moves.AttackMove;
import logic.moves.Move;
import logic.moves.RelocationMove;
import logic.pieces.properties.PieceColor;
import logic.pieces.properties.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    /**
     * creates a new bishop at the given position
     *
     * @param position - position of the new bishop
     * @param color    - color of the new bishop
     */
    public Bishop(Position position, PieceColor color) {
        super(position, color);
    }

    /**
     * A copy constructor
     *
     * @param bishop - the instance of a bishop, the identical copy of which would be created
     */
    public Bishop(Bishop bishop) {
        super(Position.copyOf(bishop.getPosition()), bishop.getPieceColor());
    }

    private List<Move> checkLine(Position startPosition, int colDifference, int rowDifference, Board board) {
        List<Move> moves = new ArrayList<>();
        Position endPosition = new Position(getPosition(), colDifference, rowDifference);

        while (endPosition.insideBoard() && board.isEmptyAt(endPosition)) {
            moves.add(new RelocationMove(startPosition, endPosition));
            endPosition = new Position(endPosition, colDifference, rowDifference);
        }

        if (endPosition.insideBoard() && differentColorFrom(board.getPieceAt(endPosition)))
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

    @Override
    public ChessPiece copy() {
        return new Bishop(this);
    }

    @Override
    public String getPieceSignature() {
        return pieceColor.getColorSign() + "b";
    }

    @Override
    public String getNotationSignature() {
        return "B";
    }
}
