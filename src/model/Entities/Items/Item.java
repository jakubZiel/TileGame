package model.Entities.Items;

import model.Entities.GameObject;
import model.GamePackage.Handler;

import java.util.ArrayList;

public abstract class Item extends GameObject {
    public static final int DEFAULT_WIDTH = 225/8,
            DEFAULT_HEIGHT = 225/8;

    private int id;

    public Item(Handler handler, float x, float y, int width, int height){

        super(handler, x, y, width, height);
    }

    protected int count = 1;

    @Override
    public boolean shouldNotExist(ArrayList<GameObject> entities) {

        if (!isAlive()){
            entities.remove(this);
            return true;
        }else
            return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void increaseCount(int increase){
        count += increase;
    }

    public int getCount(){
        return this.count;
    }


}
