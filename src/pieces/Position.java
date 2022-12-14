package pieces;

import java.util.Optional;

public class Position {
    private int row;
    private char col;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public char getCol() {
        return col;
    }

    public void setCol(char col) {
        this.col = col;
    }

    public boolean isInsideBoard() {
        return (this.row >= 1 && this.row <= 8) && (this.col >= 'a' && this.col <= 'h');
    }

    public Position(char col, int row) {
        this.col = col;
        this.row = row;
    }

    public Position(Position start, int colDifference, int rowDifference) {
        this.row = start.getRow() + rowDifference;
        this.col = (char) (start.getCol() + colDifference);
    }

    public static Position copyOf(Position position) {
        if (position == null) return null;
        return new Position(position.getCol(), position.getRow());
    }

    public static Position at(String pos) {
        return new Position(pos.charAt(0), Integer.parseInt(pos.substring(1)));
    }
}