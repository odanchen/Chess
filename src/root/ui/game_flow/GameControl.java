package root.ui.game_flow;

import root.ui.GameManager;
import root.ui.frames.game_frame.GameFrame;
import root.logic.Board;
import root.logic.pieces.properties.PieceColor;
import root.logic.moves.Move;

import static root.logic.pieces.properties.PieceColor.WHITE;
import static root.ui.game_flow.GameStates.BLACK_TURN;
import static root.ui.game_flow.GameStates.WHITE_TURN;

public class GameControl {
    private final GameFrame mainFrame;
    private final GameStatus gameStatus;
    private final GameManager gameManager;

    public GameControl(Board board, PieceColor movingSide, GameManager gameManager) {
        GameStates state = movingSide == WHITE ? WHITE_TURN : BLACK_TURN;
        this.gameStatus = new GameStatus(board, state);
        this.gameManager = gameManager;
        this.mainFrame = new GameFrame(gameStatus, this, gameManager.getGraphicsManager());
    }

    public void performMove(Move move) {
        mainFrame.getGamePanel().makeMove(move);
    }
}
