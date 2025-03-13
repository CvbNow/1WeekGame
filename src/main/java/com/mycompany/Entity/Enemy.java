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
            while (true){
                enemies[i].x = (int) (gp.tileSize * (int)((Math.random() * 100)));
                enemies[i].y = (int) (gp.tileSize * (int)((Math.random() * 100)));
                if (!gp.tileM.checkCollision(enemies[i].getTileX(), enemies[i].getTileY())){
                    break;
                }
            }
            enemies[i].targetXPos = gp.player.player.getTileX();
            enemies[i].targetYPos = gp.player.player.getTileY();
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

        if(gp.player.roundPlayed){
            for (int i = 0; i < 10; i++){
                enemies[i].targetXPos = gp.player.player.getTileX();
                enemies[i].targetYPos = gp.player.player.getTileY();
                gp.pathfinding.aStar(enemies[i]);    
            }
            //Move or attack
            //Move when it can't attack. 

        }
    }
    public void move(int index){
        //Move to next node
        if (enemies[index].pathLength > 0){
            if (enemies[index].path[enemies[index].pathLength - 1][0] < enemies[index].getTileX()){ //Left
                enemies[index].direction = 3;
                enemies[index].move(3);
            }else if (enemies[index].path[enemies[index].pathLength - 1][0] > enemies[index].getTileX()){//Right
                enemies[index].direction = 1;
                enemies[index].move(1);
            }else if (enemies[index].path[enemies[index].pathLength - 1][1] < enemies[index].getTileY()){
                enemies[index].direction = 0;
                enemies[index].move(0);
            }else if (enemies[index].path[enemies[index].pathLength - 1][1] > enemies[index].getTileY()){
                enemies[index].direction = 2;
                enemies[index].move(2);
            }
        }
    }
    public void draw(Graphics2D g2){
        for (int i = 0; i < 10; i++){
            g2.drawImage(enemies[i].image[enemies[i].direction][1], enemies[i].x - gp.player.player.x + gp.player.centerX, enemies[i].y - gp.player.player.y + gp.player.centerY, gp.tileSize, gp.tileSize, null);
        }
    }
}
