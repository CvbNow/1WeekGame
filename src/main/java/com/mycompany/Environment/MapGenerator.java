package com.mycompany.Environment;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;

import com.mycompany.weekgame.GamePanel;

public class MapGenerator {

    GamePanel gp;
    public MapGenerator(GamePanel gp){
        this.gp = gp;
    }

    int map[][] = new int[100][100];
    public int rooms[][] = new int[15][4]; //x, y, width, height

    public void generateMap(){
        for (int i = 0; i< 100; i++){
            for (int j = 0; j < 100; j++){
                map[i][j] = 0;
            }
        }
        /*Rules:
        Rooms are surrounded by walls. 
        No walls may overlap.
        0 is black/editable, 1 is floor, 2 is wall
        */

        //Random # of rooms
        rooms = new int[15][4]; //x, y, width, height

        int maxNumRooms = (int) Math.random() * 6 + 10; //Makes a number of rooms between  10 and 16
        int numRooms = 0;
        int attempts = 0;
        boolean possible = true;
        while (numRooms < maxNumRooms && attempts < 100){
            possible = true;
            int width = (int) ((Math.random()) *10 + 15); //Makes a width  between 15 and 25
            int height = (int) ((Math.random()) *10 + 15); //Makes a height between 15 and 25

            int x = (int) (Math.random() * (100 - width)); //Makes a random x position within the map
            int y = (int) (Math.random() * (100 - height)); //Makes a random y position within the map

            

            //Collision
            
            for (int i = x; i < x + width; i++){
                for (int j = y; j < y + height; j++){
                    if (map[i][j] != 0){
                        //try again
                        possible = false;
                        System.out.println("Not possible");
                        attempts++;
                        break;
                    }
                }
                if(!possible){
                    break;
                }
             }
             

            if (possible){
                System.out.println("Possible");
                for (int i = x; i < x + width; i++){
                    for (int j = y; j < y + height; j++){
                        if ( i == x || i == x + width - 1 || j == y || j == y + height - 1 && map[i][j] == 0 ){
                            map[i][j] = 2; //Wall
                        }else{
                            map[i][j] = 1; //Floor
                        }
                    }
                }
                //Save the room center
                rooms[numRooms][0] = x + width / 2;
                rooms[numRooms][1] = y + height / 2;
                rooms[numRooms][2] = width;
                rooms[numRooms][3] = height;
                numRooms++;
            }
           
        }

        //Connect the rooms
        for (int i = 0; i < numRooms - 1; i++) {
            int x1 = rooms[i][0];
            int y1 = rooms[i][1];
            int x2 = rooms[i + 1][0];
            int y2 = rooms[i + 1][1];

            while (x1 != x2 || y1 != y2) {
            if (x1 != x2) {
                if (x1 < x2) x1++;
                else x1--;
            } else if (y1 != y2) {
                if (y1 < y2) y1++;
                else y1--;
            }

            if (map[x1][y1] == 0) {
                map[x1][y1] = 1; // Floor
            }

            // Surround the hallway with walls
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                if (x1 + dx >= 0 && x1 + dx < 100 && y1 + dy >= 0 && y1 + dy < 100) {
                    if (map[x1 + dx][y1 + dy] == 0) {
                    map[x1 + dx][y1 + dy] = 2; // Wall
                    }
                }
                }
            }
            }
        }
        //Do it again, clearing the middle so you can actually walk
        for (int i = 0; i < numRooms - 1; i++) {
            int x1 = rooms[i][0];
            int y1 = rooms[i][1];
            int x2 = rooms[i + 1][0];
            int y2 = rooms[i + 1][1];

            while (x1 != x2 || y1 != y2) {
                if (x1 != x2) {
                    if (x1 < x2) x1++;
                    else x1--;
                } else if (y1 != y2) {
                    if (y1 < y2) y1++;
                    else y1--;
                }

                if (map[x1][y1] == 2) {
                    map[x1][y1] = 1; // Floor
                }

            }
        }

        System.out.println("Rooms Generated");
        //Save to a file

        saveMap();
        
    }

    public void saveMap(){
        try (PrintWriter writer = new PrintWriter(new File("1WeekGame/src/Resources/Maps/map.txt"))) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    writer.print(map[i][j] + " ");
                }
                writer.println();
            }
        } catch (Exception e) {
            System.out.println("Error saving map");
            e.printStackTrace();
        }
    }
}
