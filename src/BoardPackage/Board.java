package BoardPackage;

import assets.board_colors.BoardColors;
import assets.board_colors.ColorPair;
import pieces.*;
import pieces.moves.AttackMove;
import pieces.moves.CastlingMove;
import pieces.moves.Move;

import java.util.ArrayList;
import java.util.List;

import static pieces.PieceColor.WHITE;
import static pieces.PieceColor.BLACK;

public class Board {
    private final static int BOARD_SIZE = 8;
    ColorPair boardColor = BoardColors.OPTION1;
    ChessPiece[][] configuration = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
    Position whiteKing;
    Position blackKing;
    List<ChessPiece> whitePieces = new ArrayList<>();
    List<ChessPiece> blackPieces = new ArrayList<>();

    public Board() {
    }

    /**
     * @param color The side which is being checked for checks. (Black or white).
     */
    public boolean isCheck(PieceColor color) {
        Position kingPosition = (color == PieceColor.WHITE) ? this.whiteKing : this.blackKing;
        List<ChessPiece> pieces = (color == PieceColor.WHITE) ? this.blackPieces : this.whitePieces;

        for (ChessPiece piece : pieces) {
            piece.calculateMoves(this);
            if (piece.attacks(kingPosition)) return true;
        }

        return false;
    }

    public Board makeTestMove(Move move) {
        Board boardCopy = new Board(this);

        if (move instanceof CastlingMove) {
            return boardCopy;
            // IMPLEMEEEEENT
        } else {
            if (move instanceof AttackMove) {
                if (((AttackMove) move).getAttackedPiece().getPieceColor() == WHITE)
                    boardCopy.whitePieces.remove(((AttackMove) move).getAttackedPiece());
                else boardCopy.blackPieces.remove(((AttackMove) move).getAttackedPiece());
            }

            boardCopy.setPieceAt(move.getStartPosition(), null);
            boardCopy.setPieceAt(move.getEndPosition(), move.getStartPiece());

            if (move.getStartPiece() instanceof King) {
                if (move.getStartPiece().getPieceColor() == PieceColor.WHITE)
                    boardCopy.whiteKing = move.getEndPosition();
                else boardCopy.blackKing = move.getEndPosition();
            }
        }

        return boardCopy;
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
     * @param digit The corresponding chess position for rows. (1,2,3...8)
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

    public Board(Board board) {
        this.whiteKing = Position.copyOf(board.whiteKing);
        this.blackKing = Position.copyOf(board.blackKing);
        this.blackPieces = new ArrayList<>(board.blackPieces);
        this.whitePieces = new ArrayList<>(board.whitePieces);

        for (int i = 0; i < BOARD_SIZE; i++) {
            this.configuration[i] = board.configuration[i].clone();
        }
    }

    public void fillTestConfiguration() {
        this.setPieceAt(new Position('e', 7), new Bishop(new Position('e', 7), BLACK));
    }

    private void addPiece(ChessPiece piece) {
        this.setPieceAt(piece.getPosition(), piece);

        if (piece.getPieceColor() == WHITE) this.whitePieces.add(piece);
        else this.blackPieces.add(piece);

        if (piece instanceof King) {
            if (piece.getPieceColor() == WHITE) this.whiteKing = piece.getPosition();
            else this.blackKing = piece.getPosition();
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

        this.setPieceAt(Position.at("e1"), new King(Position.at("e1"), WHITE));
        this.setPieceAt(Position.at("e8"), new King(Position.at("e8"), BLACK));

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
}
