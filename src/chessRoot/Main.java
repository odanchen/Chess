package chessRoot;

import chessRoot.pieces.PieceColor;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.fillStandardBoard();

        GameControl control = new GameControl(board);
        control.runTheGame(PieceColor.WHITE);
    }
}
