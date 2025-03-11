package com.mycompany.Entity;

import java.awt.Graphics2D;

import com.mycompany.weekgame.GamePanel;

public class Player {
    
    GamePanel gp;

    public Entity player; 
    public int centerX, centerY;

    public Player(GamePanel gp) {
        this.gp = gp;
        player = new Entity(gp);
        player.x = 32 * 10;
        player.y = 32 * 10;
        player.type = 0;
        player.player = true;
        player.targetXPos = 0;
        player.targetYPos = 0;
        player.direction = 0;
        player.image = gp.imageH.loadSpriteSheet("1WeekGame\\src\\Resources\\Pictures\\ninja.png");
        centerX = gp.width / 2 - gp.tileSize /2;
        centerY = gp.height / 2;
        
    }

    //Have a list of button presses, that happen at the 3 frame mark. 
    Runnable set = () -> System.out.println("");
    public boolean roundPlayed = false;

    public void updateRound(){
        if (roundPlayed){
            set.run();
            roundPlayed = false;
        }
    }

    public void update() {
        //Nothing rn. Add movement later
        if (gp.kl.upPressed){
            roundPlayed = true;
            set = () ->{
                player.direction = 0;
                player.move(0);
            };
        }else if (gp.kl.rightPressed){
            roundPlayed = true;
            set = () ->{
                player.direction = 1;
                player.move(1);
            };
        }else if (gp.kl.downPressed){
            roundPlayed = true;
            set = () ->{
                player.direction = 2;
                player.move(2);
            };
        }else if (gp.kl.leftPressed){
            roundPlayed = true;
            set = () ->{
                player.direction = 3;
                player.move(3);
            };
        }
    }
    //Center of the screen
    
    public void draw(Graphics2D g2){
        //Player gets drawn at the center of the screen
        g2.drawImage(player.image[player.direction][0], centerX, centerY,gp.tileSize, gp.tileSize, null);
    }

    
}
