package root.ui.graphics;

import root.assets.colors.ColorSet;
import root.assets.settings.IOSettings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextureHolder {
    private ColorSet colorSet;
    private Map<Character, BufferedImage> bitmapFont;
    private final Map<String, BufferedImage> activeTextures;
    private String pieceTextureFolder;
    private final int pieceSize;
    private final Font font;
    private final List<String> pieceSignatures = List.of("bb", "bk", "bn", "bp", "bq", "br", "wb", "wk", "wn", "wp", "wq", "wr");

    public void generateLetterTextures(ColorSet colorSet) {
        this.colorSet = colorSet;
        List<Character> letters = new ArrayList<>();
        for (char letter = '0'; letter <= '9'; letter++) letters.add(letter);
        for (char letter = 'a'; letter <= 'z'; letter++) letters.add(letter);
        for (char letter = 'A'; letter <= 'Z'; letter++) letters.add(letter);
        letters.add(' ');
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

    private void fillActiveTextures() {
        pieceSignatures.forEach(signature -> activeTextures.put(signature, getNewTexture(signature)));
    }

    public void refreshPieceTextures(String textureFolder) {
        if (!pieceTextureFolder.equals(textureFolder)) {
            pieceTextureFolder = textureFolder;
            fillActiveTextures();
        }
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

    private BufferedImage rescale(BufferedImage image, int height, int width) {
        return toBufferedImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public String getPathToPieceTexture(String signature) {
        return getPathTo("pieces", pieceTextureFolder + File.separator + signature);
    }

    private BufferedImage getNewTexture(String signature) {
        try {
            return rescale(ImageIO.read(new File(getPathToPieceTexture(signature))), pieceSize, pieceSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPathTo(String folder, String fileName) {
        String root = Paths.get("").toAbsolutePath().toString();
        String[] fullPath = {root, "src", "root", "assets", folder, fileName + ".png"};
        return String.join(File.separator, fullPath);
    }

    public BufferedImage getTextureOf(String folder, String filename, Dimension size) {
        try {
            BufferedImage img = ImageIO.read(new File(getPathTo(folder, filename)));
            return rescale(img, size.height, size.width);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ImageIcon getIconOf(String folder, String filename, Dimension size) {
        try {
            BufferedImage img = ImageIO.read(new File(getPathTo(folder, filename)));
            return new ImageIcon(rescale(img, size.height, size.width));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
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
}
