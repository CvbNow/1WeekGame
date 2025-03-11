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
            tiles[3].image = ImageIO.read(new File("1WeekGame/src/Resources/Pictures/floor0.png"));
            tiles[3].collision = true;
            tiles[0].image = ImageIO.read(new File("1WeekGame/src/Resources/Pictures/floor1.png"));
            tiles[1].image = ImageIO.read(new File("1WeekGame/src/Resources/Pictures/floor2.png"));
            tiles[1].collision = true;
        } catch (IOException e) {
            System.out.println("Error loading floor images");
            e.printStackTrace();
        }

        loadMap("1WeekGame/src/Resources/Maps/map01.txt");
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
    }
    public boolean checkCollision(int x, int y){
        if (0 <= x && x < map[0].length && 0 <= y && y < map.length){
            return tiles[map[y][x]].collision;
        }else{
            return true;
        }
    }

}
