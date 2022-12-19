package board_package;

import pieces.*;
import pieces.moves.AttackMove;
import pieces.moves.Move;
import pieces.moves.RelocationMove;

import java.util.ArrayList;
import java.util.List;

import static pieces.PieceColor.WHITE;
import static pieces.PieceColor.BLACK;

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
        Position kingPosition = (color == PieceColor.WHITE) ? this.getWhiteKingPosition() : this.getBlackKingPosition();
        List<ChessPiece> pieces = (color == PieceColor.WHITE) ? this.getBlackPieces() : this.getWhitePieces();

        for (ChessPiece piece : pieces) {
            if (piece.attacks(kingPosition, this)) return true;
        }

        return false;
    }

    /**
     * Checks if the game should end or not (checks for stalemate or a checkmate)
     *
     * @param currentSide this is not a real doc it was just giving warning
     * @return returns a boolean value of whether a current side could not perform any moves
     */
    public boolean isMate(PieceColor currentSide) {
        List<ChessPiece> currentPieces = (currentSide == PieceColor.WHITE) ? this.getWhitePieces() : this.getBlackPieces();

        for (ChessPiece piece : currentPieces) if (!piece.calculateMoves(this).isEmpty()) return false;
        return true;
    }

    private void makeRelocationMove(Move move) {
        this.setPieceAt(move.getEndPosition(), this.getPieceAt(move.getEndPosition()));
        this.setPieceAt(move.getStartPosition(), null);
        this.getPieceAt(move.getEndPosition()).setPosition(move.getEndPosition());

        if (this.getPieceAt(move.getEndPosition()) instanceof King) {
            ((King) this.getPieceAt(move.getEndPosition())).setHasMoved(true);
        }
        if (this.getPieceAt(move.getEndPosition()) instanceof Castle) {
            ((Castle) this.getPieceAt(move.getEndPosition())).setHasMoved(true);
        }
        if (this.getPieceAt(move.getEndPosition()) instanceof Pawn) {
            ((Pawn) this.getPieceAt(move.getEndPosition())).setHasMoved(true);
        }
    }

    private void makeAttackMove(AttackMove move) {
        if (this.getPieceAt(move.getStartPosition()).getPieceColor() == WHITE) {
            this.blackPieces.remove(this.getPieceAt(move.getAttackedPosition()));
            this.makeRelocationMove(move);
        } else {
            this.whitePieces.remove(this.getPieceAt(move.getAttackedPosition()));
            this.makeRelocationMove(move);
        }
    }

    /**
     * mutates the current state of the board to the state after the move have been performed
     *
     * @param move the move to be performed
     */

    public void makeMove(Move move) {
        if (move instanceof RelocationMove) {
            this.makeRelocationMove(move);
        } else if (move instanceof AttackMove) {
            this.makeAttackMove((AttackMove) move);
        }
    }

    /**
     * @param position Position on the board.
     * @return The piece at the specified position.
     */
    public ChessPiece getPieceAt(Position position) {
        int matrixRow = Math.abs(position.getRow() - BOARD_SIZE);
        int matrixCol = (int) position.getCol() - 'a';
        return configuration[matrixRow][matrixCol];
    }

    /**
     * @param letter The corresponding chess position for columns. (a,b,c...h)
     * @param digit  The corresponding chess position for rows. (1,2,3...8)
     * @return The piece at the specified position.
     */
    public ChessPiece getPieceAt(char letter, int digit) {
        int matrixRow = Math.abs(digit - BOARD_SIZE);
        int matrixCol = (int) letter - 'a';
        return configuration[matrixRow][matrixCol];
    }

    public void setPieceAt(Position position, ChessPiece piece) {
        int matrixRow = Math.abs(position.getRow() - BOARD_SIZE);
        int matrixCol = (int) position.getCol() - 'a';
        this.configuration[matrixRow][matrixCol] = piece;
    }

    public void setPieceAt(char letter, int digit, ChessPiece piece) {
        int matrixRow = Math.abs(digit - BOARD_SIZE);
        int matrixCol = (int) letter - 'a';
        this.configuration[matrixRow][matrixCol] = piece;
    }

    /**
     * gets the position of the white king on the board
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
        List<ChessPiece> ans = new ArrayList<>(this.getWhitePieces());
        ans.addAll(this.getBlackPieces());
        return ans;
    }

    public void setBlackKingPosition(Position blackKingPosition) {
        this.blackKingPosition = blackKingPosition;
    }

    void addWhitePiece(ChessPiece piece) {
        this.whitePieces.add(piece);
    }

    void addBlackPiece(ChessPiece piece) {
        this.blackPieces.add(piece);
    }

    private void addPiece(ChessPiece piece) {
        this.setPieceAt(piece.getPosition(), piece);

        if (piece.getPieceColor() == WHITE) this.addWhitePiece(piece);
        else this.addBlackPiece(piece);

        if (piece instanceof King) {
            if (piece.getPieceColor() == WHITE) this.setWhiteKingPosition(piece.getPosition());
            else this.setBlackKingPosition(piece.getPosition());
        }
    }

    /**
     * Fills board object with standard pieces and piece positions.
     */
    public void fillStandardBoard() {
        this.addPiece(new Castle(Position.at("a1"), WHITE));
        this.addPiece(new Castle(Position.at("a8"), BLACK));

        this.addPiece(new Knight(Position.at("b1"), WHITE));
        this.addPiece(new Knight(Position.at("b8"), BLACK));

        this.addPiece(new Bishop(Position.at("c1"), WHITE));
        this.addPiece(new Bishop(Position.at("c8"), BLACK));

        this.addPiece(new Queen(Position.at("d1"), WHITE));
        this.addPiece(new Queen(Position.at("d8"), BLACK));

        this.addPiece(new King(Position.at("e1"), WHITE));
        this.addPiece(new King(Position.at("e8"), BLACK));

        this.addPiece(new Bishop(Position.at("f1"), WHITE));
        this.addPiece(new Bishop(Position.at("f8"), BLACK));

        this.addPiece(new Knight(Position.at("g1"), WHITE));
        this.addPiece(new Knight(Position.at("g8"), BLACK));

        this.addPiece(new Castle(Position.at("h1"), WHITE));
        this.addPiece(new Castle(Position.at("h8"), BLACK));

        for (char i = 'a'; i <= 'h'; i++) {
            this.addPiece(new Pawn(new Position(i, 2), WHITE));
            this.addPiece(new Pawn(new Position(i, 7), BLACK));
        }
    }

    private static List<ChessPiece> copyPiecesList(List<ChessPiece> originalPieces) {
        List<ChessPiece> copy = new ArrayList<>();
        for (ChessPiece piece : originalPieces) copy.add(piece.copy());

        return copy;
    }

    /**
     * creates the copy of the board passed through the arguments
     * @param board the instance of the board copied
     */

    public Board(Board board) {
        this.whiteKingPosition = Position.copyOf(board.getWhiteKingPosition());
        this.blackKingPosition = Position.copyOf(board.getBlackKingPosition());
        this.blackPieces = Board.copyPiecesList(board.getBlackPieces());
        this.whitePieces = Board.copyPiecesList(board.getWhitePieces());

        this.configuration = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        for (ChessPiece piece : this.getBlackPieces()) this.setPieceAt(piece.getPosition(), piece);
        for (ChessPiece piece : this.getWhitePieces()) this.setPieceAt(piece.getPosition(), piece);
    }
}
