package chessRoot.user_interface.frames.game_frame;

import chessRoot.user_interface.GraphicsManager;
import chessRoot.user_interface.game_flow.GameControl;
import chessRoot.logic.pieces.ChessPiece;
import chessRoot.logic.pieces.Position;
import chessRoot.logic.moves.Move;
import chessRoot.user_interface.game_flow.GameStates;
import chessRoot.user_interface.game_flow.GameStatus;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static chessRoot.user_interface.game_flow.GameStates.*;

public class GameFrame extends JFrame {
    private final BoardPanel boardPanel;
    private final PiecePanel piecePanel;
    private final IndicationPanel indicPanel;
    private final GameStatus gameStatus;
    private final GameControl gameControl;
    private final GraphicsManager graphicsManager;

    public GameFrame(GameStatus gameStatus, GameControl gameControl, GraphicsManager graphicsManager) {
        this.gameStatus = gameStatus;
        this.gameControl = gameControl;
        this.graphicsManager = graphicsManager;

        boardPanel = new BoardPanel(graphicsManager);
        piecePanel = new PiecePanel(graphicsManager, gameStatus);
        indicPanel = new IndicationPanel(graphicsManager, gameStatus);
        addBasicParameters();
    }

    private void addBasicParameters() {
        setUndecorated(true);
        setBounds(graphicsManager.getGameFrameBounds());
        addPanels();
        createMouseListener();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addPanels() {
        add(piecePanel);
        add(indicPanel);
        add(boardPanel);
    }

    private void playerWhiteTurnEvent(MouseEvent e) {
        if (isClickInsideBoard(e)) {
            ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(getPositionOnTheBoard(e));
            if (clickedPiece != null && clickedPiece.isWhite()) {
                selectPiece(clickedPiece);
            }
        }
    }

    private void playerBlackTurnEvent(MouseEvent e) {
        if (isClickInsideBoard(e)) {
            ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(getPositionOnTheBoard(e));
            if (clickedPiece != null && clickedPiece.isBlack()) {
                selectPiece(clickedPiece);
            }
        }
    }

    private void playerSelectedAPieceEvent(MouseEvent e) {
        if (isActionReselect(e)) reselectPiece(e);
        else if (isActionDeselect(e)) deselectPiece();
        else if (isActionMove(e)) makeMove(e);
    }

    private void onMousePress(MouseEvent e) {
        switch (gameStatus.getState()) {
            case PLAYER_WHITE_TURN:
                playerWhiteTurnEvent(e);
                break;
            case PLAYER_BLACK_TURN:
                playerBlackTurnEvent(e);
                break;
            case PLAYER_BLACK_SELECTED_PIECE:
            case PLAYER_WHITE_SELECTED_PIECE:
                playerSelectedAPieceEvent(e);
                break;
        }
    }

    private void createMouseListener() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                onMousePress(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private GameStates stateAfterMove() {
        if (gameStatus.getState() == PLAYER_BLACK_SELECTED_PIECE) return PLAYER_WHITE_TURN;
        return PLAYER_BLACK_TURN;
    }

    private void makeMove(MouseEvent e) {
        Position clickedPosition = getPositionOnTheBoard(e);

        Move moveToMake = gameStatus.getSelectedPieceMoves().stream()
                .filter(move -> move.getEndPosition().equals(clickedPosition))
                .findFirst().orElse(null);

        gameControl.performMove(moveToMake);
    }

    public void makeMove(Move move) {
        gameStatus.getBoard().makeMove(move);
        gameStatus.setGameState(stateAfterMove());
        gameStatus.deselectPiece();
        updateFrame();
    }

    private boolean isActionMove(MouseEvent e) {
        if (!isClickInsideBoard(e)) return false;
        Position clickedPosition = getPositionOnTheBoard(e);
        return gameStatus.getSelectedPieceMoves().stream().anyMatch(move -> move.getEndPosition().equals(clickedPosition));
    }

    private boolean isActionReselect(MouseEvent e) {
        if (!isClickInsideBoard(e)) return false;
        Position clickedPosition = getPositionOnTheBoard(e);
        ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(clickedPosition);
        if (gameStatus.getState() == PLAYER_BLACK_SELECTED_PIECE) {
            return (clickedPiece != null && clickedPiece.isBlack() && clickedPiece != gameStatus.getSelectedPiece());
        }
        return (clickedPiece != null && clickedPiece.isWhite() && clickedPiece != gameStatus.getSelectedPiece());
    }

    private void reselectPiece(MouseEvent e) {
        Position clickedPosition = getPositionOnTheBoard(e);
        ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(clickedPosition);
        selectPiece(clickedPiece);
    }

    private GameStates getStateAfterSelect() {
        switch (gameStatus.getState()) {
            case PLAYER_BLACK_TURN:
            case PLAYER_BLACK_SELECTED_PIECE:
                return PLAYER_BLACK_SELECTED_PIECE;
            case PLAYER_WHITE_TURN:
            case PLAYER_WHITE_SELECTED_PIECE:
                return PLAYER_WHITE_SELECTED_PIECE;
        }
        return null;
    }

    private void selectPiece(ChessPiece piece) {
        gameStatus.setGameState(getStateAfterSelect());
        gameStatus.selectPiece(piece);
        indicPanel.updatePanel();
    }

    private GameStates getStateAfterDeselect() {
        if (gameStatus.getState() == PLAYER_BLACK_SELECTED_PIECE) return PLAYER_BLACK_TURN;
        else return PLAYER_WHITE_TURN;
    }

    private boolean isActionDeselect(MouseEvent e) {
        if (!isClickInsideBoard(e)) return true;
        Position clickedPosition = getPositionOnTheBoard(e);
        return gameStatus.getSelectedPieceMoves().stream().noneMatch(move -> move.getEndPosition().equals(clickedPosition));
    }

    private void deselectPiece() {
        gameStatus.setGameState(getStateAfterDeselect());
        gameStatus.deselectPiece();
        indicPanel.updatePanel();
    }

    public void updateFrame() {
        piecePanel.updatePanel();
        indicPanel.updatePanel();
    }

    private boolean isClickInsideBoard(MouseEvent e) {
        int rect = graphicsManager.getEdgeSize();
        int board = graphicsManager.getBoardSize();

        return (e.getX() > rect) && (e.getX() < board + rect) && (e.getY() > rect) && (e.getY() < board + rect);
    }

    private Position getPositionOnTheBoard(MouseEvent e) {
        int row = (e.getY() - graphicsManager.getEdgeSize()) / graphicsManager.getSquareSize();
        int col = (e.getX() - graphicsManager.getEdgeSize()) / graphicsManager.getSquareSize();

        return new Position((char) (col + 'a'), (8 - row));
    }
}
