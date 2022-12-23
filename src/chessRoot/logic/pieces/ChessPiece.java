package chessRoot.logic.pieces;

import chessRoot.logic.Board;
import chessRoot.logic.moves.AttackMove;
import chessRoot.logic.moves.Move;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {
    protected final PieceColor pieceColor;
    protected Position position;

    /**
     * constructor of the chess piece with the given color and position
     *
     * @param position the position of the new piece
     * @param color    the color of the new piece
     */
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
        return position;
    }

    /**
     * @return The color of the piece (black or white).
     */
    public PieceColor getPieceColor() {
        return pieceColor;
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

    /**
     * checks if the move is possible to perform with the current configuration of the board
     *
     * @param move  the move to be checked
     * @param board the current configuration of the board
     * @return a boolean value, representing whether the move is possible
     */
    private boolean isMovePossible(Move move, Board board) {
        Board copyBoard = new Board(board);
        copyBoard.makeMove(move);
        return !copyBoard.isCheck(copyBoard.getPieceAt(move.getEndPosition()).getPieceColor());
    }

    /**
     * checks if the current piece is different in color from the piece passed through parameters
     *
     * @param piece the piece to compare colors with
     * @return a boolean value representing whether the colors of the pieces are different
     */
    public boolean differentColorFrom(ChessPiece piece) {
        return getPieceColor() != piece.getPieceColor();
    }

    /**
     * Validates the movements of the piece by accounting for checks.
     *
     * @param board The current chess board.
     */
    private List<Move> validateMoves(Board board, List<Move> allMoves) {
        List<Move> moves = new ArrayList<>(allMoves);
        moves.removeIf(move -> !isMovePossible(move, board));
        return moves;
    }

    /**
     * checks if the current piece has a given position in its potential moves
     *
     * @param position the checked position
     * @param board    the current configuration of the board
     * @return a boolean value which represents whether the current position could be attacked
     */
    public boolean attacks(Position position, Board board) {
        List<Move> moves = calculatePotentialMoves(board);
        return moves.stream().anyMatch(move -> move instanceof AttackMove && ((AttackMove) move).isAttackedPosition(position));
    }

    /**
     * returns the list of moves which are actually possible to perform
     *
     * @param board the current configuration of the board
     * @return a list of Move class objects, which are actually possible
     */
    public List<Move> calculateMoves(Board board) {
        return validateMoves(board, calculatePotentialMoves(board));
    }

    public abstract String getPieceLetter();

    public boolean isWhite() {
        return pieceColor == PieceColor.WHITE;
    }

    public boolean isBlack() {
        return pieceColor == PieceColor.BLACK;
    }
}