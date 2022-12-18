package pieces;

import board_package.Board;
import pieces.moves.AttackMove;
import pieces.moves.Move;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {
    protected final PieceColor pieceColor;
    protected Position position;

    ChessPiece(Position position, PieceColor color) {
        this.pieceColor = color;
        this.position = position;
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
     * Changes position of piece.
     *
     * @param position New position.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Calculates possible moves for a piece. Checks are not accounted for.
     *
     * @param board The current chess board.
     */
    abstract public List<Move> calculatePotentialMoves(Board board);

    private boolean isMovePossible(Move move, Board board) {
        Board copyBoard = new Board(board);
        copyBoard.makeMove(move);

        return !copyBoard.isCheck(copyBoard.getPieceAt(move.getEndPosition()).getPieceColor());
    }

    public boolean notSameColorAs(ChessPiece piece) {
        return this.getPieceColor() != piece.getPieceColor();
    }

    /**
     * Validates the movements of the piece by accounting for checks.
     *
     * @param board The current chess board.
     */

    private List<Move> validateMoves(Board board, List<Move> allMoves) {
        List<Move> moves = new ArrayList<>(allMoves);

        moves.removeIf(move -> !this.isMovePossible(move, board));

        return moves;
    }

    public boolean attacks(Position position, Board board) {
        List<Move> moves = this.calculatePotentialMoves(board);
        for (Move move : moves) {
            if (move instanceof AttackMove && ((AttackMove) move).getAttackedPosition().equals(position)) return true;
        }

        return false;
    }

    public List<Move> calculateMoves(Board board) {
        return validateMoves(board, this.calculatePotentialMoves(board));
    }
}
