package ui.graphics;

import assets.colors.ColorSet;
import assets.settings.IOSettings;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextureHolder {
    private final Map<String, BufferedImage> activeTextures;
    private final int pieceSize;
    private final Font font;
    private final List<String> pieceSignatures = List.of("bb", "bk", "bn", "bp", "bq", "br", "wb", "wk", "wn", "wp", "wq", "wr");
    private ColorSet colorSet;
    private Map<Character, BufferedImage> bitmapFont;
    private String pieceTextureFolder;

    public TextureHolder(Font font, int pieceSize) {
        IOSettings ioSettings = new IOSettings();
        this.colorSet = ioSettings.getBoardColors();
        this.font = font;
        this.pieceTextureFolder = ioSettings.getTexturePack();
        this.pieceSize = pieceSize;

        activeTextures = new HashMap<>();
        fillActiveTextures();
        generateLetterTextures(colorSet);
    }

    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bImage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bImage;
    }

    public void generateLetterTextures(ColorSet colorSet) {
        this.colorSet = colorSet;
        List<Character> letters = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ':');
        this.bitmapFont = letters.stream().collect(Collectors.toMap(Function.identity(), this::createBitmap));
    }

    private BufferedImage createBitmap(Character character) {
        BufferedImage image = new BufferedImage(font.getSize(), font.getSize(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(colorSet.getWhiteCell());
        drawCenteredString(g, String.valueOf(character), new Rectangle(0, 0, font.getSize(), font.getSize()), font);
        g.dispose();
        return image;
    }

    public int getLetterSize() {
        return font.getSize();
    }

    private void fillActiveTextures() {
        pieceSignatures.forEach(signature -> activeTextures.put(signature, getNewTexture(signature)));
    }

    public void refreshPieceTextures(String textureFolder) {
        if (!pieceTextureFolder.equals(textureFolder)) {
            pieceTextureFolder = textureFolder;
            fillActiveTextures();
        }
    }

    private BufferedImage rescale(BufferedImage image, int height, int width) {
        return toBufferedImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public String getPathToPieceTexture(String signature) {
        return getPathTo("pieces", pieceTextureFolder + File.separator + signature);
    }

    private BufferedImage getNewTexture(String signature) {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResource(getPathToPieceTexture(signature)).openStream()) {
            return  rescale(ImageIO.read(in), pieceSize, pieceSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPathTo(String folder, String fileName) {
        String[] fullPath = {folder, fileName + ".png"};
        return String.join(File.separator, fullPath);
    }

    public BufferedImage getTextureOf(String folder, String filename, Dimension size) {
        //noinspection DataFlowIssue
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResource(getPathTo(folder, filename)).openStream()) {
            return rescale(ImageIO.read(in), size.height, size.width);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ImageIcon getIconOf(String folder, String filename, Dimension size) {
        //noinspection DataFlowIssue
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResource(getPathTo(folder, filename)).openStream()) {
            return new ImageIcon(rescale(ImageIO.read(in), size.height, size.width));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public BufferedImage getPieceTexture(String signature) {
        return activeTextures.get(signature);
    }

    public BufferedImage getLetterImage(char letter) {
        return bitmapFont.get(letter);
    }

    public void refreshColors() {
        colorSet = new IOSettings().getBoardColors();
        generateLetterTextures(colorSet);
    }
}