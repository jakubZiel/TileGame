package States;


import GamePackage.Handler;

import java.awt.*;

public class MenuState extends State {
    @Override
    public void tick() {
        System.out.println(handler.getMouseManager().getMouseX() + " " + handler.getMouseManager().getMouseY());
        if (isLeftPressed() && isRightPressed()) State.setState(handler.getGame().GameState);
    }

    @Override
    public void render(Graphics graphics) {
    }

    public MenuState(Handler g){
        super(g);


    }


}
