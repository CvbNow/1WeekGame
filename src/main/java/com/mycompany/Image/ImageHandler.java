package com.mycompany.Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mycompany.weekgame.GamePanel;

public class ImageHandler {
    
    GamePanel gp;

    public ImageHandler(GamePanel gp){
        this.gp = gp;
    }

    public BufferedImage[][] loadSpriteSheet(String filename){
        BufferedImage[][] image;
        try{
            BufferedImage spritesheet = ImageIO.read(new File(filename));
            int width = spritesheet.getWidth() / 32;
            int height = spritesheet.getHeight() / 32;
            image = new BufferedImage[width][height];
            for (int i = 0; i < width; i++){
                for (int j = 0; j < height; j++){
                    image[i][j] = spritesheet.getSubimage(i * 32, j * 32, 32, 32);
                }
            }
            return image; 
        }catch(IOException e){
            System.out.println("Error loading spritesheet");
            return null;  
        }

    }
}
