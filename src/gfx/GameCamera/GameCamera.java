package gfx.GameCamera;

import Entities.GameObject;
import GamePackage.Handler;
import Tiles.Tile;

public class GameCamera {
    private float xOffset, yOffset;
    private Handler handler;

    public GameCamera(Handler handler, float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.handler = handler;
    }

    public void moveCamera(float xMoved, float yMoved){
        xOffset += xMoved;
        yOffset += yMoved;
        checkBlankSpace();
    }

    public void centerOnEntity (GameObject entity) {
        xOffset = entity.getX() - handler.getGame().width / 2  + entity.getWidth() / 2;
        yOffset = entity.getY() - handler.getGame().height / 2 + entity.getHeight() / 2;
        checkBlankSpace();
    }

    public void checkBlankSpace(){

        if (xOffset < 0)
            xOffset = 0;
        else if (xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getGame().width)
            xOffset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getGame().width;

        if (yOffset < 0)
            yOffset = 0;
        else if (yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getGame().height)
            yOffset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getGame().height;

    }

    public float getXOffset() {
        return xOffset;
    }

    public void setXOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getYOffset() {
        return yOffset;
    }

    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }

}
