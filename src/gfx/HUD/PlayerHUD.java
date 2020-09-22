package gfx.HUD;

import Entities.Creature.Player;
import GamePackage.Handler;

import java.awt.*;

public class PlayerHUD {

    private Handler handler;
    private Player player;
    private int maxHealth;
    private float percentage;
    private int barWidth;
    private int barHeight;
    private int maxMana;
    private float manaPercentage;


    public PlayerHUD(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        this.maxHealth = player.getHealth();
        this.percentage = 1;
        this.barWidth = 300;
        this.barHeight = 15;
        this.manaPercentage = 1;
        this.maxMana = player.getMana();
    }

    public void tick(){
        percentage = (float) player.getHealth() / maxHealth;
        manaPercentage = (float) player.getMana() / maxMana;
    }

    public void render(Graphics graphics){
        graphics.setColor(Color.RED);
        graphics.fillRect(0 , 5,  (int) (barWidth * percentage), barHeight);
        graphics.setColor(Color.CYAN);
        graphics.fillRect(0 , 25,  (int) (barWidth * manaPercentage), barHeight);
    }

}
