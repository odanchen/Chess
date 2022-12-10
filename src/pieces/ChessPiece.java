package pieces;

import java.util.List;

public abstract class ChessPiece {

    char pieceColor;
    Coordinate position;

    abstract List<Coordinate> calculateMoves();
}
