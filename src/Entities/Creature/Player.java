package Entities.Creature;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Attacks.Projectile.FireBall;
import Entities.GameObject;
import Entities.Items.Gold;
import GamePackage.Handler;
import Utils.DirectionVector;
import gfx.TextureProcessing.Animation;
import gfx.TextureProcessing.Assets;

public class Player extends Creature {

    private long lastAttack, attackCoolDown = 100, attackTimer = lastAttack;
    private long lastShoot, shootCooldown = 10000, shootTimer = lastShoot;
    private Rectangle attackBounds, lootingRange;
    private int lootingZoneSize;
    private BufferedImage lastRenderedFrame;

    public Player(Handler handler, float x, float y, int health){
        super(handler, x, y, health, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
        setBounds(18, 32, 18, 21);
        setSpeed(2);
        setAnimations();
        setLootingRange(60);

    }

    @Override
    public void render(Graphics graphics) {
        BufferedImage currentFrame = getCurrentAnimationFrame();
        lastRenderedFrame = currentFrame;

        graphics.drawImage(currentFrame, (int) (x - handler.getXOffset()), (int) (y - handler.getYOffset()), width, height, null);
        drawCollisionModel(graphics);
        renderLootingZone(graphics);
        renderAttackRange(graphics);
        renderAttack(graphics);
    }

    @Override
    public void tick() {
        getUserInput();
        loot();
        tickAnimations();
        tickLootingZone();
        move();
        attack();
        shoot();
        handler.getGame().getGameCam().centerOnEntity(this);
    }

    @Override
    public void die() {
        System.out.println("you lose!");
        System.exit(69);
    }

    private void shoot(){

        if (handler.getMouseManager().isLeftPressed()){

            shootTimer += System.currentTimeMillis() - lastShoot;

            if (shootTimer < shootCooldown)
                return;

            lastShoot = System.currentTimeMillis();
            shootTimer = 0;

            ArrayList<GameObject> entities = handler.getEntityManager().getEntities();
            entities.add(new FireBall(handler, getCenterX(), getCenterY(), 10, 10, new DirectionVector(handler.getMouseManager().getMouseX() + handler.getXOffset(),handler.getMouseManager().getMouseY() + handler.getYOffset(), getCenterX(), getCenterY()), this));
        }

    }

    private void loot(){

        if (!handler.getKeyManager().lootKey) return;

        ArrayList<GameObject> entities = handler.getEntityManager().getEntities();

        for (GameObject object : entities) {
            if (object instanceof Gold && lootingRange.intersects(object.getCollisionBounds(0f,0f))){
                Gold gold = (Gold) object;
                gold.setPickedUp();
            }
        }
    }

    private void getUserInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getGame().getKeyManager().up)
            yMove = -speed;
        if(handler.getGame().getKeyManager().down)
            yMove = speed;
        if(handler.getGame().getKeyManager().left)
            xMove = -speed;
        if(handler.getGame().getKeyManager().right)
            xMove = speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    private void attack(){

        attackTimer += System.currentTimeMillis() - lastAttack;
        lastAttack = System.currentTimeMillis();

        if (attackTimer < attackCoolDown)
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if(handler.getKeyManager().attackUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
            attackBounds = ar;
        }else if(handler.getKeyManager().attackDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
            attackBounds = ar;
        }else if(handler.getKeyManager().attackLeft){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            attackBounds = ar;
        }else if(handler.getKeyManager().attackRight){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            attackBounds = ar;
        }else{
            attackBounds = null;
            return;
        }

        attackTimer = 0;

        for(GameObject e : handler.getEntityManager().getEntities()){
            if(e.equals(this) || e instanceof Gold)
                continue;
            if(e.getCollisionBounds(0, 0).intersects(ar)){
                e.hurt(20);
                return;
            }
        }

    }

    private void renderAttack(Graphics graphics){

    }

    private void renderLootingZone(Graphics graphics){
        drawRect(lootingRange, graphics);
    }

    private void tickLootingZone(){
        setLootingRange(60);
    }

    private void renderAttackRange(Graphics graphics){
            drawRect(attackBounds, graphics);
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
        else if (lastRenderedFrame == null)
            return Assets.player;
        else
            return lastRenderedFrame;
    }

    private void tickAnimations(){

        downAnim.tick();
        upAnim.tick();
        leftAnim.tick();
        rightAnim.tick();
    }

    private void setAnimations(){
        downAnim = new Animation(Assets.playerDown, 150);
        upAnim = new Animation(Assets.playerUp, 150);
        leftAnim = new Animation(Assets.playerLeft, 150);
        rightAnim = new Animation(Assets.playerRight, 150);
    }

    private void drawRect(Rectangle rectangle, Graphics graphics){
        graphics.setColor(Color.BLUE);
        if (rectangle == null) return;
        graphics.drawRect((int) (rectangle.x - handler.getGame().getGameCam().getXOffset()), (int) (rectangle.y - handler.getGame().getGameCam().getYOffset()), rectangle.width, rectangle.height);
    }

    public Rectangle getLootingRange() {
        return lootingRange;
    }

    public void setLootingRange(int lootingZoneSize) {
        this.lootingZoneSize = lootingZoneSize;

        lootingRange = new Rectangle(getCenterX() - lootingZoneSize / 2, getCenterY() - lootingZoneSize / 2, lootingZoneSize, lootingZoneSize);

    }
}
