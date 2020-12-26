package view.Menu;

import model.GamePackage.Handler;
import view.display.Display;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class MenuDisplay {
    public JPanel mainPanel;
    private JButton startButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton resumeButton;
    private JFileChooser fileChooser;
    private Handler handler;
    private Display display;

    //TODO listeners should resume, start ,load and save current game state
    public MenuDisplay(Handler handler, Display display){
        this.handler = handler;

        this.display = display;
        addButtonListeners(mainPanel);
        setFileChooser();

    }

    public void addButtonListeners(JPanel panel){
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (handler.getWorld()){
                    handler.getKeyManager().getKeys()[KeyEvent.VK_P] = false;
                    handler.getFrame().remove(panel);
                    handler.getFrame().add(handler.getGame().getDisplay().getCanvas());
                    handler.getFrame().setVisible(true);
                    handler.getFrame().requestFocus();
                    handler.getWorld().notify();
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("file " + fileChooser.getSelectedFile());
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setFileChooser(){

    }


}
