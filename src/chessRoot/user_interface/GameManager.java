package chessRoot.user_interface;

import chessRoot.assets.board_colors.ColorSet;
import chessRoot.logic.Board;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.user_interface.game_flow.GameControl;

import static chessRoot.assets.board_colors.BoardColors.OPTION1;

public class GameManager {
    private GameControl gameControl;
    private ColorSet boardColors = OPTION1;

    public ColorSet getBoardColors() {
        return boardColors;
    }

    public void runTheGame(Board board, PieceColor firstMove) {
        gameControl = new GameControl(board, firstMove, this);
    }
}
