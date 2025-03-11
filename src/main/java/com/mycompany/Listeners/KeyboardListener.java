package com.mycompany.Listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.mycompany.weekgame.GamePanel;

public class KeyboardListener implements KeyListener{
    
    GamePanel gp;
    public KeyboardListener(GamePanel gp){
        this.gp = gp;
    }

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {
        int value = e.getKeyCode();
        switch (value){
            case KeyEvent.VK_W:
                gp.player.player.move(0);
            break;
            case KeyEvent.VK_A:
                gp.player.player.move(3);
                break;
            case KeyEvent.VK_S:
                gp.player.player.move(2);
                break;
            case KeyEvent.VK_D:
                gp.player.player.move(1);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int value = e.getKeyCode();
        switch (value){
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int value = e.getKeyCode();
        switch (value){
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
        }
    }
}
