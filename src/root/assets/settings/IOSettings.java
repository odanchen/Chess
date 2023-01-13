package root.assets.settings;

import root.assets.colors.BoardColors;
import root.assets.colors.ColorSet;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;

public class IOSettings {
    private final File propFile;

    public String getTexturePack() {
        try(InputStream input = new FileInputStream(propFile)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties.getProperty("texturePack", "cburnett");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getFlipToggle() {
        try(InputStream input = new FileInputStream(propFile)) {
            Properties properties = new Properties();
            properties.load(input);
            return Boolean.parseBoolean(properties.getProperty("flipToggle", "false"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ColorSet getBoardColors() {
        try(InputStream input = new FileInputStream(propFile)) {
            Properties properties = new Properties();
            properties.load(input);
            return BoardColors.getColors(properties.getProperty("boardColors", "option1"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTexturePack(String texturePack) {
        try(OutputStream output = new FileOutputStream(propFile)) {
            Properties properties = new Properties();
            properties.setProperty("texturePack", texturePack);
            properties.store(output, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setBoardColors(ColorSet colors) {
        try(OutputStream output = new FileOutputStream(propFile)) {
            Properties properties = new Properties();
            properties.setProperty("boardColors", colors.getStringVal());
            properties.store(output, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFlipToggle(boolean toggle) {
        try(OutputStream output = new FileOutputStream(propFile)) {
            Properties properties = new Properties();
            properties.setProperty("flipToggle", Boolean.toString(toggle));
            properties.store(output, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File getSettingsFile() {
        String root = Paths.get("").toAbsolutePath().toString();
        String[] fullPath = {root, "src", "root", "assets", "settings", "settings.properties"};
        return new File(String.join(File.separator, fullPath));
    }

    public IOSettings() {
        propFile = getSettingsFile();
    }
}
