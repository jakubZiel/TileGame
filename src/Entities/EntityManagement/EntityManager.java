package Entities.EntityManagement;

import Entities.*;
import Entities.Attacks.Projectile.FireBall;
import Entities.Attacks.Projectile.Projectile;
import Entities.Creature.Enemy;
import Entities.Creature.Player;
import Entities.Items.Boulder;
import Entities.Items.Gold;
import Entities.Items.Tree;
import GamePackage.Handler;
import Utils.DirectionVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;


//TODO add projectile handling

public class EntityManager {
    private Handler handler;
    private Player player;
    private ArrayList<GameObject> entities;
    private Comparator<GameObject> renderSorter = new Comparator<GameObject>() {
        @Override
        public int compare(GameObject o1, GameObject o2) {
            if (o1.getY() == o2.getY())
                return 0;
            else if (o1.getY() > o2.getY())
                return 1;
            else
                return -1;
        }
    };

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        entities = new ArrayList<>();
        this.player = player;
        addObjects();
    }

    private void addObjects(){
        addEntity(player);
        addTrees();
        addBoulders();
        addEnemies();
    }

    private void addTrees() {
        addEntity(new Tree(handler, 50, 50));
        addEntity(new Tree(handler, 150, 150));
        addEntity(new Tree(handler, 400, 200));
        addEntity(new Tree(handler, 150, 50));
    }

    private void addBoulders(){

        addEntity(new Boulder(handler, 500, 50));

    }

    private void addEnemies(){

        addEntity(new Enemy(handler, 500, 500, 100));
        addEntity(new Enemy(handler, 100, 500, 100));
    }

    public void tick(){
        for (int i = 0; i < entities.size(); i++) {
            GameObject e = entities.get(i);
            e.tick();
            checkIfRemove(e, i);
        }
    }

    private void checkIfRemove(GameObject e, int i){

        if (e instanceof Projectile){
            Projectile p = (Projectile) e;
            if (!p.isAirBorne()){
                entities.remove(e);
                i--;
            }
        }

        if (!e.isAlive()) {
            if (e instanceof Enemy)
                addEntity(new Gold(handler,e.getX(), e.getY()));

            entities.remove(i);
            i--;
        }else if (e instanceof Gold){
            Gold g = (Gold)e;
            if (g.getPickedUp()) {
                entities.remove(e);
                i--;
            }
        }
    }

    public void render(Graphics graphics){
        entities.sort(renderSorter);
        for (GameObject object : entities)
            object.render(graphics);
    }

    public void addEntity(GameObject entity){
        entities.add(entity);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<GameObject> getEntities(){
        return entities;
    }
}
