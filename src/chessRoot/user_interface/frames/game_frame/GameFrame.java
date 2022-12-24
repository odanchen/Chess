package chessRoot.user_interface.frames.game_frame;

import chessRoot.user_interface.GraphicsManager;
import chessRoot.user_interface.game_flow.GameControl;
import chessRoot.logic.pieces.ChessPiece;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.logic.pieces.Position;
import chessRoot.logic.moves.Move;
import chessRoot.user_interface.game_flow.GameStatus;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static chessRoot.logic.pieces.PieceColor.WHITE;
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

        boardPanel = new BoardPanel(graphicsManager, gameStatus);
        piecePanel = new PiecePanel(graphicsManager, gameStatus);
        indicPanel = new IndicationPanel(graphicsManager, gameStatus);
        addBasicParameters();
    }

    private void addBasicParameters() {
        setUndecorated(true);
        setBounds(graphicsManager.getFrameRectangle());
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
                gameStatus.setGameState(PLAYER_WHITE_SELECTED_PIECE);
                gameStatus.selectPiece(clickedPiece);
                indicPanel.updatePanel();
            }
        }
    }

    private void playerBlackTurnEvent(MouseEvent e) {
        if (isClickInsideBoard(e)) {
            ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(getPositionOnTheBoard(e));
            if (clickedPiece != null && clickedPiece.isBlack()) {
                gameStatus.setGameState(PLAYER_BLACK_SELECTED_PIECE);
                gameStatus.selectPiece(clickedPiece);
                indicPanel.updatePanel();
            }
        }
    }

    private void playerWhiteSelectedAPieceEvent(MouseEvent e) {
        if (isClickInsideBoard(e)) {
            Position clickedPosition = getPositionOnTheBoard(e);
            ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(clickedPosition);

            if (clickedPiece != null && clickedPiece.isWhite() && clickedPiece != gameStatus.getSelectedPiece()) {
                gameStatus.selectPiece(clickedPiece);
                indicPanel.updatePanel();
            } else {
                Move moveToMake = gameStatus.getSelectedPieceMoves().stream()
                        .filter(move -> move.getEndPosition().equals(clickedPosition))
                        .findFirst().orElse(null);

                if (moveToMake != null) {
                    gameControl.performMove(moveToMake);
                    if (gameStatus.getBoard().isMate(PieceColor.BLACK)) {
                        System.out.println("player black lost");
                    }
                    gameStatus.setGameState(PLAYER_BLACK_TURN);
                } else {
                    gameStatus.setGameState(PLAYER_WHITE_TURN);
                    gameStatus.deselectPiece();
                    indicPanel.updatePanel();
                }
            }
        } else {
            gameStatus.setGameState(PLAYER_WHITE_TURN);
            gameStatus.deselectPiece();
            indicPanel.updatePanel();
        }

    }

    private void playerBlackSelectedAPieceEvent(MouseEvent e) {
        if (isClickInsideBoard(e)) {
            Position clickedPosition = getPositionOnTheBoard(e);
            ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(clickedPosition);

            if (clickedPiece != null && clickedPiece.isBlack() && clickedPiece != gameStatus.getSelectedPiece()) {
                gameStatus.selectPiece(clickedPiece);
                indicPanel.updatePanel();
            } else {
                Move moveToMake = gameStatus.getSelectedPieceMoves().stream()
                        .filter(move -> move.getEndPosition().equals(clickedPosition))
                        .findFirst().orElse(null);

                if (moveToMake != null) {
                    gameControl.performMove(moveToMake);
                    if (gameStatus.getBoard().isMate(WHITE)) {
                        System.out.println("player white lost");
                    }
                    gameStatus.setGameState(PLAYER_WHITE_TURN);
                } else {
                    gameStatus.setGameState(PLAYER_BLACK_TURN);
                    gameStatus.deselectPiece();
                    indicPanel.updatePanel();
                }
            }
        } else {
            gameStatus.setGameState(PLAYER_BLACK_TURN);
            gameStatus.deselectPiece();
            indicPanel.updatePanel();
        }
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
                playerBlackSelectedAPieceEvent(e);
                break;
            case PLAYER_WHITE_SELECTED_PIECE:
                playerWhiteSelectedAPieceEvent(e);
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

    public void updateFrame() {
        piecePanel.updatePanel();
        indicPanel.updatePanel();
    }

    private boolean isClickInsideBoard(MouseEvent e) {
        int rect = graphicsManager.rectangleSize();
        int board = graphicsManager.getBoardSize();

        return (e.getX() > rect) && (e.getX() < board + rect) && (e.getY() > rect) && (e.getY() < board + rect);
    }

    private Position getPositionOnTheBoard(MouseEvent e) {
        int row = (e.getY() - graphicsManager.rectangleSize()) / graphicsManager.squareSize();
        int col = (e.getX() - graphicsManager.rectangleSize()) / graphicsManager.squareSize();

        return new Position((char) (col + 'a'), (8 - row));
    }
}
