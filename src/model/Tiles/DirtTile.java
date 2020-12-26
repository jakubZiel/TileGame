package model.Tiles;

import view.gfx.TextureProcessing.Assets;

public class DirtTile extends Tile{


    public DirtTile(int id) {
        super(Assets.dirt, id);
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}

