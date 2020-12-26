package model.States;

import model.Entities.EntityManagement.EntityManager;
import model.Entities.Creature.Player;
import model.GamePackage.Handler;
import model.WorldGenertaion.World;
import view.Menu.MenuDisplay;
import view.display.EntityDisplayManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameState extends State {

    private World world;
    private EntityManager entityManager;

    private boolean gamePaused = false;
    public MenuDisplay menuDisplay;
    public JFrame frame = new JFrame();

    private EntityDisplayManager displayManager;

    @Override
    public void tick() {
        checkIfPaused();

        world.tick();
        entityManager.tick();
    }

    @Override
    public void render(Graphics graphics) {
        world.render(graphics);
        entityManager.render(graphics);
    }

    public GameState(Handler g){
        super(g);
        world = new World("./res/worlds/world1", g);
        handler.setWorld(world);
        entityManager = new EntityManager(handler, new Player(handler ,100, 100, 50));

        entityManager.getPlayer().setX(world.getSpawnX());
        entityManager.getPlayer().setY(world.getSpawnY());

        //menuDisplay = new MenuDisplay(this.handler);
    }

    private void checkIfPaused(){

            if (handler.getKeyManager().getKeys()[KeyEvent.VK_P]) {
                    synchronized (world){
                        try {
                            handler.getGame().setPaused(true);
                            /*
                            menuDisplay.setLocation(handler.getGame().getFrame().getLocation());
                            menuDisplay.setSize(handler.getGame().getFrame().getSize());
                            menuDisplay.setVisible(true);
                            handler.getFrame().setVisible(true);
                            */

                            handler.getGame().getDisplay().turnToMenu();
                            world.wait();


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }

    public World getWorld() {
        return world;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }
}
