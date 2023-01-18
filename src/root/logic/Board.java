package root.logic;

import root.logic.moves.*;
import root.logic.pieces.Castle;
import root.logic.pieces.ChessPiece;
import root.logic.pieces.King;
import root.logic.pieces.Pawn;
import root.logic.pieces.properties.PieceColor;
import root.logic.pieces.properties.Position;

import java.util.ArrayList;
import java.util.List;

import static root.logic.pieces.properties.PieceColor.WHITE;

public class Board {
    private final static int BOARD_SIZE = 8;
    private ChessPiece[][] configuration = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
    private Position whiteKingPosition;
    private Position blackKingPosition;
    private List<ChessPiece> whitePieces = new ArrayList<>();
    private List<ChessPiece> blackPieces = new ArrayList<>();

    public Board() {
    }

    /**
     * creates the copy of the board passed through the arguments
     *
     * @param board the instance of the board copied
     */
    public Board(Board board) {
        this.whiteKingPosition = Position.copyOf(board.getWhiteKingPosition());
        this.blackKingPosition = Position.copyOf(board.getBlackKingPosition());
        this.blackPieces = Board.copyPiecesList(board.getBlackPieces());
        this.whitePieces = Board.copyPiecesList(board.getWhitePieces());
        this.configuration = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        this.getAllPieces().forEach(piece -> setPieceAt(piece.getPosition(), piece));
    }

    private static List<ChessPiece> copyPiecesList(List<ChessPiece> originalPieces) {
        List<ChessPiece> copy = new ArrayList<>();
        originalPieces.forEach(piece -> copy.add(piece.copy()));
        return copy;
    }

    public boolean isUnderAttack(Position position, PieceColor currentSideColor) {
        List<ChessPiece> pieces = currentSideColor == WHITE ? getBlackPieces() : getWhitePieces();
        return pieces.stream().anyMatch(piece -> piece.attacks(position, this));
    }

    /**
     * @param color The side which is being checked for checks. (Black or white).
     */
    public boolean isCheck(PieceColor color) {
        Position kingPosition = color == WHITE ? getWhiteKingPosition() : getBlackKingPosition();
        return isUnderAttack(kingPosition, color);
    }

    /**
     * Checks if the game should end or not (checks for stalemate or a checkmate)
     *
     * @param currentSide this is not a real doc it was just giving warning
     * @return returns a boolean value of whether a current side could not perform any moves
     */
    public boolean isCheckmate(PieceColor currentSide) {
        List<ChessPiece> currentPieces = currentSide == WHITE ? getWhitePieces() : getBlackPieces();
        return currentPieces.stream().allMatch(piece -> piece.calculateMoves(this).isEmpty()) && isCheck(currentSide);
    }

    public boolean isStalemate(PieceColor currentSide) {
        List<ChessPiece> currentPieces = currentSide == WHITE ? getWhitePieces() : getBlackPieces();
        return currentPieces.stream().allMatch(piece -> piece.calculateMoves(this).isEmpty()) && !isCheck(currentSide);
    }

    private void handleMoveSensitivePieces(Move move) {
        if (move.getPieceAtEnd(this) instanceof Castle) {
            ((Castle) move.getPieceAtEnd(this)).setHasMoved(true);
        }
        if (move.getPieceAtEnd(this) instanceof Pawn) {
            ((Pawn) move.getPieceAtEnd(this)).setHasMoved(true);
            if (Math.abs(move.getStartPosition().getRow() - move.getEndPosition().getRow()) == 2)
                ((Pawn) move.getPieceAtEnd(this)).setHasMovedTwo(true);
            else resetPawnMovedTwo();
        }
        if (move.getPieceAtEnd(this) instanceof King) {
            ((King) move.getPieceAtEnd(this)).setHasMoved(true);
        }
    }

    private void resetPawnMovedTwo() {
        for (char x = 'a'; x < 'a' + BOARD_SIZE; x++)
            for (int y = 1; y <= BOARD_SIZE; y++)
                if (this.getPieceAt(new Position(x, y)) instanceof Pawn)
                    ((Pawn) this.getPieceAt(new Position(x, y))).setHasMovedTwo(false);
    }

    private void handleKingMove(Move move) {
        if (move.getPieceAtEnd(this) instanceof King) {
            if (move.getPieceAtEnd(this).isWhite()) setWhiteKingPosition(move.getEndPosition());
            else setBlackKingPosition(move.getEndPosition());
        }
    }

    private void makeRelocationMove(Move move) {
        setPieceAt(move.getEndPosition(), move.getPieceAtStart(this));
        setPieceAt(move.getStartPosition(), null);
        move.getPieceAtEnd(this).setPosition(move.getEndPosition());

        handleMoveSensitivePieces(move);
        handleKingMove(move);
    }

    private void makeAttackMove(AttackMove move) {
        if (move.getAttackedPiece(this).isBlack()) {
            blackPieces.remove(move.getAttackedPiece(this));
        } else {
            whitePieces.remove(move.getAttackedPiece(this));
        }
        makeRelocationMove(move);
    }

