package chessRoot;

import chessRoot.pieces.ChessPiece;

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
