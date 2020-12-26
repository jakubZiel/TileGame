package view.gfx.HUD;

import model.Entities.GameObject;
import model.GamePackage.Handler;

import java.awt.*;

public class HealthBar {
    private GameObject object;
    private int maxHealth;
    private float percentage;
    private int barWidth;
    private int barHeight;
    private Handler handler;


    public HealthBar(GameObject object, Handler handler){
        this.object = object;
        this.handler = handler;
        this.maxHealth = object.getHealth();
        percentage = 1;
        barWidth = object.getWidth();
        barHeight = 5;
    }

    public float getPercentage(){
        return (float)this.object.getHealth() / maxHealth;
    }

    public float getBarPositionX(){
        return object.getX() + (float) object.getWidth() / 2 - (float) (barWidth * percentage) / 2 - handler.getXOffset() ;
    }

    public float getBarPositionY(){
        return object.getY() - 5 - handler.getYOffset();
    }

    public void tick(){
        percentage = (float) object.getHealth() / maxHealth;
    }

    public void render(Graphics graphics){
        graphics.setColor(Color.RED);
        graphics.fillRect((int) getBarPositionX(),(int) getBarPositionY(),  (int) (barWidth * percentage), barHeight);
    }

}
