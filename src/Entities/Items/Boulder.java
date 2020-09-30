package Entities.Items;

import GamePackage.Handler;
import gfx.TextureProcessing.Assets;

import java.awt.*;

public class Boulder extends Item {

    private static int DEFAULT_BOULDER_WIDTH = DEFAULT_WIDTH * 4;
    private static int DEFAULT_BOULDER_HEIGHT = DEFAULT_HEIGHT * 4;

    public Boulder(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_BOULDER_WIDTH, DEFAULT_BOULDER_HEIGHT);
        bounds.x = width / 4;
        bounds.width /= 2;
        bounds.y =   2 * height / 5;
        bounds.height = height / 4;
        health = 1000;
        setId(0);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.boulder, (int) (x - handler.getGame().getGameCam().getXOffset()), (int) (y - handler.getGame().getGameCam().getYOffset()), width, height, null );
        drawCollisionModel(graphics);
    }

    @Override
    public void tick() {

    }

    @Override
    public void die(){

    }
}
