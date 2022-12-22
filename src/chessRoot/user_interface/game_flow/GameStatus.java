package chessRoot.user_interface.game_flow;

import chessRoot.logic.Board;
import chessRoot.logic.pieces.ChessPiece;

public class GameStatus {
    ChessPiece selectedPiece;
    GameStates gameState;
    Board board;

    public GameStatus(GameStates startingState, Board board)
    {
        this.selectedPiece = null;
        this.gameState = startingState;
        this.board = board;
    }
}
