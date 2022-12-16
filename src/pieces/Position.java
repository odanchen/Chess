package pieces;

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

    /**
     * @return If the current position is inside the board or not. (Assumes 8 by 8 board)
     */
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

    public boolean equals(Position position) {
        return (this.getRow() == position.getRow() && this.getCol() == position.getCol());
    }
}