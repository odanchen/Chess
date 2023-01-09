package root.logic.pieces;

public class Position {
    private final int row;
    private final char col;

    /**
     * @return Row of the position.
     */
    public int getRow() {
        return row;
    }

    /**
     * @return Column of the position.
     */
    public char getCol() {
        return col;
    }

    public String toString() {
        return String.valueOf(col) + String.valueOf(row);
    }

    /**
     * @return If the current position is inside the board or not. (Assumes 8 by 8 board)
     */
    public boolean insideBoard() {
        return (row >= 1 && row <= 8) && (col >= 'a' && col <= 'h');
    }

    public Position(char col, int row) {
        this.col = col;
        this.row = row;
    }

    public Position(Position start, int colDifference, int rowDifference) {
        this.row = start.getRow() + rowDifference;
        this.col = (char) (start.getCol() + colDifference);
    }

    /**
     * @param position Current position.
     * @return Replica of position.
     */
    public static Position copyOf(Position position) {
        if (position == null) return null;
        return new Position(position.getCol(), position.getRow());
    }

    /**
     * @param pos A string containing the current position in chess term. Example: a2, or e5.
     * @return New position object.
     */
    public static Position at(String pos) {
        return new Position(pos.charAt(0), Integer.parseInt(pos.substring(1)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return (getRow() == position.getRow() && getCol() == position.getCol());
    }

    public int rowToIdx() {
        int BOARD_SIZE = 8;
        return Math.abs(row - BOARD_SIZE);
    }

    public int colToIdx() {
        return (int) col - 'a';
    }
}