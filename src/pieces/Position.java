package pieces;

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
    public Position(char col, int row)
    {
        this.col = col;
        this.row = row;
    }
}