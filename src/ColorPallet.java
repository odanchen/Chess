package root.ui.frames.menu_frame;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

import root.ui.GameManager;
import root.ui.frames.BaseFrame;
import root.ui.frames.menu_frame.panels.BackgroundPanel;
import root.ui.graphics.GraphicsManager;
public class SettingsFrame extends BaseFrame {
    public boolean isFlipTogOn;
    public SettingsFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        super(gameManager, graphicsManager);

        JPanel panel = new JPanel();
        panel.setBounds(graphicsManager.getGameBounds().height/4,graphicsManager.getGameBounds().width/4,graphicsManager.getGameBounds().width/2,graphicsManager.getGameBounds().height/2);
        //panel.setBackground(Color.blue);
        JLabel lbl = new JLabel("Select one of the possible choices and click OK");
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lbl);

        //piece texture selection
        String[] choices = { "Cburnett", "Kilifiger", "Kosal", "Legipzig",
                "Maya", "Pirat", "Regular" };
        final JComboBox<String> cb = new JComboBox<>(choices);
        cb.setSelectedItem(choices[2]);
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(cb);

        JButton btn = new JButton("OK");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btn);
        setVisible(true);


        //flip toggle
        JLabel flipLabel = new JLabel("flips the board on the end of your move");
        flipLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JToggleButton flipTog = new JToggleButton("place holder"); //TODO make it so the button has an icon and not a string
        flipTog.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(flipLabel);
        panel.add(flipTog);


        flipTog.addActionListener(e->{
            flipButton();
        });

        //back button
        JButton back = new JButton("back");

        back.addActionListener(e->{
            SwingUtilities.getWindowAncestor((JComponent) e.getSource()).dispose();
            GameManager manager = new GameManager();
            manager.runMenu();
        });

        panel.add(back);

        JButton save = new JButton("save");
        panel.add(save);

        save.addActionListener(e->{
            saveSettings();
        });

        add(panel);

    }

    private void saveSettings(){
        try{
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write("file toggle:" + isFlipTogOn + "\n" + "texture: ");
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

