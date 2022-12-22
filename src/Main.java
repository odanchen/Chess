import chessRoot.logic.Board;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.user_interface.game_flow.GameControl;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.fillStandardBoard();

        GameControl control = new GameControl(board, PieceColor.WHITE);
        control.runTheGame();
    }
}
