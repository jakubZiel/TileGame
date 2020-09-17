package States;

import GamePackage.Game;
import GamePackage.Handler;

import java.awt.*;

public abstract class State {
    private static State currentState = null;

    protected Handler handler;

    public State(Handler h){
        this.handler = h;
    }

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    protected boolean isLeftPressed(){
        return handler.getMouseManager().isLeftPressed();
    }

    protected boolean isRightPressed(){
        return handler.getMouseManager().isRightPressed();
    }
}
