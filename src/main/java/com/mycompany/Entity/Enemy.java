package com.mycompany.Entity;

import java.awt.Graphics2D;

import com.mycompany.weekgame.GamePanel;

public class Enemy {
    
    GamePanel gp;
    public Enemy (GamePanel gp){
        this.gp = gp;
    }
    public Entity[] enemies = new Entity[10];

    public void setupEnemies(){
        for (int i = 0; i< 10; i++){
            enemies[i] = new Entity(gp);
            enemies[i].x = (int) (gp.tileSize * (Math.random() * 100));
            enemies[i].y = (int) (gp.tileSize * (Math.random() * 100));
            enemies[i].type = 1;
            enemies[i].player = false;
            enemies[i].direction = (int) (Math.random() * 4);
            enemies[i].image = gp.imageH.loadSpriteSheet("1WeekGame\\src\\Resources\\Pictures\\ninja.png");
        }
        enemies[0].x = gp.tileSize * 6;
        enemies[0].y = gp.tileSize * 12;

    }
    public void updateRound(){
        //Add enemy movement here
        for (int i = 0; i < 10; i++){
            enemies[i].targetXPos = gp.player.player.getTileX();
            enemies[i].targetYPos = gp.player.player.getTileY();    
        }
        if(gp.player.roundPlayed){
            //Calculate distance to player
            for (int i = 0; i< enemies.length(); i++){
                
            }
            int distance = ()

        }
    }
    public void draw(Graphics2D g2){
        for (int i = 0; i < 10; i++){
            g2.drawImage(enemies[i].image[enemies[i].direction][1], enemies[i].x - gp.player.player.x + gp.player.centerX, enemies[i].y - gp.player.player.y + gp.player.centerY, gp.tileSize, gp.tileSize, null);
        }
    }
}
