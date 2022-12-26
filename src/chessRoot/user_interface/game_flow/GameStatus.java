package chessRoot.user_interface.game_flow;

import chessRoot.logic.Board;
import chessRoot.logic.moves.Move;
import chessRoot.logic.pieces.ChessPiece;

import java.util.ArrayList;
import java.util.List;

public class GameStatus {
    private ChessPiece selectedPiece;
    private GameStates gameState;
    private List<Move> gameLog;
    private final Board board;

    public Board getBoard() {
        return board;
    }

    public ChessPiece getSelectedPiece() {
        return selectedPiece;
    }

    public GameStates getState() {
        return gameState;
    }

    public void setGameState(GameStates state) {
        this.gameState = state;
    }

    public void selectPiece(ChessPiece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public void deselectPiece() {
        selectedPiece = null;
    }

    public List<Move> getSelectedPieceMoves() {
        return selectedPiece.calculateMoves(board);
    }

    public boolean isPieceSelected() {
        return selectedPiece != null;
    }

    public List<ChessPiece> getAllPieces() {
        return board.getAllPieces();
    }

    public GameStatus(Board board, GameStates startingState) {
        this.selectedPiece = null;
        this.gameState = startingState;
        this.board = board;
        this.gameLog = new ArrayList<>();
    }
}
