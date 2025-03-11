package com.mycompany.Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.mycompany.weekgame.GamePanel;

public class MouseClickListener implements MouseListener {

    GamePanel gp;
    public MouseClickListener(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
