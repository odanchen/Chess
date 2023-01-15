package root.logic.pieces;

import root.logic.Board;
import root.logic.moves.AttackMove;
import root.logic.moves.Move;
import root.logic.pieces.properties.PieceColor;
import root.logic.pieces.properties.Position;

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

    public static ChessPiece fromFEN(char piece, Position position) {
        PieceColor newPieceColor = Character.isUpperCase(piece) ? PieceColor.WHITE : PieceColor.BLACK;
        switch (Character.toUpperCase(piece)) {
            case 'P':
                return new Pawn(position, newPieceColor);
            case 'R':
                return new Castle(position, newPieceColor);
            case 'N':
                return new Knight(position, newPieceColor);
            case 'B':
                return new Bishop(position, newPieceColor);
            case 'Q':
                return new Queen(position, newPieceColor);
            case 'K':
                return new King(position, newPieceColor);
            default:
                return null;
        }
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
     * Changes position of piece.
     *
     * @param position New position.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @return The color of the piece (black or white).
     */
    public PieceColor getPieceColor() {
        return pieceColor;
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

    public abstract String getNotationSignature();

    public boolean isWhite() {
        return pieceColor == PieceColor.WHITE;
    }

    public boolean isBlack() {
        return pieceColor == PieceColor.BLACK;
    }
}
