package BoardPackage;

import pieces.*;

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

    public boolean isCheck(PieceColor color) {
        Position kingPosition = (color == PieceColor.WHITE) ? this.whiteKing : this.blackKing;
        List<ChessPiece> pieces = (color == PieceColor.WHITE) ? this.blackPieces : this.whitePieces;

        for (ChessPiece piece : pieces) {
            piece.calculateMoves(this);
            if (piece.getMoves().contains(kingPosition)) return true;
        }

        return false;
    }

    public Board makeMove(ChessPiece piece, Position finish) {
        Board ans = new Board(this);

        if (ans.getPieceAt(finish) != null) {
            if (piece.getPieceColor() == PieceColor.WHITE) ans.blackPieces.remove(ans.getPieceAt(finish));
            else ans.whitePieces.remove(ans.getPieceAt(finish));
        }

        ans.setPieceAt(finish, piece);
        ans.setPieceAt(piece.getPosition(), null);
        piece.setPosition(finish);


        if (piece instanceof King) {
            if (piece.getPieceColor() == PieceColor.WHITE) ans.whiteKing = finish;
            else ans.blackKing = finish;
        }

        return ans;
    }

    public ChessPiece getPieceAt(Position position) {
        int matrixRow = Math.abs(position.getRow() - BOARD_SIZE);
        int matrixCol = (int) position.getCol() - 'a';
        return configuration[matrixRow][matrixCol];
    }

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

    public void fillStandardBoard() {
        this.setPieceAt(Position.at("a1"), new Castle(Position.at("a1"), WHITE));
        this.setPieceAt(Position.at("a8"), new Castle(Position.at("a8"), BLACK));

        this.setPieceAt(Position.at("b1"), new Knight(Position.at("b1"), WHITE));
        this.setPieceAt(Position.at("b8"), new Knight(Position.at("b8"), BLACK));

        this.setPieceAt(Position.at("c1"), new Bishop(Position.at("c1"), WHITE));
        this.setPieceAt(Position.at("c8"), new Bishop(Position.at("c8"), BLACK));

        this.setPieceAt(Position.at("d1"), new Queen(Position.at("d1"), WHITE));
        this.setPieceAt(Position.at("d8"), new Queen(Position.at("d8"), BLACK));

        this.setPieceAt(Position.at("e1"), new King(Position.at("e1"), WHITE));
        this.setPieceAt(Position.at("e8"), new King(Position.at("e8"), BLACK));
        this.whiteKing = Position.at("e1");
        this.blackKing = Position.at("e8");

        this.setPieceAt(Position.at("f1"), new Bishop(Position.at("f1"), WHITE));
        this.setPieceAt(Position.at("f8"), new Bishop(Position.at("f8"), BLACK));

        this.setPieceAt(Position.at("g1"), new Knight(Position.at("g1"), WHITE));
        this.setPieceAt(Position.at("g8"), new Knight(Position.at("g8"), BLACK));

        this.setPieceAt(Position.at("h1"), new Castle(Position.at("h1"), WHITE));
        this.setPieceAt(Position.at("h8"), new Castle(Position.at("h8"), BLACK));

        for (char i = 'a'; i <= 'h'; i++) {
            this.setPieceAt(new Position(i, 2), new Pawn(new Position(i, 2), WHITE));
            this.setPieceAt(new Position(i, 7), new Pawn(new Position(i, 7), BLACK));
        }
    }
}
