package model.Entities.EntityManagement;

import model.Entities.*;
import model.Entities.Creature.Enemy;
import model.Entities.Creature.Player;
import model.Entities.Items.Boulder;
import model.Entities.Items.Tree;
import model.GamePackage.Handler;
import QuadTree.QuadTree;
import Utils.MyPoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;


public class EntityManager {
    private Handler handler;
    private Player player;
    private ArrayList<GameObject> entities;
    private QuadTree quadTreeModel;

    private Comparator<GameObject> renderSorter = new Comparator<>() {
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
        this.quadTreeModel = new QuadTree(10, new Rectangle(0, 0, this.handler.getGame().width, this.handler.getGame().height));
        addObjects();
    }

    public void tick(){
        fillQuadTree();

        for (int i = 0; i < entities.size(); i++) {

            GameObject e = entities.get(i);
            e.tick();

            if (e.shouldNotExist(entities))
                i--;
        }
    }

    public void fillQuadTree(){
        quadTreeModel.clear();

        for (GameObject g : entities)
            quadTreeModel.insert(new MyPoint(g));
    }

    public void render(Graphics graphics){
        entities.sort(renderSorter);
        for (GameObject object : entities)
            object.render(graphics);

        player.hud.render(graphics);
        player.getInventory().render(graphics);
    }

    public void addEntity(GameObject entity){
        entities.add(entity);
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

    public Player getPlayer() {
        return player;
    }

    public ArrayList<GameObject> getEntities(){
        return entities;
    }

    public QuadTree getQuadTreeModel() {
        return quadTreeModel;
    }
}
