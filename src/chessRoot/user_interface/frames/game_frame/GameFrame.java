package chessRoot.user_interface.frames.game_frame;

import chessRoot.assets.board_colors.BoardColors;
import chessRoot.assets.board_colors.ColorSet;
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
    private int boardSize = 512;
    private ColorSet colorSet = BoardColors.OPTION1;
    private final GameStatus gameStatus;
    private final GameControl gameControl;

    public GameFrame(GameStatus gameStatus, GameControl gameControl) {
        setUndecorated(true);
        setBounds(0, 0, boardSize, boardSize);

        this.gameStatus = gameStatus;
        this.gameControl = gameControl;
        boardPanel = new BoardPanel(boardSize, colorSet);
        piecePanel = new PiecePanel(boardSize, gameStatus, gameControl);
        indicPanel = new IndicationPanel(boardSize, gameStatus, gameControl, colorSet);

        add(piecePanel);
        add(indicPanel);
        add(boardPanel);

        createMouseListener();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void playerWhiteTurnEvent(MouseEvent e) {
        ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(getPositionOnTheBoard(e.getX(), e.getY()));
        if (clickedPiece != null && clickedPiece.isWhite()) {
            gameStatus.setGameState(PLAYER_WHITE_SELECTED_PIECE);
            gameStatus.selectPiece(clickedPiece);
            indicPanel.updateSelectedPiece(clickedPiece);
        }
    }

    private void playerBlackTurnEvent(MouseEvent e) {
        ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(getPositionOnTheBoard(e.getX(), e.getY()));
        if (clickedPiece != null && clickedPiece.isBlack()) {
            gameStatus.setGameState(PLAYER_BLACK_SELECTED_PIECE);
            gameStatus.selectPiece(clickedPiece);
            indicPanel.updateSelectedPiece(clickedPiece);
        }
    }

    private void playerWhiteSelectedAPieceEvent(MouseEvent e) {
        Position clickedPosition = getPositionOnTheBoard(e.getX(), e.getY());

        Move moveToMake = gameStatus.getSelectedPieceMoves().stream()
                .filter(move -> move.getEndPosition().equals(clickedPosition))
                .findFirst().orElse(null);

        if (moveToMake != null) {
            gameControl.performMove(moveToMake);
            piecePanel.repaint();
            if (gameStatus.getBoard().isMate(PieceColor.BLACK)) {
                System.out.println("player black lost");
            }
            gameStatus.setGameState(PLAYER_BLACK_TURN);
        } else gameStatus.setGameState(PLAYER_WHITE_TURN);

        indicPanel.removeAll();
        piecePanel.repaint();
        gameStatus.deselectPiece();
    }

    private void playerBlackSelectedAPieceEvent(MouseEvent e) {
        Position clickedPosition = getPositionOnTheBoard(e.getX(), e.getY());

        Move moveToMake = gameStatus.getSelectedPieceMoves().stream()
                .filter(move -> move.getEndPosition().equals(clickedPosition))
                .findFirst().orElse(null);

        if (moveToMake != null) {
            gameControl.performMove(moveToMake);
            piecePanel.repaint();
            if (gameStatus.getBoard().isMate(WHITE)) {
                System.out.println("player white lost");
            }
            gameStatus.setGameState(PLAYER_WHITE_TURN);
        } else gameStatus.setGameState(PLAYER_BLACK_TURN);

        indicPanel.removeAll();
        piecePanel.repaint();
        gameStatus.deselectPiece();
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

    public void updatePieces() {
        this.piecePanel.repaint();
    }

    private int squareSize() {
        return this.boardSize / 8;
    }


    private Position getPositionOnTheBoard(int x, int y) {
        int row = y / squareSize();
        int col = x / squareSize();

        return new Position((char) (col + 'a'), (8 - row));
    }
}
