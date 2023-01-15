package root.ui.game_flow;

import root.logic.Board;
import root.logic.moves.Move;
import root.logic.pieces.ChessPiece;
import root.logic.pieces.properties.PieceColor;
import root.logic.pieces.properties.Position;
import root.logic.utils.GameLog;
import root.logic.utils.TimerPair;

import java.util.List;

public class GameStatus {
    private final Board board;
    private final GameLog gameLog;
    private final TimerPair timers;
    private ChessPiece selectedPiece;
    private Move selectedMove;
    private GameStates gameState;
    private GameResult gameResult;

    public GameStatus(Board board, PieceColor startingSide, TimerPair timers) {
        this.selectedPiece = null;
        this.selectedMove = null;
        this.gameResult = null;
        this.gameState = startingSide == PieceColor.WHITE ? GameStates.WHITE_TURN : GameStates.BLACK_TURN;
        this.board = board;
        this.gameLog = new GameLog(board);
        this.timers = timers;
    }

    public Board getBoard() {
        return board;
    }

    public String getBlackTimeLeft() {
        return timers.getBlackTimer().getTimeLeft();
    }
    public String getWhiteTimeLeft() {
        return timers.getWhiteTimer().getTimeLeft();
    }

    public void handleTimer(Move move) {
        if (move.getPieceAtEnd(board).isWhite()) {
            timers.getWhiteTimer().stop();
            timers.getBlackTimer().start();
        } else {
            timers.getBlackTimer().stop();
            timers.getWhiteTimer().start();
        }
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
