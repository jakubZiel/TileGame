package Entities.Attacks.Projectile;

import Entities.Creature.Creature;
import GamePackage.Handler;
import Utils.DirectionVector;
import gfx.TextureProcessing.Animation;
import gfx.TextureProcessing.Assets;

import java.awt.*;

//TODO fireball should not block with entity that produces it
//TODO  fireball should have cooldown

public class FireBall extends Projectile {



    public FireBall(Handler handler, float x, float y, int width, int height, DirectionVector directionVector, Creature shooter) {
        super(handler, x, y, width, height, shooter);
        setDirectionVector(directionVector);
        projAnimation = new Animation(Assets.fireBall, 100);
        setMovementSpeed();
        this.range = Integer.MAX_VALUE;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(projAnimation.getCurrentFrame(), (int) (x - handler.getGame().getGameCam().getXOffset()), (int) (y - handler.getGame().getGameCam().getYOffset()), width, height, null);
        drawCollisionModel(graphics);
    }

    @Override
    public void tick() {

        tickAnimations();
        move();
    }

    @Override
    public void die() {

    }

    public void setMovementSpeed(){

        xMove += speed * directionVector.xDirection;
        yMove += speed * directionVector.yDirection;
    }

    public void tickAnimations(){
        projAnimation.tick();
    }

}
