package logic.pieces;

import logic.Board;
import logic.moves.AttackMove;
import logic.moves.Move;
import logic.moves.RelocationMove;
import logic.pieces.properties.PieceColor;
import logic.pieces.properties.Position;

import java.util.ArrayList;
import java.util.List;

public class Castle extends ChessPiece {
    private boolean hasMoved;

    /**
     * creates a new rook at the given position
     *
     * @param position - position of the new rook
     * @param color    - color of the new rook
     * @param hasMoved - information if the rook has already moved. Used for castling rules
     */
    public Castle(Position position, PieceColor color, boolean hasMoved) {
        super(position, color);
        this.hasMoved = hasMoved;
    }

    /**
     * creates a new rook at the given position, that hasn't moved yet
     *
     * @param position - position of the new rook
     * @param color    - color of the new rook
     */
    public Castle(Position position, PieceColor color) {
        super(position, color);
        this.hasMoved = false;
    }

    /**
     * A copy constructor
     *
     * @param castle - the instance of a rook, the identical copy of which would be created
     */
    public Castle(Castle castle) {
        super(Position.copyOf(castle.getPosition()), castle.getPieceColor());
        this.hasMoved = castle.getHasMoved();
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

        moves.addAll(checkLine(getPosition(), 0, 1, board));
        moves.addAll(checkLine(getPosition(), 1, 0, board));
        moves.addAll(checkLine(getPosition(), 0, -1, board));
        moves.addAll(checkLine(getPosition(), -1, 0, board));

        return moves;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public ChessPiece copy() {
        return new Castle(this);
    }

    @Override
    public String getPieceSignature() {
        return pieceColor.getColorSign() + "r";
    }

    @Override
    public String getNotationSignature() {
        return "R";
    }
}
