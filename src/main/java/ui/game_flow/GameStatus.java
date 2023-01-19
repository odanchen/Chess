package ui.game_flow;

import logic.moves.Move;
import logic.pieces.ChessPiece;
import logic.utils.GameLog;
import logic.utils.TimerPair;
import logic.Board;
import logic.pieces.properties.PieceColor;
import logic.pieces.properties.Position;

import java.util.List;

public class GameStatus {
    private final Board board;
    private final GameLog gameLog;
    private TimerPair timers;
    private ChessPiece selectedPiece;
    private Move selectedMove;
    private GameStates gameState;
    private GameResult gameResult;
    private final boolean timerPresent;

    public GameStatus(Board board, PieceColor startingSide, TimerPair timers) {
        this.selectedPiece = null;
        this.selectedMove = null;
        this.gameResult = null;
        this.gameState = startingSide == PieceColor.WHITE ? GameStates.WHITE_TURN : GameStates.BLACK_TURN;
        this.board = board;
        this.gameLog = new GameLog(board);
        this.timers = timers;
        this.timerPresent = true;
    }

    public GameStatus(Board board, PieceColor startingSide) {
        this.selectedPiece = null;
        this.selectedMove = null;
        this.gameResult = null;
        this.gameState = startingSide == PieceColor.WHITE ? GameStates.WHITE_TURN : GameStates.BLACK_TURN;
        this.board = board;
        this.gameLog = new GameLog(board);
        this.timerPresent = false;
    }

    public boolean timerPresent() {
        return timerPresent;
    }

    public Board getBoard() {
        return board;
    }

    public String getBlackTimeLeft() {
        return timers.getBlackTimeLeft();
    }

    public String getWhiteTimeLeft() {
        return timers.getWhiteTimeLeft();
    }

    public void handleTimer(Move move) {
        if (move.getPieceAtEnd(board).isWhite()) timers.whiteMoved();
        else timers.blackMoved();
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

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult result) {
        gameResult = result;
    }

    public void updateGameResult() {
        gameResult = generateGameResult();
    }

    public GameLog getGameLog() {
        return gameLog;
    }

    public boolean isGameEnd() {
        return (generateGameResult() != null);
    }

    private GameResult generateGameResult() {
        if (isCheckmate(PieceColor.WHITE)) {
            return GameResult.PLAYER_BLACK_WON_BY_CHECKMATE;
        } else if (isCheckmate(PieceColor.BLACK)) {
            return GameResult.PLAYER_WHITE_WON_BY_CHECKMATE;
        } else if (isStalemate()) {
            return GameResult.STALEMATE;
        }
        return null;
    }

    public void logMove(Move move) {
        gameLog.addMove(move);
    }

    public boolean isCheckmate(PieceColor pieceColor) {
        return board.isCheckmate(pieceColor);
    }

    public boolean isStalemate() {
        return board.isStalemate(PieceColor.WHITE) || board.isStalemate(PieceColor.BLACK);
    }
}
