package Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile {

    public static final float TILE_WIDTH = 225/4,
                            TILE_HEIGHT = 225/4;

    public static Tile[] tiles = new Tile[256];


    //static resource
    public static Tile grassTile = new GrassTile(0); // 0 is grass
    public static Tile dirtTile = new DirtTile(1); // 1 is dirt
    public static Tile rockTile = new RockTile(2); // 2 is rock



    protected BufferedImage texture;
    protected final int id;


    public Tile(BufferedImage texture, int id){

        this.id = id;
        this.texture = texture;

        tiles[id] = this;
    }
    public int getId(){
        return id;
    }

    public void tick(){

    }
    public void render(Graphics graphics, int x, int y){
        graphics.drawImage(texture, x, y, (int)TILE_WIDTH, (int)TILE_HEIGHT, null);
    }

    public abstract boolean isWalkable();
}
