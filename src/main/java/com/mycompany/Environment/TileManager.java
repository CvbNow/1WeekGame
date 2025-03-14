package com.mycompany.Environment;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mycompany.weekgame.GamePanel;

public class TileManager {
 
    public Tile[] tiles;
    public int map[][] = new int[100][100];
    GamePanel gp;
    public TileManager(GamePanel gp){
        this.gp = gp;
        
        tiles = new Tile[3];
        for (int i = 0; i < tiles.length; i++){
            tiles[i] = new Tile();
        }

        try {
            tiles[0].image = ImageIO.read(new File("1WeekGame/src/Resources/Pictures/floor0.png"));
            //tiles[0].collision = true;
            tiles[1].image = ImageIO.read(new File("1WeekGame/src/Resources/Pictures/floor1.png"));
            tiles[2].image = ImageIO.read(new File("1WeekGame/src/Resources/Pictures/floor2.png"));
            //tiles[2].collision = true;
        } catch (IOException e) {
            System.out.println("Error loading floor images");
            e.printStackTrace();
        }

    }
    public void loadMap(String s){
        try (BufferedReader br = new BufferedReader(new FileReader(s))) { 
            for (int row = 0; row < 100; row++) {
            String[] line = br.readLine().split(" ");
            for (int col = 0; col < 100; col++) {
                map[row][col] = Integer.parseInt(line[col]);
            }
            }
        } catch (Exception e) {
            System.out.println("Error loading map");
            e.printStackTrace();
        }
        
    }
    public void drawMap(Graphics2D g2){
        int row = gp.player.player.getTileY() - 15;
        int rowMax = row + 30;
        int col = gp.player.player.getTileX() - 21;
        int colMax = col + 42;

        if (row < 0) row = 0;
        if (col < 0) col = 0;
        if (rowMax > 100) rowMax = 100;
        if (colMax > 100) colMax = 100;

        for (row = 0; row < rowMax; row++) {
            for (col = 0; col < colMax; col++) {
                g2.drawImage(tiles[map[row][col]].image, col * gp.tileSize - gp.player.player.x + gp.player.centerX, row * gp.tileSize - gp.player.player.y + gp.player.centerY, gp.tileSize, gp.tileSize, null);
            }
        }
        //for (int i = 0; )
        //Draw minimap
        int x = gp.width - 100*32/16;
        for (int i = 0; i < 100; i++){
            for (int j = 0; j< 100; j++){
                g2.drawImage(tiles[map[i][j]].image, x + i * 2,  j* 2, 2, 2, null);
            }
        }
    }
    public boolean checkCollision(int x, int y){
        if (0 <= x && x < map[0].length && 0 <= y && y < map.length){
            return tiles[map[y][x]].collision;
        }else{
            return true;
        }
    }
    public void setup(){
        loadMap("1WeekGame/src/Resources/Maps/map.txt");
    }

}
