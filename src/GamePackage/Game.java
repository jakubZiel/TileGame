package GamePackage;

import GameInput.MouseManager;
import States.GameState;
import GameInput.KeyManager;
import States.MenuState;
import States.State;
import display.Display;
import gfx.TextureProcessing.Assets;
import gfx.GameCamera.GameCamera;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private Display display;
    public int width, height;
    private boolean running = false;
    public String title;

    private BufferStrategy buffStrat;
    private Graphics graphics;
    private KeyManager keyManager;
    private MouseManager mouseManager;
    //Camera
    private GameCamera gameCam;

    Thread thread;

    public State MenuState;
    public State GameState;

    private Handler handler;

    public Game(int width, int height) {
        keyManager = new KeyManager();
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
        display = new Display("MyGame" ,width ,height);
        Assets.init();
        handler = new Handler(this);
        gameCam = new GameCamera(handler, 0, 0);

        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        MenuState = new MenuState(handler);
        GameState = new GameState(handler);

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

            if (delta >= 1) {
                tick();
                render();
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
        if(!running)return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }   //inside run()

    public synchronized void start(){
        if(running)return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }   //launches game in Launch()

    public void render(){
        buffStrat = display.getCanvas().getBufferStrategy();

        if(buffStrat == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = buffStrat.getDrawGraphics();
        graphics.clearRect(0,0, width, height);

        if(State.getState() != null) State.getState().render(graphics);

        buffStrat.show();
        graphics.dispose();
    }   //inside gameLoop()

    public void tick(){
        keyManager.tick();
        mouseManager.tick();
        if(State.getState() != null) State.getState().tick();
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
}
