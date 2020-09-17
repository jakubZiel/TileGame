package Utils;

import Entities.GameObject;

public class DirectionVector {

    public float xDirection;
    public float yDirection;

    public DirectionVector(GameObject enemy, GameObject player){

        setDirectionVector(enemy, player);
    }

    public DirectionVector(float xEnd, float yEnd, float xBeg, float yBeg){
        setDirectionVector(xEnd, yEnd, xBeg, yBeg);
    }

    public void setDirectionVector(GameObject enemy, GameObject player){

        xDirection = player.getX() - enemy.getX();
        yDirection = player.getY() - enemy.getY();

        float length = (float)(Math.pow(xDirection, 2) + Math.pow(yDirection, 2));
        length = (float)(Math.sqrt(length));

        xDirection /= length;
        yDirection /= length;
    }

    public void setDirectionVector(float xEnd, float yEnd, float xBeg, float yBeg){
        xDirection = xEnd - xBeg;
        yDirection = yEnd - yBeg;

        float length = (float)(Math.pow(xDirection, 2) + Math.pow(yDirection, 2));
        length = (float)(Math.sqrt(length));

        xDirection /= length;
        yDirection /= length;
    }


    public static float getDistance(float x1, float y1, float x2, float y2){
        return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
