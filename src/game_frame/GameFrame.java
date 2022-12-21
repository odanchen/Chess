package game_frame;

import assets.board_colors.BoardColors;
import assets.board_colors.ColorPair;
import board_package.Board;
import pieces.ChessPiece;
import pieces.PieceColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

class BoardPanel extends JPanel {
    private ColorPair boardColors = BoardColors.OPTION1;
    private int boardSize;

    private int squareSize() {
        return boardSize / 8;
    }

    @Override
    public void paint(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if ((col + row) % 2 == 0) g.setColor(this.boardColors.getWhiteCell());
                else g.setColor(this.boardColors.getBlackCell());

                g.fillRect(col * this.squareSize(), row * this.squareSize(), this.squareSize(), this.squareSize());
            }
        }
    }

    BoardPanel(int boardSideSize) {
        this.setSize(boardSideSize, boardSideSize);
        this.setOpaque(true);
        this.boardSize = boardSideSize;
    }
}

class PiecePanel extends JPanel {
    private final Board board;
    private int boardSize;
    private String pieceTextureFolder = "cburnett";
    private final double SQUARE_TO_PIECE_RATIO = 0.875;

    private int getSquareSize() {
        return this.boardSize / 8;
    }

    private int getPieceSize() {
        return (int) (getSquareSize() * this.SQUARE_TO_PIECE_RATIO);
    }

    private int getPieceCoordinate(int idx) {
        return (int) (getSquareSize() * (idx + (1 - this.SQUARE_TO_PIECE_RATIO)));
    }

    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }

    private String getImageName(ChessPiece piece) {
        String color = (piece.getPieceColor() == PieceColor.WHITE) ? "w" : "b";

        return (color + piece.getPieceLetter() + ".png");
    }

    private BufferedImage getTextureOfPiece(ChessPiece piece) throws IOException {
        String root = Paths.get("").toAbsolutePath().toString();
        String[] fullPath = {root, "src", "assets", "pieces_textures", this.pieceTextureFolder, getImageName(piece)};

        return ImageIO.read(new File(String.join(File.separator, fullPath)));
    }

    @Override
    public void paint(Graphics g) {
        for (ChessPiece piece : board.getAllPieces()) {

            int row = Math.abs(piece.getPosition().getRow() - 8);
            int col = (int) piece.getPosition().getCol() - 'a';

            try {
                BufferedImage image = getTextureOfPiece(piece);
                image = toBufferedImage(image.getScaledInstance(getPieceSize(), getPieceSize(), Image.SCALE_SMOOTH));
                g.drawImage(image, getPieceCoordinate(col), getPieceCoordinate(row), null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    PiecePanel(int boardSideSize, Board board) {
        this.setSize(boardSideSize, boardSideSize);
        this.setOpaque(false);
        this.board = board;
        this.boardSize = boardSideSize;
    }
}

public class GameFrame extends JFrame {
    private final BoardPanel boardPanel;
    private final PiecePanel piecePanel;
    private final Board board;

    public GameFrame(int x, int y, int boardSize, Board board) {
        this.setBounds(x, y, boardSize, boardSize);
        this.setUndecorated(true);

        this.board = board;
        this.boardPanel = new BoardPanel(boardSize);
        this.piecePanel = new PiecePanel(boardSize, board);

        this.add(piecePanel);
        this.add(boardPanel);
    }

    public void updatePieces()
    {
        this.piecePanel.repaint();
    }
}
