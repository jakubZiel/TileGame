package gfx.TextureProcessing;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] animationSet;
    private int updateSpeed, setIndex;
    private long lastTime, timer;

    public Animation(BufferedImage[] animationSet, int updateSpeed) {
        this.animationSet = animationSet;
        this.updateSpeed = updateSpeed;
        this.setIndex = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > updateSpeed) {
            setIndex++;
            timer = 0;
            if (setIndex >= animationSet.length)
                setIndex = 0;
        }

    }

    public BufferedImage getCurrentFrame(){
        return animationSet[setIndex];
    }

}
