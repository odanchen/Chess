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
        moves.removeIf(move -> !move.isPossible(board));
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

    public static ChessPiece fromFEN(char piece, Position position) {
        switch (piece) {
            case 'P':
                return new Pawn(position, PieceColor.WHITE);
            case 'p':
                return new Pawn(position, PieceColor.BLACK);
            case 'R':
                return new Castle(position, PieceColor.WHITE);
            case 'r':
                return new Castle(position, PieceColor.BLACK);
            case 'N':
                return new Knight(position, PieceColor.WHITE);
            case 'n':
                return new Knight(position, PieceColor.BLACK);
            case 'B':
                return new Bishop(position, PieceColor.WHITE);
            case 'b':
                return new Bishop(position, PieceColor.BLACK);
            case 'Q':
                return new Queen(position, PieceColor.WHITE);
            case 'q':
                return new Queen(position, PieceColor.BLACK);
            case 'K':
                return new King(position, PieceColor.WHITE);
            case 'k':
                return new King(position, PieceColor.BLACK);
            default:
                return null;
        }
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

    public abstract String getPieceSignature();

    public boolean isWhite() {
        return pieceColor == PieceColor.WHITE;
    }

    public boolean isBlack() {
        return pieceColor == PieceColor.BLACK;
    }
}
