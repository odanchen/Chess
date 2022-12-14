package BoardPackage;

import pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final static int BOARD_SIZE = 8;
    ColorPair boardColor = BoardColors.OPTION1;
    ChessPiece[][] configuration = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
    Position whiteKing;
    Position blackKing;
    List<ChessPiece> whitePieces = new ArrayList<>();
    List<ChessPiece> blackPieces = new ArrayList<>();
    public boolean isCheck(PieceColor color) {
        Position kingPosition = (color == PieceColor.WHITE) ? this.whiteKing : this.blackKing;
        List<ChessPiece> pieces = (color == PieceColor.WHITE) ? this.blackPieces : this.whitePieces;

        for (ChessPiece piece : pieces) {
            piece.calculateMoves(this);
            if (piece.getMoves().contains(kingPosition)) return true;
        }

        return false;
    }
    public Board makeMove(Position start, Position finish)
    {
        Board ans = this;

        if (ans.getPieceAt(finish) != null)
        {
            if (ans.getPieceAt(start).getPieceColor() == PieceColor.WHITE) ans.blackPieces.remove(ans.getPieceAt(finish));
            else ans.whitePieces.remove(ans.getPieceAt(finish));
        }

        ans.setPieceAt(finish, this.getPieceAt(start));
        ans.setPieceAt(start, null);
        ans.getPieceAt(finish).setPosition(finish);

        if (ans.getPieceAt(finish) instanceof King)
        {
            if (ans.getPieceAt(finish).getPieceColor() == PieceColor.WHITE) ans.whiteKing = finish;
            else ans.blackKing = finish;
        }

        return ans;
    }
    public ChessPiece getPieceAt(Position position)
    {
        int matrixRow = Math.abs(position.getRow() - BOARD_SIZE);
        int matrixCol = (int) position.getCol() - 'a';
        return configuration[matrixRow][matrixCol];
    }
    public ChessPiece getPieceAt(char letter, int digit)
    {
        int matrixRow = Math.abs(digit - BOARD_SIZE);
        int matrixCol = (int) letter - 'a';
        return configuration[matrixRow][matrixCol];
    }
    public void setPieceAt(Position position, ChessPiece piece)
    {
        int matrixRow = Math.abs(position.getRow() - BOARD_SIZE);
        int matrixCol = (int) position.getCol() - 'a';
        configuration[matrixRow][matrixCol] = piece;
    }
    public void fillTestConfiguration()
    {
        this.setPieceAt(new Position('e', 7), new Bishop(new Position('e', 7), PieceColor.BLACK));
    }
    public void fillStandardBoard()
    {
        this.setPieceAt(new Position('a', 1), new Castle(new Position('a', 1), PieceColor.WHITE));
        this.setPieceAt(new Position('a', 8), new Castle(new Position('a', 8), PieceColor.BLACK));

        this.setPieceAt(new Position('b', 1), new Knight(new Position('b', 1), PieceColor.WHITE));
        this.setPieceAt(new Position('b', 8), new Knight(new Position('b', 8), PieceColor.BLACK));

        this.setPieceAt(new Position('c', 1), new Bishop(new Position('c', 1), PieceColor.WHITE));
        this.setPieceAt(new Position('c', 8), new Bishop(new Position('c', 8), PieceColor.BLACK));

        this.setPieceAt(new Position('d', 1), new Queen(new Position('d', 1), PieceColor.WHITE));
        this.setPieceAt(new Position('d', 8), new Queen(new Position('d', 8), PieceColor.BLACK));

        this.setPieceAt(new Position('e', 1), new King(new Position('e', 1), PieceColor.WHITE));
        this.setPieceAt(new Position('e', 8), new King(new Position('e', 8), PieceColor.BLACK));
        this.whiteKing = new Position('e', 1);
        this.blackKing = new Position('e', 8);

        this.setPieceAt(new Position('f', 1), new Bishop(new Position('f', 1), PieceColor.WHITE));
        this.setPieceAt(new Position('f', 8), new Bishop(new Position('f', 8), PieceColor.BLACK));

        this.setPieceAt(new Position('g', 1), new Knight(new Position('g', 1), PieceColor.WHITE));
        this.setPieceAt(new Position('g', 8), new Knight(new Position('g', 8), PieceColor.BLACK));

        this.setPieceAt(new Position('h', 1), new Castle(new Position('h', 1), PieceColor.WHITE));
        this.setPieceAt(new Position('h', 8), new Castle(new Position('h', 8), PieceColor.BLACK));

        for (char i = 'a'; i <= 'h'; i++)
        {
            this.setPieceAt(new Position(i, 2), new Pawn(new Position(i, 2), PieceColor.WHITE));
            this.setPieceAt(new Position(i, 7), new Pawn(new Position(i, 7), PieceColor.BLACK));
        }
    }
}
