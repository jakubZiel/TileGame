package gfx.TextureProcessing;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(String filePath){
        sheet = ImageLoader.loadImage(filePath);
    }

    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }
}
