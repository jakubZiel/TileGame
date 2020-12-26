package model.GamePackage;

import controller.MouseManager;
import model.States.GameState;
import controller.KeyManager;
import model.States.MenuState;
import model.States.State;
import view.display.Display;
import view.display.DisplayManager;
import view.gfx.TextureProcessing.Assets;
import view.gfx.GameCamera.GameCamera;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private String mode;

    private Display display;
    public int width, height;
    private boolean running = false;
    public String title;

    private BufferStrategy buffStrat;
    private Graphics graphics;
    private KeyManager keyManager;
    private MouseManager mouseManager;
    private DisplayManager displayManager;

    boolean testMode = true;

    //Camera
    private GameCamera gameCam;

    Thread thread;

    public State MenuState;
    public State GameState;

    private Handler handler;

    private boolean paused;

    public Game(int width, int height, String mode) {
        this.mode = mode;
        mouseManager = new MouseManager();
        this.width = width;
        this.height = height;
        this.title = "MyGame";
    }

    @Override
    public void run() {
        init();
        gameLoop();
        stop();
    }   //main game function

    private void init() {
        

        Assets.init();
        handler = new Handler(this);
        keyManager = new KeyManager(handler);
        display = new Display("MyGame" ,width ,height, handler);

        gameCam = new GameCamera(handler, 0, 0);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        MenuState = new MenuState(handler);
        GameState = new GameState(handler);


        displayManager = new DisplayManager(handler);

        State.setState(GameState);
    }   //inside run()

    public void gameLoop(){

        int fps = 120;

        double timePerTick = 1000000000.0f/ fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){


            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            //after notifying the game thread delta gets really big,
            // that ends with effect accelerated time, if we set delta = 0 we eliminate that threat

            if (paused) {
                delta = 0;
                paused = false;
            }

            if (delta >= 1) {
                tick();
                displayManager.render();
                ticks++;
                delta--;
            }


            if(timer >= 1000000000) {
                System.out.println("ticks: " + ticks);
                timer = 0;
                ticks = 0;
            }
        }
    }   //inside run()

    public synchronized void stop(){
        if(!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }   //inside run()

    public synchronized void start(){
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }   //launches game in Launch()

    public void tick(){
        keyManager.tick();
        mouseManager.tick();
        if(State.getState() != null)
            State.getState().tick();
    }   //inside gameLoop()

    //getters and setters
    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public JFrame getFrame(){return display.getFrame();}

    public GameCamera getGameCam() {return gameCam; }

    public GameState getMainState(){
        return (GameState) GameState;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public Thread getThread() {
        return thread;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public Display getDisplay(){
        return display;
    }
}
