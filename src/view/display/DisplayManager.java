package view.display;

import model.States.State;
import model.GamePackage.Handler;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class DisplayManager{

    private Handler handler;
    private BufferStrategy buffStrat;
    private Graphics graphics;

    private EntityDisplayManager entityDisplayManager;

    public DisplayManager(Handler handler) {
        this.handler = handler;
    }

    public void render(){
        buffStrat = handler.getGame().getDisplay().getCanvas().getBufferStrategy();

        if (entityDisplayManager == null)
            entityDisplayManager = new EntityDisplayManager(handler);



        if(buffStrat == null){
            handler.getGame().getDisplay().getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = buffStrat.getDrawGraphics();
        graphics.clearRect(0,0, handler.getGame().width, handler.getGame().height);

        if(State.getState() != null)
            State.getState().render(graphics);
        buffStrat.show();
        graphics.dispose();
    }
}
