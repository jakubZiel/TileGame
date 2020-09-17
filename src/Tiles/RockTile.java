package Tiles;

import gfx.TextureProcessing.Assets;

public class RockTile extends Tile{

    public RockTile(int id) {
        super(Assets.stone, id);
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
