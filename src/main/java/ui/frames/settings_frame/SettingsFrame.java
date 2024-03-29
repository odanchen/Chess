package ui.frames.settings_frame;

import assets.colors.BoardColors;
import assets.settings.IOSettings;
import ui.GameManager;
import ui.frames.components.BaseFrame;
import ui.frames.components.CustomButton;
import ui.graphics.GraphicsManager;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;

@SuppressWarnings("SpellCheckingInspection")
public class SettingsFrame extends BaseFrame {
    private final GridBagConstraints gridBag;
    private final JPanel panelHolder;
    private JToggleButton flipToggleButton;
    private JComboBox<String> textureChoice;
    private JComboBox<String> colorChoice;
    private JComboBox<String> lengthChoice;

    public SettingsFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        super(gameManager, graphicsManager);
        gridBag = new GridBagConstraints();

        panelHolder = new JPanel();
        panelHolder.setOpaque(false);
        panelHolder.setBounds(graphicsManager.getGameBounds().width * 8 / 15, graphicsManager.getGameBounds().width / 4, graphicsManager.getGameBounds().width / 6, graphicsManager.getGameBounds().height / 3);
        panelHolder.setLayout(new GridBagLayout());

        addTextureChoice();
        addToggleFlipButton();
        addColorChoice();
        addSaveButton();
        addMenuButton();
        addLengthChoice();

        add(panelHolder);
        addBackgroundPanel("settingsMenu");
        validate();
    }

    private String getPieceFolder() {
        return (String) textureChoice.getSelectedItem();
    }

    private boolean getToggleStatus() {
        return flipToggleButton.getModel().isSelected();
    }

    private String getColorSet() {
        return (String) colorChoice.getSelectedItem();
    }

    private String getSelectedLength() {
        return (String) lengthChoice.getSelectedItem();
    }

    private void addToPanelHolder(int gridY, JComponent component) {
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weighty = 2;
        gridBag.weightx = 0.5;
        gridBag.gridx = 2;
        gridBag.gridy = gridY;
        panelHolder.add(component, gridBag);
    }

    private void addLengthChoice() {
        lengthChoice = new JComboBox<>(new String[]
                {"Long", "Medium", "Short", "Infinite"});
        lengthChoice.setSelectedItem(new IOSettings().getGameLength());
        addToPanelHolder(3, lengthChoice);
    }

    private String fixTextureFolder(String folder) {
        char[] arr = folder.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

    private void addTextureChoice() {
        textureChoice = new JComboBox<>(new String[]
                {"Cburnett", "Kilifiger", "Kosal", "Leipzig", "Maya", "Pirat", "Regular"});
        textureChoice.setSelectedItem(fixTextureFolder(new IOSettings().getTexturePack()));
        addToPanelHolder(0, textureChoice);
    }

    private void addColorChoice() {
        colorChoice = new JComboBox<>(new String[]{"Brown", "Green", "Gray"});
        colorChoice.setSelectedItem(new IOSettings().getBoardColors().getStringVal());
        addToPanelHolder(1, colorChoice);
    }

    private void addSaveButton() {
        Dimension size = graphicsManager.getTextButtonDimension();
        int buttonY = (int) (graphicsManager.getGameBounds().getSize().getHeight() * 3 / 4);
        CustomButton saveButton = new CustomButton("saveButtonReleased", graphicsManager, size);
        saveButton.addActionListener(e -> saveSettings());
        addButton(saveButton, graphicsManager.getCenterOfScreenX(size.width), buttonY);
    }

    private void addMenuButton() {
        Dimension size = graphicsManager.getTextButtonDimension();
        Dimension scene = graphicsManager.getFrameDimension();
        CustomButton menuButton = new CustomButton("menuButtonReleased", graphicsManager, size);
        menuButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor((JComponent) e.getSource()).dispose();
            gameManager.runMenu();
        });
        addButton(menuButton, scene.width / 20, scene.height / 20);
    }

    private void addButton(JButton button, int x, int y) {
        JPanel buttonsPanel = new JPanel();
        Dimension size = graphicsManager.getTextButtonDimension();
        buttonsPanel.setBounds(x, y, size.width, (int) (size.height * 1.5));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(button);
        add(buttonsPanel);
        validate();
    }

    private void addToggleFlipButton() {
        flipToggleButton = new JToggleButton("flip toggle");
        flipToggleButton.setSelected(new IOSettings().getFlipToggle());
        flipToggleButton.addActionListener(e -> flipButton());
        addToPanelHolder(2, flipToggleButton);
    }

    private void saveSettings() {
        new IOSettings().setProperties(getPieceFolder(), getToggleStatus(), BoardColors.getColors(getColorSet()), getSelectedLength());
        graphicsManager.refreshTextures();
    }

    public void flipButton() {
        flipToggleButton.setSelected(flipToggleButton.getModel().isSelected());
    }
}
