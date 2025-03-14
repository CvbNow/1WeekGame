package com.mycompany.Attacks;

import java.util.HashMap;

public class AttackManager {
    
    public HashMap<String, Attack> attacks = new HashMap<>();
    public HashMap<Integer, String> type = new HashMap<>();
    public AttackManager(){
        setup();
    }
    public void setup(){
        addAttack(1, 1, "Poke");
        addAttack(3, 1, "Punch");
        addAttack(1, 4, "Shruiken");
        
        setupTypes();
    }
    public void addAttack(int damage, int range, String name){
        Attack temp = new Attack();

        temp = new Attack();
        temp.damage = damage;
        temp.range = range;
        temp.name = name;

        attacks.put(temp.name, temp);
    }
    public void setupTypes(){
        // 0 -> Player
        //Start off with just a poke
        type.put(0, "Poke");
        //1 -> melee enemy
        type.put(1,"Poke");
        //2 -> Ranged enemy
        type.put(2, "Shruiken");
        
        
    }

}
