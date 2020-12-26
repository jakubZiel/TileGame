package controller;

import model.GamePackage.Handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right;
    public boolean attackUp , attackDown, attackLeft, attackRight;
    public boolean lootKey;
    public Handler handler;


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

    public KeyManager(Handler handler){

        this.handler = handler;
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_E) {

            if (keys[KeyEvent.VK_P] && e.getKeyCode() == KeyEvent.VK_P)
                synchronized (handler.getWorld()){
                    handler.getFrame().add(handler.getGame().getDisplay().getCanvas());
                    handler.getWorld().notify();
            }

            keys[e.getKeyCode()] = !keys[e.getKeyCode()];
        }
        else
            keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (!(e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_E))
            keys[e.getKeyCode()] = false;


    }

    public boolean[] getKeys() {
        return keys;
    }
}
