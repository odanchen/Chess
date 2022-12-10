import java.util.List;

abstract class ChessPiece {
    class Coordinate {
        int row;
        char col;
    }

    char pieceColor;
    Coordinate position;

    abstract List<Coordinate> calculateMoves();
}
