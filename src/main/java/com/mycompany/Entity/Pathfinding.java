package com.mycompany.Entity;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.HashSet;
import com.mycompany.weekgame.GamePanel;

public class Pathfinding {
 
    GamePanel gp;
    public Pathfinding(GamePanel gp){
        this.gp = gp;
    }

    public void aStar(Entity entity) {

        PriorityQueue<Node> openList = new PriorityQueue<>();
        HashMap<Node, Node> closedList = new HashMap<>(); // Stores visited nodes

        Node start = new Node(entity.getTileX(), entity.getTileY());
        Node goal = new Node(entity.targetXPos, entity.targetYPos);
        
        start.g = 0;
        start.h = Math.abs(goal.x - start.x) + Math.abs(goal.y - start.y); // Manhattan distance heuristic
        start.f = start.g + start.h;

        openList.add(start);

        while (!openList.isEmpty()) {
            Node current = openList.poll(); // Get node with lowest f-cost
            
            if (current.equals(goal)) { // If we reached the target
                reconstructPath(current, entity);
                return;
            }

            closedList.put(current, current);

            for (int i = 0; i < 4; i++) { // Only cardinal directions (no diagonals)
                int newX = current.x;
                int newY = current.y;

                switch (i) {
                    case 0: newX += 1; break; // Right
                    case 1: newX -= 1; break; // Left
                    case 2: newY += 1; break; // Down
                    case 3: newY -= 1; break; // Up
                }
                
                // Check if within bounds and not a wall
                if (gp.tileM.checkCollision(newX, newY)) continue;
                
                Node neighbor = new Node(newX, newY);

                if (closedList.containsKey(neighbor)) continue; // Skip if already visited

                int tentativeG = current.g + 1; // Assume uniform movement cost

                if (tentativeG < neighbor.g) {
                    neighbor.g = tentativeG;
                    neighbor.h = Math.abs(goal.x - newX) + Math.abs(goal.y - newY);
                    neighbor.f = neighbor.g + neighbor.h;
                    neighbor.parent = current;

                    openList.add(neighbor); // Add or update node in open list
                }
            }
        }
    }

    private void reconstructPath(Node current, Entity entity) {
        //System.out.println("Reconstructing path...");
        //Wipe current path
        entity.pathLength = 0;
        entity.path = new int[1000][2];
        while (current.parent != null) {
            entity.path[entity.pathLength][0] = current.x;
            entity.path[entity.pathLength][1] = current.y;
            entity.pathLength++;
            current = current.parent;
        }
    }
}