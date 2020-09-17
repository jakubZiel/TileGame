package gfx.TextureProcessing;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Screenshot {
    public static BufferedImage takeScreenShotOfFrame(JFrame frame){

        Rectangle frameBounds = frame.getBounds();
        try {
            Robot r = new Robot();

            frameBounds.y += 40;
            frameBounds.height -= 40;
            BufferedImage Image = r.createScreenCapture(frameBounds);

            Image = imageBlur(Image);
            return Image;

            } catch ( AWTException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage imageBlur(BufferedImage image){

        float[] matrix = new float[400];
        for (int i = 0; i < 400; i++)
            matrix[i] = 1.0f/450.0f;

        BufferedImageOp op = new ConvolveOp( new Kernel(20, 20, matrix) );
        BufferedImage result = op.filter(image, null);

        return result;
    }
}
