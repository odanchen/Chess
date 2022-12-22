package chessRoot.game_frame;

import chessRoot.Board;
import chessRoot.pieces.Position;
import chessRoot.pieces.moves.Move;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame {
    private final BoardPanel boardPanel;
    private final PiecePanel piecePanel;
    private final Board board;
    private int boardSize;
    private Move ans = null;

    public GameFrame(int x, int y, int boardSize, Board board) {
        this.setBounds(x, y, boardSize, boardSize);
        this.setUndecorated(true);

        this.board = board;
        this.boardSize = boardSize;
        this.boardPanel = new BoardPanel(boardSize);
        this.piecePanel = new PiecePanel(boardSize, board);

        this.add(piecePanel);
        this.add(boardPanel);
        this.createMouseListener();
    }

    private void createMouseListener()
    {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
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

    //public Move listenUserMove(PieceColor currentSide) throws IOException {
        //ans = null;


      //  return ans;
    //}
}
