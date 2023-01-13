package root.ui.frames.menu_frame;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

import root.ui.GameManager;
import root.ui.frames.components.BaseFrame;
import root.ui.frames.components.BackgroundPanel;
import root.ui.graphics.GraphicsManager;
public class SettingsFrame extends BaseFrame {
    public boolean isFlipTogOn;
    public String[] choices = { "Cburnett", "Kilifiger", "Kosal", "Legipzig",
            "Maya", "Pirat", "Regular" };


    public SettingsFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        super(gameManager, graphicsManager);

        JPanel panel = new JPanel();
        panel.setBounds(graphicsManager.getGameBounds().height / 4, graphicsManager.getGameBounds().width / 4, graphicsManager.getGameBounds().width / 2, graphicsManager.getGameBounds().height/4);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel lbl = new JLabel("Select one of the possible choices and click OK");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;

        panel.add(lbl,c);

        //piece texture selection
        final JComboBox<String> cb = new JComboBox<>(choices);
        cb.setSelectedItem(choices[2]);
        cb.setMaximumSize(cb.getPreferredSize());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        panel.add(cb, c);

        JButton btn = new JButton("OK");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 0;
        panel.add(btn, c);
        setVisible(true);

        //flip toggle
        JLabel flipLabel = new JLabel("flips the board on the end of your move");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(flipLabel,c);

        JToggleButton flipTog = new JToggleButton("place holder"); //TODO make it so the button has an icon and not a string
        flipTog.setBounds(1000, 10, 40, 40);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        panel.add(flipTog,c);


        flipTog.addActionListener(e -> {
            flipButton();
        });

        //somthing to ass buttons to the top of the screen
        JPanel topBar = new JPanel();


        //back button
        JButton back = new JButton("back");

        back.addActionListener(e -> {
            SwingUtilities.getWindowAncestor((JComponent) e.getSource()).dispose();
            GameManager manager = new GameManager();
            manager.runMenu();
        });

        topBar.add(back);

        JButton save = new JButton("save");
        //save.setAlignmentX();
        topBar.add(save);

        save.addActionListener(e -> {
            saveSettings();
        });
        add(topBar);
        add(panel);

    }

    private void saveSettings(){
        try{
            FileWriter myWriter = new FileWriter("src/root/settings.txt");
            myWriter.write("file toggle:" + isFlipTogOn + "\n" + "texture: " + choices[2]);
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isFlipToggleOn() {
        return isFlipTogOn;
    }


    public void flipButton(){
        if(isFlipTogOn){
            isFlipTogOn = false;
            System.out.println("isFlipTogTrue has been made false");
        }else {
            isFlipTogOn = true;
            System.out.println("isFlipTogTrue has been made true");
        }

    }
}
