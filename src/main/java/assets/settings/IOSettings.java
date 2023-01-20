package assets.settings;

import assets.colors.BoardColors;
import assets.colors.ColorSet;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * A class which reads saved setting for the game
 */

@SuppressWarnings("SpellCheckingInspection")
public class IOSettings {
    private final File propFile;

    public IOSettings() {
        propFile = getSettingsFile();
    }

    /**
     * Accesses the properties file.
     * @return The name of the texture pack currently used.
     */
    public String getTexturePack() {
        try (InputStream input = new FileInputStream(propFile)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties.getProperty("texturePack", "cburnett").toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Accesses the properties file for the flip toggle.
     * @return The yes/no of whether the flip toggle is on or not.
     */
    public boolean getFlipToggle() {
        try (InputStream input = new FileInputStream(propFile)) {
            Properties properties = new Properties();
            properties.load(input);
            return Boolean.parseBoolean(properties.getProperty("flipToggle", "false"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Accesses the properties file for the current color set being used.
     * @return The color set.
     */
    public ColorSet getBoardColors() {
        try (InputStream input = new FileInputStream(propFile)) {
            Properties properties = new Properties();
            properties.load(input);
            return BoardColors.getColors(properties.getProperty("boardColors", "Brown"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Accesses the properties file for the game length preference being used.
     * @return The string key for the corresponding game length.
     */
    public String getGameLength() {
        try (InputStream input = new FileInputStream(propFile)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties.getProperty("gameLength", "long");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the properties.
     * @param texturePack
     * @param toggle
     * @param colorSet
     * @param gameLength
     */
    public void setProperties(String texturePack, boolean toggle, ColorSet colorSet, String gameLength) {
        try (OutputStream output = new FileOutputStream(propFile)) {
            Properties properties = new Properties();
            properties.setProperty("texturePack", texturePack);
            properties.setProperty("flipToggle", String.valueOf(toggle));
            properties.setProperty("boardColors", colorSet.getStringVal());
            properties.setProperty("gameLength", gameLength);
            properties.store(output, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private File getSettingsFile() {
        String root = Paths.get("").toAbsolutePath().toString();
        String[] fullPath = {root, "settings.properties"};
        File file = new File(String.join(File.separator, fullPath));
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }
}
