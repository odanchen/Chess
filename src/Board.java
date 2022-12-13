import pieces.*;

public class Board {
    private final int BOARD_SIZE = 8;

    ColorPair boardColor = BoardColors.OPTION1;

    ChessPiece[][] configuration = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
    public ChessPiece getPieceAt(Coordinate coordinate)
    {
        int matrixRow = Math.abs(coordinate.getRow() - BOARD_SIZE);
        int matrixCol = (int) coordinate.getCol() - 'a';
        return configuration[matrixRow][matrixCol];
    }
    public ChessPiece getPieceAt(char letter, int digit)
    {
        int matrixRow = Math.abs(digit - BOARD_SIZE);
        int matrixCol = (int) letter - 'a';
        return configuration[matrixRow][matrixCol];
    }
    public void setPieceAt(Coordinate coordinate, ChessPiece piece)
    {
        int matrixRow = Math.abs(coordinate.getRow() - BOARD_SIZE);
        int matrixCol = (int) coordinate.getCol() - 'a';
        configuration[matrixRow][matrixCol] = piece;
    }
    public void fillStandardBoard()
    {
        this.setPieceAt(new Coordinate('a', 1), new Castle(new Coordinate('a', 1), 'W'));
        this.setPieceAt(new Coordinate('a', 8), new Castle(new Coordinate('a', 8), 'B'));

        this.setPieceAt(new Coordinate('b', 1), new Knight(new Coordinate('b', 1), 'W'));
        this.setPieceAt(new Coordinate('b', 8), new Knight(new Coordinate('b', 8), 'B'));

        this.setPieceAt(new Coordinate('c', 1), new Bishop(new Coordinate('c', 1), 'W'));
        this.setPieceAt(new Coordinate('c', 8), new Bishop(new Coordinate('c', 8), 'B'));

        this.setPieceAt(new Coordinate('d', 1), new Queen(new Coordinate('d', 1), 'W'));
        this.setPieceAt(new Coordinate('d', 8), new Queen(new Coordinate('d', 8), 'B'));

        this.setPieceAt(new Coordinate('e', 1), new King(new Coordinate('e', 1), 'W'));
        this.setPieceAt(new Coordinate('e', 8), new King(new Coordinate('e', 8), 'B'));

        this.setPieceAt(new Coordinate('f', 1), new Bishop(new Coordinate('f', 1), 'W'));
        this.setPieceAt(new Coordinate('f', 8), new Bishop(new Coordinate('f', 8), 'B'));

        this.setPieceAt(new Coordinate('g', 1), new Knight(new Coordinate('g', 1), 'W'));
        this.setPieceAt(new Coordinate('g', 8), new Knight(new Coordinate('g', 8), 'B'));

        this.setPieceAt(new Coordinate('h', 1), new Castle(new Coordinate('h', 1), 'W'));
        this.setPieceAt(new Coordinate('h', 8), new Castle(new Coordinate('h', 8), 'B'));

        for (char i = 'a'; i <= 'h'; i++)
        {
            this.setPieceAt(new Coordinate(i, 2), new Pawn(new Coordinate(i, 2), 'W', false));
            this.setPieceAt(new Coordinate(i, 7), new Pawn(new Coordinate(i, 7), 'B', false));
        }
    }
}
