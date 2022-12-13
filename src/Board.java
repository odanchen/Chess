import pieces.ChessPiece;
import pieces.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Board {
    ColorPair boardColor = BoardColors.OPTION1;

    List<List<ChessPiece>> configuration = new ArrayList<>();
    public ChessPiece getPieceAt(Coordinate coordinate)
    {
        int matrixRow = Math.abs(coordinate.getRow() - 8);
        int matrixCol = (int) coordinate.getCol() - 'a';
        return configuration.get(matrixRow).get(matrixCol);
    }
}
