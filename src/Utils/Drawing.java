package Utils;

import model.GamePackage.Handler;

import java.awt.*;

public class Drawing {

    public static void drawThickRectangle(int thickness, Graphics graphics, Rectangle rectangle, Handler handler){

        for (int i = 0; i < thickness; i++ ){

            graphics.drawRect(
                    (int) (rectangle.x + i - handler.getGame().getGameCam().getXOffset()),
                    (int) (rectangle.y + i - handler.getGame().getGameCam().getYOffset()),
                    rectangle.width - 2 * i , rectangle.height - 2 * i);
        }
    }

}


