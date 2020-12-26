package view.gfx.HUD;

import model.Entities.Creature.Player;
import model.Entities.Items.Item;
import model.GamePackage.Handler;
import view.gfx.TextureProcessing.Assets;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Inventory {
    private Player player;
    private Handler handler;
    private ArrayList<Item> items;
    private int money;

    boolean active = false;
    private BufferedImage currentInventory;

    private int x, y;
    private int width, height;

    public Inventory(Player player, Handler handler){
        this.player = player;
        this.handler = handler;
        this.money = 0;
        this.items = new ArrayList<>();
        this.currentInventory = Assets.inventoryImage;

        x = (int)(handler.getFrame().getWidth() * 0.1);
        y = (int)(handler.getFrame().getHeight() * 0.1);
        width =  (int)(handler.getFrame().getWidth() * 0.8);
        height = (int)(handler.getFrame().getHeight() * 0.8);
    }

    public void tick(){
        if (handler.getKeyManager().getKeys()[KeyEvent.VK_E])
            active = true;
        else
            active = false;

        if (!active)
            return;

    }

    public void render(Graphics graphics){
        if (!active)
            return;

        graphics.drawImage(currentInventory, x, y, width, height ,null);
        renderItems(graphics);
    }

    private void renderItems(Graphics graphics){

        int rowLength = 9;

        int TopX = (int)(width * 0.04) + x;
        int TopY = (int)(height * 0.52) + y;
        int betweenSlotsX = (int)(width * 0.1);
        int betweenSlotsY = (int)(height * 0.1) + 3;

        int tiltX = 5, tiltY = 3;

        int currX, currY;

        for (int i = 0; i < items.size() ; i++) {
            currX = i % rowLength;
            currY = i / rowLength;

            graphics.drawImage(Assets.goldImages[0],tiltX + currX * betweenSlotsX + TopX,tiltY + currY * betweenSlotsY + TopY, (int)(betweenSlotsX * 0.8) , (int)(betweenSlotsY * 0.8) , null);
        }
    }

    public void addPickedUpItem(Item item){

        if (items.size() == 27)
            return;
        for (Item i : items){
            if (i.getId() == item.getId()) {
                i.increaseCount(item.getCount());
                return;
            }
        }
        items.add(item);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
