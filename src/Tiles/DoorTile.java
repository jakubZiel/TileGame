package Tiles;

import java.awt.image.BufferedImage;

public class DoorTile extends Tile{
    public DoorTile(BufferedImage texture, int id) {
        super(texture, id);
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
