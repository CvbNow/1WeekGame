package src.main.java.com.mycompany.Environment;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.main.java.com.mycompany.weekgame.GamePanel;

public class TileManager {
 
    public Tile[] tiles;
    int map[][] = new int[100][100];
    GamePanel gp;
    public TileManager(GamePanel gp){
        this.gp = gp;
        
        tiles = new Tile[1];
        for (int i = 0; i < tiles.length; i++){
            tiles[i] = new Tile();
        }

        try {
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/Resources/Pictures/floor1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadMap("/Resources/Maps/map1.txt");
    }
    public void loadMap(String s){
        try (BufferedReader br = new BufferedReader(new FileReader(s))) {
            for (int row = 0; row < 10; row++) {
            String[] line = br.readLine().split(" ");
            for (int col = 0; col < 10; col++) {
                map[row][col] = Integer.parseInt(line[col]);
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawMap(Graphics2D g2){
        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
                g2.drawImage(tiles[map[row][col]].image, col * gp.tileSize, row * gp.tileSize, null);
            }
        }
        //for (int i = 0; )
    }

}
