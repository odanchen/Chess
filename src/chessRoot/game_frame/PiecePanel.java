package chessRoot.game_frame;

import chessRoot.Board;
import chessRoot.pieces.ChessPiece;
import chessRoot.pieces.PieceColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class PiecePanel extends JPanel {
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
        return (getSquareSize() * idx) + (getSquareSize()-getPieceSize())/2;
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

    private BufferedImage getTextureOfPiece(ChessPiece piece) {
        String root = Paths.get("").toAbsolutePath().toString();
        String[] fullPath = {root, "src", "chessRoot", "assets", "pieces_textures", this.pieceTextureFolder, getImageName(piece)};

        try {
            return ImageIO.read(new File(String.join(File.separator, fullPath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void paint(Graphics g) {
        for (ChessPiece piece : board.getAllPieces()) {

            int row = Math.abs(piece.getPosition().getRow() - 8);
            int col = (int) piece.getPosition().getCol() - 'a';

            BufferedImage image;

            image = getTextureOfPiece(piece);

            image = toBufferedImage(image.getScaledInstance(getPieceSize(), getPieceSize(), Image.SCALE_SMOOTH));
            g.drawImage(image, getPieceCoordinate(col), getPieceCoordinate(row), null);
        }
    }

    PiecePanel(int boardSideSize, Board board) {
        this.setSize(boardSideSize, boardSideSize);
        this.setOpaque(false);
        this.board = board;
        this.boardSize = boardSideSize;
    }
}