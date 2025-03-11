package com.mycompany.Entity;

import java.awt.image.BufferedImage;

import com.mycompany.weekgame.GamePanel;

public class Entity {

    public int x, y;
    public int type;

    public boolean player;

    public int targetXPos, targetYPos;
    public int direction;

    public BufferedImage[][] image;

    GamePanel gp;
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void move(int i){
      
        // 0 = up, 1 = right, 2 = down, 3 = left
        switch(i){
            case 0: //up
                if (!gp.tileM.checkCollision(this.getTileX(),this.getTileY() - 1)){
                    this.y = this.y - gp.tileSize;
                }
            break;
            case 1: //right
                if (!gp.tileM.checkCollision(this.getTileX() + 1,this.getTileY())){
                    this.x = this.x + gp.tileSize;
                }
            break;
            case 2: //down
                if (!gp.tileM.checkCollision(this.getTileX(),this.getTileY() + 1)){
                    this.y = this.y + gp.tileSize;
                }
            break;
            case 3: //left
                if (!gp.tileM.checkCollision(this.getTileX() - 1,this.getTileY())){
                    this.x = this.x - gp.tileSize;
                }
            break;
        
        }

    }
    public  int getTileX(){
        return this.x / gp.tileSize;
    }
    public int getTileY(){
        return this.y / gp.tileSize;
    }
}
