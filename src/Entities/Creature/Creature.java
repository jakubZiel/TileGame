package Entities.Creature;


import Entities.GameObject;
import GamePackage.Handler;
import Tiles.Tile;
import gfx.TextureProcessing.Animation;

public abstract class Creature extends GameObject {

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_WIDTH = 225/4,
                            DEFAULT_HEIGHT = 225/4;

    protected float speed;

    protected float xMove;
    protected float yMove;

    protected Animation upAnim;
    protected Animation downAnim;
    protected Animation leftAnim;
    protected Animation rightAnim;


    public Creature(Handler handler, float x, float y, int health, int width, int height){
        super(handler, x, y, width, height);
        this.health = health;
        speed = DEFAULT_SPEED;

        this.xMove = 0;
        this.yMove = 0;

    }

    protected boolean collisionWithTile(int x, int y){

        return !handler.getWorld().getTile(x,y).isWalkable();
    }

    public void move(){
        if (!checkEntityCollision(xMove, 0f))
            moveX();
        if (!checkEntityCollision(0f, yMove))
            moveY();
    }

    public void moveX(){

        int tileY = (int)((y + bounds.y) / Tile.TILE_HEIGHT);
        int tileY_2 = (int)((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT);
        int tileX;

        if (xMove > 0)
            tileX = (int) ((x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH);
        else
            tileX = (int) ((x + xMove + bounds.x) / Tile.TILE_WIDTH);

        if (!collisionWithTile(tileX, tileY) && !(collisionWithTile(tileX, tileY_2))) x += xMove;
    }

    public void moveY(){
        if(yMove < 0) {
            int ty = (int) ((y + yMove + bounds.y) / Tile.TILE_HEIGHT);

            if(!collisionWithTile((int) ((x + bounds.x) / Tile.TILE_WIDTH), ty) &&
                    !collisionWithTile((int) ((x + bounds.x + bounds.width) / Tile.TILE_WIDTH), ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }

        }else if(yMove > 0){//Down
            int ty = (int) ((y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT);

            if(!collisionWithTile((int) ((x + bounds.x) / Tile.TILE_WIDTH), ty) &&
                    !collisionWithTile((int) ((x + bounds.x + bounds.width) / Tile.TILE_WIDTH), ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }

        }
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }



}
