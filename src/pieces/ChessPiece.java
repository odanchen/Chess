package pieces;

import BoardPackage.Board;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {
    protected PieceColor pieceColor;
    protected Position position;
    protected List<Position> moves;

    public List<Position> getMoves() {
        return this.moves;
    }

    public void setMoves(List<Position> moves) {
        this.moves = moves;
    }
    abstract public ChessPiece copy();
    public Position getPosition() {
        return this.position;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    public void setPieceColor(PieceColor color) {
        this.pieceColor = color;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    abstract public void calculateMoves(Board board);

    public void validateMoves(Board board) {
        List<Position> moves = new ArrayList<>(this.getMoves());
        for (Position pos : moves) {
            Board tempBoard = board.makeMove(this.copy(), pos);
            if (tempBoard.isCheck(this.getPieceColor())) moves.remove(pos);
        }
        this.setMoves(moves);
    }

}
