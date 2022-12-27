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

    private void playerTurnEvent(MouseEvent e) {
        if (isActionSelect(e)) actionSelect(e);
    }

    private void playerSelectedAPieceEvent(MouseEvent e) {
        if (isActionReselect(e)) reselectPiece(e);
        else if (isActionDeselect(e)) deselectPiece();
        else if (isActionMove(e)) makeMove(e);
    }

    private void onMousePress(MouseEvent e) {
        switch (gameStatus.getState()) {
            case PLAYER_WHITE_TURN:
            case PLAYER_BLACK_TURN:
                playerTurnEvent(e);
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

    private void actionSelect(MouseEvent e) {
        ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(getPositionOnTheBoard(e));
        selectPiece(clickedPiece);
    }

    private boolean isActionSelect(MouseEvent e) {
        if (isClickOutsideBoard(e)) return false;
        ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(getPositionOnTheBoard(e));
        return (isClickedPieceRightColor(clickedPiece));
    }

    private boolean isClickedPieceRightColor(ChessPiece clickedPiece) {
        if (clickedPiece == null) return false;

        if (gameStatus.getState() == PLAYER_BLACK_SELECTED_PIECE || gameStatus.getState() == PLAYER_BLACK_TURN) {
            return clickedPiece.isBlack();
        } else {
            return clickedPiece.isWhite();
        }
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
        if (isActionReselect(e)) return false;
        Position clickedPosition = getPositionOnTheBoard(e);
        return gameStatus.getSelectedPieceMoves().stream().anyMatch(move -> move.getEndPosition().equals(clickedPosition));
    }

    private boolean isActionReselect(MouseEvent e) {
        if (isClickOutsideBoard(e)) return false;
        Position clickedPosition = getPositionOnTheBoard(e);
        ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(clickedPosition);
        return (isClickedPieceRightColor(clickedPiece) && clickedPiece != gameStatus.getSelectedPiece());
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
        if (isClickOutsideBoard(e)) return true;
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

    private boolean isClickOutsideBoard(MouseEvent e) {
        int rect = graphicsManager.getEdgeSize();
        int board = graphicsManager.getBoardSize();

        return (e.getX() <= rect) || (e.getX() >= board + rect) || (e.getY() <= rect) || (e.getY() >= board + rect);
    }

    private Position getPositionOnTheBoard(MouseEvent e) {
        int row = Math.abs((e.getY() - graphicsManager.getEdgeSize()) / graphicsManager.getSquareSize() - (graphicsManager.isFlipped() ? 7 : 0));
        int col = Math.abs((e.getX() - graphicsManager.getEdgeSize()) / graphicsManager.getSquareSize() - (graphicsManager.isFlipped() ? 7 : 0));

        return new Position((char) (col + 'a'), (8 - row));
    }
}
