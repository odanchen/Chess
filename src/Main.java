import board_package.Board;
import pieces.PieceColor;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Board board = new Board();
        board.fillStandardBoard();

        GameControl control = new GameControl(board);
        control.runTheGame(board, PieceColor.WHITE);
    }
}
