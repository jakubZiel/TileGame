package States;

import Entities.EntityManagement.EntityManager;
import Entities.Creature.Player;
import GamePackage.Handler;
import WorldGenertaion.World;
import gfx.TextureProcessing.Assets;
import gfx.TextureProcessing.Screenshot;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameState extends State {

    private World world;
    private EntityManager entityManager;
    private BufferedImage pauseBlurredScreen;

    private boolean gamePaused = false;

    private long pauseTimer, pauseLastTime = pauseTimer;
    private final long pauseCooldown = 200;

    @Override
    public void tick() {
        checkIfPaused();
        if (!gamePaused) {
            changeState();
            world.tick();
            entityManager.tick();
        }
    }

    @Override
    public void render(Graphics graphics) {

        world.render(graphics);
        entityManager.render(graphics);

        if (gamePaused)
            renderPause(graphics);
    }

    public GameState(Handler g){
        super(g);
        world = new World("/media/jakub/SDHC/IdeaProjects/GameTutorial/res/worlds/world1", g);
        handler.setWorld(world);
        entityManager = new EntityManager(handler, new Player(handler ,100, 100, 50));

        entityManager.getPlayer().setX(world.getSpawnX());
        entityManager.getPlayer().setY(world.getSpawnY());
    }

    private void changeState(){
        if (isLeftPressed() && isRightPressed()) State.setState(handler.getGame().MenuState);
    }

    private void checkIfPaused(){

        pauseTimer = System.currentTimeMillis();
        if (pauseTimer > pauseLastTime + pauseCooldown) {

            if (handler.getKeyManager().getKeys()[KeyEvent.VK_P] && !gamePaused) {
                gamePaused = true;
                pauseBlurredScreen = Screenshot.takeScreenShotOfFrame(handler.getFrame());
                pauseLastTime = System.currentTimeMillis();

            } else if (handler.getKeyManager().getKeys()[KeyEvent.VK_P] && gamePaused) {
                gamePaused = false;
                pauseLastTime = System.currentTimeMillis();
            }
        }
    }

    private void renderPause(Graphics graphics){
        int imageW = 200, imageH = 200;
        int rectW = handler.getGame().width, rectH = handler.getGame().height;

        graphics.setColor(Color.WHITE);
        graphics.drawImage(pauseBlurredScreen, -10, -10, handler.getFrame().getWidth() + 20, handler.getFrame().getHeight(), null);
        graphics.drawImage(Assets.pause, rectW / 2 - imageW /2 ,rectH / 2 - imageH / 2 - 25, imageW, imageH, null);
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }

    public World getWorld() {
        return world;
    }
}
