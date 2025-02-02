package model.Entities;

import model.Entities.Attacks.Projectile.Projectile;
import model.GamePackage.Handler;
import model.Tiles.Tile;
import Utils.MyPoint;
import view.gfx.HUD.HealthBar;

import java.awt.*;
import java.util.ArrayList;


public abstract class GameObject {
    protected float x, y;
    protected int width, height;
    protected Handler handler;
    protected int health;
    protected boolean alive;


    protected Rectangle bounds;
    protected GameObject collisionWith;
    protected HealthBar healthBar;


    public GameObject(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.alive = true;

        health = 10;

        bounds = new Rectangle(0, 0, width, height);

    }

    public abstract void render(Graphics graphics);

    public abstract void tick();

    protected  void drawCollisionModel(Graphics graphics){
        graphics.setColor(Color.red);
        graphics.drawRect((int)(x + bounds.x - handler.getGameCamera().getXOffset()), (int)(y + bounds.y - handler.getGameCamera().getYOffset()),bounds.width, bounds.height);
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public boolean checkEntityCollision(float xOffset, float yOffset){

            ArrayList<Point> neighbourObjects = handler.getQuadTreeModel().allPointsInRectangle(
                    new Rectangle((int) (x - Tile.TILE_WIDTH), (int) (y - Tile.TILE_HEIGHT), (int) (3 * Tile.TILE_WIDTH), (int) (3 * Tile.TILE_HEIGHT)));

            if (neighbourObjects == null){
                collisionWith = null;
                return false;
            }

            for (Point object : neighbourObjects) {

                MyPoint p = (MyPoint) object;

                if (p.object.equals(this))
                    continue;
                if (p.object.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {

                    if (this instanceof Projectile) {
                        Projectile projectile = (Projectile) this;

                        if (projectile.getShooter() == p.object)
                            continue;
                        else {
                            collisionWith = p.object;
                            p.object.hurt(10);
                            return true;
                        }
                    }
                    collisionWith = p.object;
                    return true;
                }
            }

        collisionWith = null;
        return  false;
    }

    public void hurt(int damage){
        health -= damage;
        if (health <= 0) {
            alive = false;
        }
    }

    public abstract void die();

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    protected void setBounds(int x, int y, int width, int height){

        bounds.x = x;
        bounds.y = y;
        bounds.width = width;
        bounds.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getCenterX(){
        return (int)(x + width / 2);
    }

    public int getCenterY(){
        return (int)(y + height / 2);
    }

    public abstract boolean shouldNotExist(ArrayList<GameObject> entities);

}
