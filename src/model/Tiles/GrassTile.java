package model.Tiles;

import view.gfx.TextureProcessing.Assets;

public class GrassTile extends Tile {

    public GrassTile(int id) {
        super(Assets.grass, id);
    }

    public boolean isWalkable(){return true;}
}
