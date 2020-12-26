package view.display;

import model.GamePackage.Handler;
import view.Menu.MenuDisplay;
import view.Menu.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class Display {
    private JFrame frame;
    private Canvas canvas;
    private String title;
    private  int width, height;
    private Handler handler;

    public Display(String title, int width, int height, Handler handler) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.handler = handler;

        createDisplay();
    }

    public void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(false);

        mp = new MenuPanel(handler);
        md = new MenuDisplay(handler, this);

        frame.add(canvas);
    }
    public MenuPanel mp;
    public MenuDisplay md;

    public Canvas getCanvas() {
        return canvas;
    }

    public void turnToMenu(){
        handler.getGame().getFrame().remove(handler.getGame().getDisplay().getCanvas());
        //handler.getGame().getFrame().add(mp);

        handler.getGame().getFrame().add(md.getMainPanel());
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
