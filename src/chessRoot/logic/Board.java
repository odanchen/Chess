package chessRoot.logic;

import chessRoot.logic.moves.AttackMove;
import chessRoot.logic.moves.Move;
import chessRoot.logic.moves.RelocationMove;
import chessRoot.logic.pieces.*;

import java.util.ArrayList;
import java.util.List;

import static chessRoot.logic.pieces.PieceColor.BLACK;
import static chessRoot.logic.pieces.PieceColor.WHITE;

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
     * @param color The side which is being checked for checks. (Black or white).
     */
    public boolean isCheck(PieceColor color) {
        Position kingPosition = color == WHITE ? getWhiteKingPosition() : getBlackKingPosition();
        List<ChessPiece> pieces = color == WHITE ? getBlackPieces() : getWhitePieces();
        return pieces.stream().anyMatch(piece -> piece.attacks(kingPosition, this));
    }

    /**
     * Checks if the game should end or not (checks for stalemate or a checkmate)
     *
     * @param currentSide this is not a real doc it was just giving warning
     * @return returns a boolean value of whether a current side could not perform any moves
     */
    public boolean isMate(PieceColor currentSide) {
        List<ChessPiece> currentPieces = currentSide == WHITE ? getWhitePieces() : getBlackPieces();
        return currentPieces.stream().allMatch(piece -> piece.calculateMoves(this).isEmpty());
    }

    private void makeRelocationMove(Move move) {
        setPieceAt(move.getEndPosition(), move.getPieceAtStart(this));
        setPieceAt(move.getStartPosition(), null);
        move.getPieceAtEnd(this).setPosition(move.getEndPosition());

        if (move.getPieceAtEnd(this) instanceof King) {
            ((King) move.getPieceAtEnd(this)).setHasMoved(true);

            if (move.getPieceAtEnd(this).isWhite())
                setWhiteKingPosition(move.getEndPosition());
            else setBlackKingPosition(move.getEndPosition());
        }
        if (move.getPieceAtEnd(this) instanceof Castle) {
            ((Castle) move.getPieceAtEnd(this)).setHasMoved(true);
        }
        if (move.getPieceAtEnd(this) instanceof Pawn) {
            ((Pawn) move.getPieceAtEnd(this)).setHasMoved(true);
        }
    }

    private void makeAttackMove(AttackMove move) {
        if (move.getAttackedPiece(this).isBlack()) {
            blackPieces.remove(move.getAttackedPiece(this));
        } else {
            whitePieces.remove(move.getAttackedPiece(this));
        }
        makeRelocationMove(move);
    }

    /**
     * mutates the current state of the board to the state after the move have been performed
     *
     * @param move the move to be performed
     */
    public void makeMove(Move move) {
        if (move instanceof RelocationMove) {
            makeRelocationMove(move);
        } else if (move instanceof AttackMove) {
            makeAttackMove((AttackMove) move);
        }
    }

    /**
     * @param position Position on the board.
     * @return The piece at the specified position.
     */
    public ChessPiece getPieceAt(Position position) {
        int matrixRow = position.rowToIdx();
        int matrixCol = position.colToIdx();
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

    public void setBlackKingPosition(Position blackKingPosition) {
        this.blackKingPosition = blackKingPosition;
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

    /**
     * Fills board object with standard pieces and piece positions.
     */
    public void fillStandardBoard() {
        addPiece(new Castle(Position.at("a1"), WHITE));
        addPiece(new Castle(Position.at("a8"), BLACK));

        addPiece(new Knight(Position.at("b1"), WHITE));
        addPiece(new Knight(Position.at("b8"), BLACK));

        addPiece(new Bishop(Position.at("c1"), WHITE));
        addPiece(new Bishop(Position.at("c8"), BLACK));

        addPiece(new Queen(Position.at("d1"), WHITE));
        addPiece(new Queen(Position.at("d8"), BLACK));

        addPiece(new King(Position.at("e1"), WHITE));
        addPiece(new King(Position.at("e8"), BLACK));

        addPiece(new Bishop(Position.at("f1"), WHITE));
        addPiece(new Bishop(Position.at("f8"), BLACK));

        addPiece(new Knight(Position.at("g1"), WHITE));
        addPiece(new Knight(Position.at("g8"), BLACK));

        addPiece(new Castle(Position.at("h1"), WHITE));
        addPiece(new Castle(Position.at("h8"), BLACK));

        for (char i = 'a'; i <= 'h'; i++) {
            addPiece(new Pawn(new Position(i, 2), WHITE));
            addPiece(new Pawn(new Position(i, 7), BLACK));
        }
    }

    private static List<ChessPiece> copyPiecesList(List<ChessPiece> originalPieces) {
        List<ChessPiece> copy = new ArrayList<>();
        originalPieces.forEach(piece -> copy.add(piece.copy()));
        return copy;
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
}