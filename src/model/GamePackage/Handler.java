package model.GamePackage;

import model.Entities.EntityManagement.EntityManager;
import controller.KeyManager;
import controller.MouseManager;
import QuadTree.QuadTree;
import model.WorldGenertaion.World;
import view.gfx.GameCamera.GameCamera;

import javax.swing.*;

public class Handler {

    private Game game;
    private World world;

    public Handler(Game game) {
        this.game = game;
    }

    public GameCamera getGameCamera(){
        return game.getGameCam();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public EntityManager getEntityManager(){
        return getGame().getMainState().getEntityManager();
    }

    public QuadTree getQuadTreeModel(){
        return getEntityManager().getQuadTreeModel();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public JFrame getFrame(){
        return game.getFrame();
    }

    public float getXOffset(){
        return getGameCamera().getXOffset();
    }

    public float getYOffset(){
        return getGameCamera().getYOffset();
    }
}

