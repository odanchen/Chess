package chessRoot.user_interface.game_flow;

import chessRoot.logic.Board;
import chessRoot.logic.log.GameLog;
import chessRoot.logic.moves.Move;
import chessRoot.logic.pieces.ChessPiece;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.logic.pieces.Position;

import java.util.ArrayList;
import java.util.List;

public class GameStatus {
    private ChessPiece selectedPiece;
    private Move selectedMove;
    private GameStates gameState;
    private final Board board;
    private final GameLog gameLog;

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

    public void selectMove(Move move) {
        this.selectedMove = move;
    }

    public void deselectMove() {
        this.selectedMove = null;
    }

    public Move getSelectedMove() {
        return selectedMove;
    }

    public PieceColor getSelectedColor() {
        return selectedPiece.getPieceColor();
    }

    public ChessPiece getPieceAt(Position position) {
        return board.getPieceAt(position);
    }

    public void logMove(Move move) {
        gameLog.addMove(move);
        System.out.println(gameLog.getString().toString());
    }

    public GameStatus(Board board, GameStates startingState) {
        this.selectedPiece = null;
        this.selectedMove = null;
        this.gameState = startingState;
        this.board = board;
        this.gameLog = new GameLog(board);
    }
}
