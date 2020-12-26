package model.Entities.Items;

import model.Entities.GameObject;
import model.GamePackage.Handler;
import view.gfx.TextureProcessing.Animation;
import view.gfx.TextureProcessing.Assets;

import java.awt.*;
import java.util.ArrayList;

public class Gold  extends Item {

    private boolean pickedUp;
    private Animation goldAnim;
    private int value;

    public Gold(Handler handler, float x, float y, int value){
        super(handler, x, y, Item.DEFAULT_WIDTH, Item.DEFAULT_HEIGHT);
        pickedUp = false;
        goldAnim = new Animation(Assets.goldImages, 150);
        this.count = value;
        setId(1);
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
