package src.main.java.com.mycompany.Entity;

public class Player {
    
    Entity player = new Entity();

    public Player() {
        player.x = 32 * 10;
        player.y = 32 * 10;
        player.type = 0;
        player.player = true;
        player.targetXPos = 0;
        player.targetYPos = 0;
        player.direction = 0;
    }


}