    private void makeCastlingMove(CastlingMove move) {
        makeRelocationMove(move.getCastleMove());
        makeRelocationMove(move.getKingMove());
    }

    private void makePromotionMove(PromotionMove move) {
        makeRelocationMove(move);
        replacePiece(move.getNewPiece());
    }

    private void makePromotionAttackMove(PromotionAttackMove move) {
        makeAttackMove(move);
        replacePiece(move.getNewPiece());
    }

    /**
     * mutates the current state of the board to the state after the move have been performed
     *
     * @param move the move to be performed
     */
    public void makeMove(Move move) {
        if (move instanceof PromotionMove) {
            makePromotionMove((PromotionMove) move);
        } else if (move instanceof PromotionAttackMove) {
            makePromotionAttackMove((PromotionAttackMove) move);
        } else if (move instanceof RelocationMove) {
            makeRelocationMove(move);
        } else if (move instanceof AttackMove) {
            makeAttackMove((AttackMove) move);
        } else if (move instanceof CastlingMove) {
            makeCastlingMove((CastlingMove) move);
        }

    }

    /**
     * @param position Position on the board.
     * @return The piece at the specified position.
     */
    public ChessPiece getPieceAt(Position position) {
        int matrixRow = position.rowToIdx();
        int matrixCol = position.colToIdx();
        if (matrixRow < 0 || matrixRow >= BOARD_SIZE || matrixCol < 0 || matrixCol >= BOARD_SIZE) {
            return null;
        }
        return configuration[matrixRow][matrixCol];
    }

    /**
     * @param position Position on the board.
     */
    public void setPieceAt(Position position, ChessPiece piece) {
        int matrixRow = position.rowToIdx();
        int matrixCol = position.colToIdx();
        configuration[matrixRow][matrixCol] = piece;
    }

    /**
     * Gets the position of the white king on the board
     *
     * @return the position of the white king on the board
     */
    public Position getWhiteKingPosition() {
        return whiteKingPosition;
    }

    public void setWhiteKingPosition(Position whiteKingPosition) {
        this.whiteKingPosition = whiteKingPosition;
    }

    /**
     * gets the position of the black king on the board
     *
     * @return the position of the black king on the board
     */

    public Position getBlackKingPosition() {
        return blackKingPosition;
    }

    public void setBlackKingPosition(Position blackKingPosition) {
        this.blackKingPosition = blackKingPosition;
    }

    /**
     * gets all the white pieces on the board
     *
     * @return all white pieces present on the board
     */

    public List<ChessPiece> getWhitePieces() {
        return whitePieces;
    }

    /**
     * gets all the black pieces on the board
     *
     * @return all black pieces present on the board
     */

    public List<ChessPiece> getBlackPieces() {
        return blackPieces;
    }

    /**
     * Gets all the pieces on the board
     *
     * @return a list of all pieces present on the board
     */

    public List<ChessPiece> getAllPieces() {
        List<ChessPiece> ans = new ArrayList<>(getWhitePieces());
        ans.addAll(getBlackPieces());
        return ans;
    }

    private void addWhitePiece(ChessPiece piece) {
        whitePieces.add(piece);
    }

    private void addBlackPiece(ChessPiece piece) {
        blackPieces.add(piece);
    }

    public boolean isNotEmptyAt(Position position) {
        return getPieceAt(position) != null;
    }

    public boolean isEmptyAt(Position position) {
        return getPieceAt(position) == null;
    }

    private void replacePiece(ChessPiece newPiece) {
        if (getPieceAt(newPiece.getPosition()).isWhite()) whitePieces.remove(getPieceAt(newPiece.getPosition()));
        else blackPieces.remove(getPieceAt(newPiece.getPosition()));
        addPiece(newPiece);
    }

    private void addPiece(ChessPiece piece) {
        setPieceAt(piece.getPosition(), piece);

        if (piece.isWhite()) {
            addWhitePiece(piece);
        } else {
            addBlackPiece(piece);
        }

        if (piece instanceof King) {
            if (piece.isWhite()) setWhiteKingPosition(piece.getPosition());
            else setBlackKingPosition(piece.getPosition());
        }
    }

    public void fromFEN(String fen) {
        String[] fenParts = fen.split(" ");
        String[] fenRows = fenParts[0].split("/");
        for (int i = 0; i < fenRows.length; i++) {
            int col = 0;
            for (int j = 0; j < fenRows[i].length(); j++) {
                char c = fenRows[i].charAt(j);
                if (Character.isDigit(c)) {
                    col += Character.getNumericValue(c);
                } else {
                    Position position = new Position((char) ('a' + col), BOARD_SIZE - i);
                    ChessPiece piece = ChessPiece.fromFEN(c, position);
                    addPiece(piece);
                    col++;
                }
            }
        }

    }

    /**
     * Fills board object with standard pieces and piece positions.
     */
    public void fillStandardBoard() {
        this.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
    }
}