package view.Menu;

import model.GamePackage.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuPanel extends JPanel {

    private JButton startButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton resumeButton;
    private JFileChooser fileChooser;

    private Handler handler;

    public MenuPanel (Handler handler){

        super(null);
        this.handler = handler;

        addButtons();
        addFileChooser();
        addButtonListeners(this);
    }

    private void addButtons(){
        startButton = new JButton();
        saveButton = new JButton();
        loadButton = new JButton();
        resumeButton = new JButton();

        setSize(800, 540);

        startButton.setSize(80,40);
        startButton.setLocation(getWidth() / 2 - startButton.getWidth() / 2,50);

        resumeButton.setSize(80,40);
        resumeButton.setLocation(getWidth() / 2 - resumeButton.getWidth() / 2,100);

        saveButton.setSize(80,40);
        saveButton.setLocation(getWidth() / 2 - saveButton.getWidth() / 2,150);

        loadButton.setSize(80,40);
        loadButton.setLocation(getWidth() / 2 - loadButton.getWidth() / 2,200);

        add(startButton);
        add(saveButton);
        add(resumeButton);
        add(loadButton);
    }

    private void addButtonListeners(JPanel thisPanel){

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (handler.getWorld()) {
                    handler.getKeyManager().getKeys()[KeyEvent.VK_P] = false;
                    handler.getFrame().remove(thisPanel);
                    handler.getFrame().add(handler.getGame().getDisplay().getCanvas());
                    handler.getFrame().setVisible(true);
                    handler.getFrame().requestFocus();
                    handler.getWorld().notify();
                }
            }
        });
    }

    private void addFileChooser(){


    }
}
