package view.display;

import model.Entities.Creature.Player;
import model.Entities.GameObject;
import model.GamePackage.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityDisplayManager {

    private Handler handler;
    private ArrayList<GameObject> entities;
    private Player player;

    public EntityDisplayManager(Handler handler){
        this.handler = handler;

        player = handler.getEntityManager().getPlayer();
        entities = handler.getEntityManager().getEntities();
    }

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

    public void render(Graphics graphics){
        entities.sort(renderSorter);
        for (GameObject object : entities)
            object.render(graphics);

        player.hud.render(graphics);
        player.getInventory().render(graphics);
    }
}
