package Utils;

import model.Entities.GameObject;

import java.awt.*;

public class MyPoint extends Point {
   public GameObject object;

    public MyPoint(GameObject object) {
        super(object.getCenterX(), object.getCenterY());
        this.object = object;
    }

    public GameObject getObject() {
        return object;
    }
}
