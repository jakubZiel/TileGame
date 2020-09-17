package GameInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right;
    public boolean attackUp , attackDown, attackLeft, attackRight;
    public boolean lootKey;

    public void tick(){
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];


        attackUp = keys[KeyEvent.VK_UP];
        attackDown = keys[KeyEvent.VK_DOWN];
        attackLeft = keys[KeyEvent.VK_LEFT];
        attackRight = keys[KeyEvent.VK_RIGHT];

        lootKey = keys[KeyEvent.VK_L];
    }

    public KeyManager(){
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public boolean[] getKeys() {
        return keys;
    }
}
