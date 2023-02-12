package org.example;

import java.util.Random;

public class Goblin {
    private int health;
    private int strength;

    private Equipment drop;

    public Goblin(int health, int strength)
    {
        this.health = health;
        this.strength = strength;
    }


    public int getHealth() {
        return health;
    }

    public void attack(Human h)
    {
        Random r = new Random();
        int damage = r.nextInt(strength + 1);
        h.takeDamage(damage);
        if(h.getHealth() <= 0)
            System.out.println("You have died");
        else
            System.out.println("You have " + h.getHealth() + " health left");
    }

    public void takeDamage(int damage)
    {
        health -= damage;
    }

    public String toString(){
        return String.format("health: %d strength: %d", health, strength);
    }
}
