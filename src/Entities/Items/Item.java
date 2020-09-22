package Entities.Items;

import Entities.GameObject;
import GamePackage.Handler;

import java.util.ArrayList;

public abstract class Item extends GameObject {
    public static final int DEFAULT_WIDTH = 225/8,
            DEFAULT_HEIGHT = 225/8;



    public Item(Handler handler, float x, float y, int width, int height){

        super(handler, x, y, width, height);
    }

    @Override
    public boolean shouldNotExist(ArrayList<GameObject> entities) {

        if (!isAlive()){
            entities.remove(this);

            return true;
        }else
            return false;



    }
}
