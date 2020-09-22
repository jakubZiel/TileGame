package Entities.Attacks.Projectile;

import Entities.Creature.Creature;
import Entities.GameObject;
import GamePackage.Handler;
import Tiles.Tile;
import Utils.DirectionVector;
import gfx.TextureProcessing.Animation;

import java.util.ArrayList;

public abstract class Projectile  extends Creature {
    //TODO projectile doesnt work
    protected int damage;
    protected int range;
    protected DirectionVector directionVector;
    protected float shootFromX;
    protected float shootFromY;

    protected boolean airBorne;

    protected Creature shooter;


    protected Animation projAnimation;

    public Projectile(Handler handler, float x, float y, int width, int height, Creature shooter) {
        super(handler, x, y, Integer.MAX_VALUE, width, height);
        this.airBorne = true;
        this.shootFromX = x;
        this.shootFromY = y;
        this.damage = 0;
        setShooter(shooter);
    }

    public void setDirectionVector(DirectionVector directionVector){
        this.directionVector = directionVector;
    }

    public void move(){

        if (DirectionVector.getDistance(x, y, shootFromX, shootFromY) > range) airBorne = false;

        if (!checkEntityCollision(xMove, 0f))
            moveX();
        else {
            collisionWith.hurt(damage);
            airBorne = false;
        }
        if (!checkEntityCollision(0f, yMove))
            moveY();
        else {
            collisionWith.hurt(damage);
            airBorne = false;
        }
    }

    public void moveX(){

        int tileY = (int)((y + bounds.y) / Tile.TILE_HEIGHT);
        int tileY_2 = (int)((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT);
        int tileX;

        if (xMove > 0)
            tileX = (int) ((x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH);
        else
            tileX = (int) ((x + xMove + bounds.x) / Tile.TILE_WIDTH);

        if (!collisionWithTile(tileX, tileY) && !(collisionWithTile(tileX, tileY_2)))
            x += xMove;
        else
            airBorne = false;
    }

    public void moveY(){
        if(yMove < 0) {
            int ty = (int) ((y + yMove + bounds.y) / Tile.TILE_HEIGHT);

            if(!collisionWithTile((int) ((x + bounds.x) / Tile.TILE_WIDTH), ty) &&
                    !collisionWithTile((int) ((x + bounds.x + bounds.width) / Tile.TILE_WIDTH), ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
                airBorne = false;
            }

        }else if(yMove > 0){//Down
            int ty = (int) ((y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT);

            if(!collisionWithTile((int) ((x + bounds.x) / Tile.TILE_WIDTH), ty) &&
                    !collisionWithTile((int) ((x + bounds.x + bounds.width) / Tile.TILE_WIDTH), ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
                airBorne = false;
            }
        }
    }

    public boolean isAirBorne() {
        return airBorne;
    }

    public void setAirBorne(boolean airBorne) {
        this.airBorne = airBorne;
    }

    public void setShooter(Creature object){
        this.shooter = object;
    }

    public Creature getShooter() {
        return shooter;
    }

    @Override
    public boolean shouldNotExist(ArrayList<GameObject> entities){

            if (!isAirBorne()){
                entities.remove(this);
                return true;
            }else
                return false;
    }
}
