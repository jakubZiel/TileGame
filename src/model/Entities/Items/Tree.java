package model.Entities.Items;

import model.GamePackage.Handler;
import view.gfx.TextureProcessing.Assets;

import java.awt.*;

public class Tree extends Item {

    private static int DEFAULT_TREE_WIDTH = DEFAULT_WIDTH * 4;
    private static int DEFAULT_TREE_HEIGHT = DEFAULT_HEIGHT * 4;



    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, (DEFAULT_WIDTH * 4), (DEFAULT_HEIGHT * 4));
        bounds.x = width / 3;
        bounds.width /= 3;
        bounds.y =   height / 2 - 4;
        bounds.height = height / 4;
        health = 500;
        setId(2);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.tree, (int) (x - handler.getGame().getGameCam().getXOffset()), (int) (y - handler.getGame().getGameCam().getYOffset()), width, height, null );
        drawCollisionModel(graphics);
    }

    @Override
    public void tick() {

    }

    @Override
    public void die() {

    }
}
