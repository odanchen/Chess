package pieces;

import BoardPackage.Board;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {
    protected PieceColor pieceColor;
    protected Position position;
    protected List<Position> moves;

    /**
     * @return The list of possible moves for the piece.
     */
    public List<Position> getMoves() {
        return this.moves;
    }

    /**
     * Changes the stored moves of the piece.
     * @param moves The new moves list.
     */
    public void setMoves(List<Position> moves) {
        this.moves = moves;
    }

    /**
     * @return A replica of the chess piece.
     */
    abstract public ChessPiece copy();

    /**
     * @return The position of the piece.
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * @return The color of the piece (black or white).
     */
    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    /**
     * Sets piece color (black or white).
     * @param color New color.
     */
    public void setPieceColor(PieceColor color) {
        this.pieceColor = color;
    }

    /**
     * Changes position of piece.
     * @param position New position.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Calculates possible moves for a piece. Checks are not accounted for.
     * @param board The current chess board.
     */
    abstract public void calculateMoves(Board board);

    /**
     * Validates the movements of the piece by accounting for checks.
     * @param board The current chess board.
     */
    public void validateMoves(Board board) {
        List<Position> moves = new ArrayList<>(this.getMoves());
        for (Position pos : moves) {
            Board tempBoard = board.makeMove(this.copy(), pos);
            if (tempBoard.isCheck(this.getPieceColor())) moves.remove(pos);
        }
        this.setMoves(moves);
    }

}
