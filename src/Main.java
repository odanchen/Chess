import chessRoot.logic.Board;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.user_interface.GameManager;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.fillStandardBoard();

        GameManager manager = new GameManager();
        manager.runTheGame(board, PieceColor.WHITE);
    }
}
