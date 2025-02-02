package model.Entities.Creature;

import model.Entities.GameObject;
import model.Entities.Items.Gold;
import model.GamePackage.Handler;
import Utils.DirectionVector;
import view.gfx.HUD.HealthBar;
import view.gfx.TextureProcessing.Animation;
import view.gfx.TextureProcessing.Assets;
import Utils.Drawing;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends Creature {

    private Rectangle territory;
    private Rectangle attackZone;
    private int attackSize;

    public Enemy(Handler handler, float x, float y, int health){
        super(handler, x, y, health, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);

        territory = new Rectangle((int)(x - 2 * width), (int)(y - 2 * height), width * 5, height * 5 );

        this.attackSize = 150;

        setSpeed(1);
        setAttackZone(attackSize);
        setAnimations();

        setBounds(12, 32, 32, 21);
        healthBar = new HealthBar(this, this.handler);
    }

    @Override
    public void render(Graphics graphics) {
        renderTerritory(graphics);
        drawAttackZone(graphics);
        graphics.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGame().getGameCam().getXOffset()), (int) (y - handler.getGame().getGameCam().getYOffset()), width, height, null);
        drawCollisionModel(graphics);
        healthBar.render(graphics);
    }

    private void renderTerritory(Graphics graphics){
        graphics.setColor(Color.magenta);
        Drawing.drawThickRectangle(3, graphics, territory, handler);
    }

    @Override
    public void tick() {

        checkTerritory();
        tickAnimations();
        move();
        attack();
        tickAttackZone();
        healthBar.tick();
    }

    @Override
    public void die(){

    }

    private void setAttackZone(int killZoneSide){
        attackZone = new Rectangle(getCenterX() - killZoneSide / 2 , getCenterY() - killZoneSide / 2, killZoneSide, killZoneSide);
    }

    public void drawAttackZone(Graphics graphics){

        graphics.setColor(Color.RED);
        Drawing.drawThickRectangle(3, graphics, attackZone, handler);
    }

    public void checkTerritory(){

        if (!handler.getEntityManager().getPlayer().getCollisionBounds(0,0).intersects(territory)){
            xMove = 0;
            yMove = 0;
        }else {
            DirectionVector vector = new DirectionVector(this, handler.getEntityManager().getPlayer());
            xMove = speed * vector.xDirection;
            yMove = speed * vector.yDirection;
        }

    }

    public void attack(){

    }

    public void tickAnimations(){
        downAnim.tick();
        upAnim.tick();
        leftAnim.tick();
        rightAnim.tick();
    }

    public void setAnimations(){
        downAnim = new Animation(Assets.monsterDown, 150);
        upAnim = new Animation(Assets.monsterUp, 150);
        leftAnim = new Animation(Assets.monsterLeft, 150);
        rightAnim = new Animation(Assets.monsterRight, 150);
    }

    private void tickAttackZone(){
        setAttackZone(attackSize);
    }

    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0)
            return leftAnim.getCurrentFrame();
        else if (xMove > 0)
            return rightAnim.getCurrentFrame();
        else if ( yMove < 0)
            return upAnim.getCurrentFrame();
        else if (yMove > 0)
            return downAnim.getCurrentFrame();
        else
            return Assets.monsterDown[0];
    }

    @Override
    public boolean shouldNotExist(ArrayList<GameObject> entities){
        if (super.shouldNotExist(entities)){

            entities.add(new Gold(handler,this.getX(), this.getY(), 100));

            return true;
        }else
            return false;
    }
}
