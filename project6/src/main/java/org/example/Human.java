package org.example;

import java.lang.reflect.Array;
import java.util.Random;

public class Human {
    private int health;
    private int strength;
    private int resistance;
    private Equipment weapon;
    private Equipment armor;
    private Equipment helmet;

    public Human()
    {
        health = 20;
        resistance = 0;
        strength = 1;

    }

    public int getHealth() {
        return health;
    }

    public void equip(Equipment e)
    {
        if(e.getType() == "weapon") {
            weapon = e;
        }

        if(e.getType() == "armor"){
            armor = e;
        }

        if(e.getType() == "helmet"){
            helmet = e;
        }

        int wep = weapon == null?0:weapon.getValue();
        int hel = helmet == null?0:helmet.getValue();
        int arm = armor == null?0:armor.getValue();

        strength = 1 + wep;
        resistance = hel + arm;

        System.out.println(String.format("You equipped %s (+%d)", e.getName(), e.getValue()));
    }

    public int takeDamage(int damage)
    {
        int trueDamage = damage - resistance;
        if(trueDamage >= 0) {
            health -= trueDamage;
        }
        else
            trueDamage = 0;
        System.out.println("Goblin " + " has dealt " + trueDamage + " damage to you");
        return trueDamage;
    }

    public void attack(Goblin g)
    {
        Random r = new Random();
        int damage = r.nextInt(strength + 1);
        g.takeDamage(damage);
        System.out.println("You have dealt " + damage + " damage to Goblin");
        if(g.getHealth() <= 0)
            System.out.println("The goblin has died");
        else
            System.out.println("The goblin has " + g.getHealth() + " health left");
    }

    public String toString()
    {
        String weapon, armor, helmet;
        if(this.weapon == null)
            weapon = "weapon: none";
        else
            weapon = this.weapon.toString();
        if(this.armor == null)
            armor = "armor: none";
        else
            armor = this.armor.toString();
        if(this.helmet == null)
            helmet = "helmet: none";
        else
            helmet = this.helmet.toString();
        return String.format("health: %d, strength: %d, resistance: %d, %s, %s, %s",health,  strength, resistance, weapon, armor, helmet);
    }



}
