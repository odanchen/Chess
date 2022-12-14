package pieces;

import BoardPackage.Board;

import java.util.ArrayList;
import java.util.List;

public class Castle extends ChessPiece {
    private boolean hasMoved;
    private List<Position> checkLine(Board board, int colDifference, int rowDifference)
    {
        List<Position> moves = new ArrayList<>();

        Position curPosition = new Position(this.getPosition(), colDifference, rowDifference);
        while (curPosition.isInsideBoard() && board.getPieceAt(curPosition) == null)
        {
            moves.add(curPosition);
            curPosition = new Position(curPosition, colDifference, rowDifference);
        }
        if (curPosition.isInsideBoard() && board.getPieceAt(curPosition).getPieceColor() != this.getPieceColor())
            moves.add(curPosition);

        return moves;
    }
    @Override
    public void calculateMoves(Board board)
    {
        List<Position> moves = new ArrayList<>();

        moves.addAll(checkLine(board, 1, 0));
        moves.addAll(checkLine(board, 0, 1));
        moves.addAll(checkLine(board, -1, 0));
        moves.addAll(checkLine(board, 0, -1));

        this.setMoves(moves);
    }
    public Castle(Position position, PieceColor color, boolean hasMoved)
    {
        this.pieceColor = color;
        this.position = position;
        this.hasMoved = hasMoved;
    }
}
