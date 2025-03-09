package src.main.java.com.mycompany.weekgame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;

import src.main.java.com.mycompany.Environment.TileManager;

public class GamePanel extends JPanel implements Runnable{

    Thread gameThread; 
    public int FPS = 60;
    public int tileSize = 32;
    public int width = tileSize * 39;
    public int height = tileSize * 22;

    //Files
    public TileManager tileM = new TileManager(this);
    public GamePanel(){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.white);
       this.setDoubleBuffered(true);

        this.setFocusable(true);
        this.requestFocusInWindow();

        gameThread = new Thread(this);
        gameThread.start();
    }

/**********************************************************************************/
    //Delta time variables
        int fps = 60;
        double lastTime = System.nanoTime();
        double currentTime;
    //Delta time loop
        @Override
        public void run() {
            while (gameThread.isAlive()){
                currentTime = System.nanoTime();
                if ((currentTime - lastTime) / 1000000000.0 > 1.0 / fps){
                    update();
                    repaint();
                    lastTime = currentTime;
                }
            }
        }
/*********************************************************************************/
    public void update(){
    //Update stuff
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.drawMap(g2);
        //Draw stuff
    }
    
}
