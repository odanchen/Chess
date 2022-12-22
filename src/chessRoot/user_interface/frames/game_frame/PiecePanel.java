package chessRoot.user_interface.frames.game_frame;

import chessRoot.logic.Board;
import chessRoot.logic.pieces.ChessPiece;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.user_interface.game_flow.GameControl;
import chessRoot.user_interface.game_flow.GameStatus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class PiecePanel extends JPanel {
    private int boardSize;
    private String pieceTextureFolder = "cburnett";
    private final double SQUARE_TO_PIECE_RATIO = 0.875;
    private final GameStatus gameStatus;
    private final GameControl gameControl;

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
        for (ChessPiece piece : gameStatus.getBoard().getAllPieces()) {

            int row = Math.abs(piece.getPosition().getRow() - 8);
            int col = (int) piece.getPosition().getCol() - 'a';

            BufferedImage image;

            image = getTextureOfPiece(piece);

            image = toBufferedImage(image.getScaledInstance(getPieceSize(), getPieceSize(), Image.SCALE_SMOOTH));
            g.drawImage(image, getPieceCoordinate(col), getPieceCoordinate(row), null);
        }
    }

    PiecePanel(int boardSideSize, GameStatus gameStatus, GameControl gameControl) {
        this.setSize(boardSideSize, boardSideSize);
        this.setOpaque(false);
        this.gameStatus = gameStatus;
        this.gameControl = gameControl;
        this.boardSize = boardSideSize;
    }
}