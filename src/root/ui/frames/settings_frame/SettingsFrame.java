package root.ui.frames.settings_frame;

import javax.swing.*;
import java.awt.*;

import root.assets.settings.IOSettings;
import root.ui.GameManager;
import root.ui.frames.components.BaseFrame;
import root.ui.graphics.GraphicsManager;

public class SettingsFrame extends BaseFrame {
    private JToggleButton flipToggleButton;
    private JComboBox<String> textureChoice;

    private JComboBox<String> colorChoice;

    private final GridBagConstraints gridBag;
    private final JPanel panelHolder;

    private String getPieceFolder() {
        return (String) textureChoice.getSelectedItem();
    }

    private boolean getToggleStatus() {
        return flipToggleButton.getModel().isSelected();
    }

    private void addToPanelHolder(double weightX, int gridX, int gridY, JComponent component) {
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = weightX;
        gridBag.gridx = gridX;
        gridBag.gridy = gridY;
        panelHolder.add(component, gridBag);
    }

    private void addTextureChoice() {
        textureChoice = new JComboBox<>(new String[]
                {"cburnett", "kilifiger", "kosal", "leipzig", "maya", "pirat", "regular"});
        textureChoice.setSelectedItem(new IOSettings().getTexturePack());
        addToPanelHolder(0.5, 2, 0, textureChoice);
    }

    private void addToggleFlipButton() {
        flipToggleButton = new JToggleButton("flip toggle");
        flipToggleButton.setSelected(new IOSettings().getFlipToggle());
        flipToggleButton.addActionListener(e -> flipButton());
        addToPanelHolder(0.5, 2, 1, flipToggleButton);
    }

    public SettingsFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        super(gameManager, graphicsManager);
        gridBag = new GridBagConstraints();

        panelHolder = new JPanel();
        panelHolder.setBounds(graphicsManager.getGameBounds().height / 4, graphicsManager.getGameBounds().width / 4, graphicsManager.getGameBounds().width / 2, graphicsManager.getGameBounds().height / 4);
        panelHolder.setLayout(new GridBagLayout());

        JLabel lbl = new JLabel("Piece texture pack: ");
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 0.5;
        gridBag.gridx = 1;
        gridBag.gridy = 0;

        panelHolder.add(lbl, gridBag);

        //piece texture selection
        addTextureChoice();

        //flip toggle
        JLabel flipLabel = new JLabel("flips the board on the end of your move");
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 0.5;
        gridBag.gridx = 1;
        gridBag.gridy = 1;
        panelHolder.add(flipLabel, gridBag);

        addToggleFlipButton();

        JLabel saveL = new JLabel("Saves your settings");
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 0.5;
        gridBag.gridx = 1;
        gridBag.gridy = 2;
        panelHolder.add(saveL, gridBag);


        JButton saveB = new JButton("save");
        //save.setAlignmentX();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 0.5;
        gridBag.gridx = 2;
        gridBag.gridy = 2;
        panelHolder.add(saveB, gridBag);


        saveB.addActionListener(e -> saveSettings());

        //somthing to ass buttons to the top of the screen
        JPanel topBar = new JPanel();
        topBar.setBounds(10, 10, 60, 60);
        topBar.setBackground(Color.blue);
        topBar.setVisible(true);

        //back button
        JButton back = new JButton("back");

        back.addActionListener(e -> {
            SwingUtilities.getWindowAncestor((JComponent) e.getSource()).dispose();
            gameManager.runMenu();
        });

        topBar.add(back);

        add(topBar);
        add(panelHolder);
        validate();
    }

    private void saveSettings() {
        new IOSettings().setProperties(getPieceFolder(), getToggleStatus(), null);
        graphicsManager.refreshTextures();
    }

    public void flipButton() {
        flipToggleButton.setSelected(flipToggleButton.getModel().isSelected());
    }
}
