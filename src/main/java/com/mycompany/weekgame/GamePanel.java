package com.mycompany.weekgame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Map;
import java.awt.Color;
import javax.swing.JPanel;

import com.mycompany.Entity.Enemy;
import com.mycompany.Entity.Entity;
import com.mycompany.Entity.Pathfinding;
import com.mycompany.Entity.Player;
import com.mycompany.Environment.MapGenerator;
import com.mycompany.Environment.TileManager;
import com.mycompany.Image.ImageHandler;
import com.mycompany.Listeners.KeyboardListener;
import com.mycompany.Listeners.MouseClickListener;

public class GamePanel extends JPanel implements Runnable{

    Thread gameThread; 
    public int FPS = 60;
    public int tileSize = 64;
    public int width = tileSize * 39 /2;
    public int height = tileSize * 22 /2;

    //Files
    public TileManager tileM = new TileManager(this);
    public ImageHandler imageH = new ImageHandler(this);
    public Entity entity = new Entity(this);
    public Player player = new Player(this);
    public Enemy enemy = new Enemy(this);

    public MapGenerator mapGenerator = new MapGenerator(this);
    public Pathfinding pathfinding = new Pathfinding(this);

    public MouseClickListener mcl = new MouseClickListener(this);
    public KeyboardListener kl = new KeyboardListener(this);


    public GamePanel(){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.setDoubleBuffered(true);

        gameThread = new Thread(this);
        gameThread.start();

        this.addMouseListener(mcl);
        this.addKeyListener(kl);
    }

/**********************************************************************************/
    //Delta time variables
        int fps = 30;
        double lastTime = System.nanoTime();
        double currentTime;
        public long frameCount = 0;
    //Delta time loop
        @Override
        public void run() {
            setup();
            while (gameThread.isAlive()){
                currentTime = System.nanoTime();
                if ((currentTime - lastTime) / 1000000000.0 > 1.0 / fps){
                    frameCount++;
                    repaint();
                    updateGame();
                    if (frameCount% 3 == 0) updateRound();
                    repaint();
                    lastTime = currentTime;
                }
            }
        }
/*********************************************************************************/
    public void setup(){        
        mapGenerator.generateMap();
        tileM.setup();
        enemy.setupEnemies();
        player.setup();
    }
    public void updateGame(){
        player.update();
    //Update stuff
    }
    public void updateRound(){  
        enemy.updateRound();
        player.updateRound();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.drawMap(g2);
        player.draw(g2);
        enemy.draw(g2);

        //Draw stuff
    }
    
}
