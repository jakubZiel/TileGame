package model.Tiles;

import view.gfx.TextureProcessing.Assets;

public class DoorTile extends Tile{
    public DoorTile(int id) {
        super(Assets.door, id);
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
