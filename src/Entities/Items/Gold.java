package Entities.Items;

import Entities.GameObject;
import GamePackage.Handler;
import gfx.TextureProcessing.Animation;
import gfx.TextureProcessing.Assets;

import java.awt.*;
import java.util.ArrayList;

public class Gold  extends Item {

    private boolean pickedUp;
    private Animation goldAnim;

    public Gold(Handler handler, float x, float y){
        super(handler, x, y, Item.DEFAULT_WIDTH, Item.DEFAULT_HEIGHT);
        pickedUp = false;
        goldAnim = new Animation(Assets.goldImages, 150);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(goldAnim.getCurrentFrame(),(int) (x - handler.getGame().getGameCam().getXOffset()), (int) (y - handler.getGame().getGameCam().getYOffset()), width, height, null );
    }

    @Override
    public void tick() {

        tickAnimations();

    }

    private void tickAnimations(){
        goldAnim.tick();
    }

    public void setPickedUp(){
        pickedUp = true;
    }

    public boolean getPickedUp(){
        return pickedUp;
    }

    @Override
    public void die() {

    }

    @Override
    public boolean shouldNotExist(ArrayList<GameObject> entities) {
        if (pickedUp) {
            entities.remove(this);
            return true;
        }else
            return false;
    }
}
