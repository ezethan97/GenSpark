package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        Grid map = new Grid(10, 10);
        Human player = new Human();
        int health = 5, strength = 1, kills = 0;
        Goblin g1 = new Goblin(health, strength);

        int px = r.nextInt(10);
        int py = r.nextInt(10);
        map.add(player, px, py);
        int gx, gy, tx, ty;
        do {
            gx = r.nextInt(10);
            gy = r.nextInt(10);
        }
        while (map.contains(gx,gy) != null);
        map.add(g1, gx, gy);
        do {
            tx = r.nextInt(10);
            ty = r.nextInt(10);
        }
        while (map.contains(tx,ty) != null);
        map.add(createTreasure(), tx, ty);

        while (player.getHealth() > 0) {
            System.out.println(map);
            System.out.println("Choose a direction to move north/south/east/west or type status to see your and enemy's status");
            Scanner s = new Scanner(System.in);
            String direction = s.nextLine();
            if(direction.equals("status"))
                System.out.println("Player\n" + player + "\nGoblin\n" + g1);
            else {
                try {
                    map.move(px, py, direction);
                    switch (direction) {
                        case "north" -> {
                            if (map.contains(px, py - 1).getClass() != Goblin.class)
                                py--;
                        }
                        case "south" -> {
                            if (map.contains(px, py + 1).getClass() != Goblin.class)
                                py++;
                        }
                        case "east" -> {
                            if (map.contains(px + 1, py).getClass() != Goblin.class)
                                px++;
                        }
                        case "west" -> {
                            if (map.contains(px - 1, py).getClass() != Goblin.class)
                                px--;
                        }
                    }
                    if (g1.getHealth() > 0) {
                        if (gx < px)
                            direction = "east";
                        if (gx > px)
                            direction = "west";
                        if (gy < py)
                            direction = "south";
                        if (gy > py)
                            direction = "north";
                        map.move(gx, gy, direction);
                        switch (direction) {
                            case "north" -> {
                                if (map.contains(gx, gy - 1).getClass() != Human.class)
                                    gy--;
                            }
                            case "south" -> {
                                if (map.contains(gx, gy + 1).getClass() != Human.class)
                                    gy++;
                            }
                            case "east" -> {
                                if (map.contains(gx + 1, gy).getClass() != Human.class)
                                    gx++;
                            }
                            case "west" -> {
                                if (map.contains(gx - 1, gy).getClass() != Human.class)
                                    gx--;
                            }
                        }
                    }
                    if (g1.getHealth() <= 0) {
                        kills++;
                        map.add(createTreasure(), gx, gy);
                        do {
                            gx = r.nextInt(10);
                            gy = r.nextInt(10);
                        }
                        while (map.contains(gx, gy) != null);
                        g1 = new Goblin(++health, ++strength);
                        map.add(g1, gx, gy);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid movement");
                }
            }
        }
        System.out.println("You have killed " + kills + "goblins");
    }

    public static Equipment createTreasure()
    {
        Random r = new Random();
        int treasureType = r.nextInt(6);
        Equipment treasure = switch (treasureType) {
            case 0 -> new Equipment("iron sword", "weapon", 2);
            case 1 -> new Equipment("iron helmet", "helmet", 1);
            case 2 -> new Equipment("iron armor", "armor", 2);
            case 3 -> new Equipment("steel sword", "weapon", 3);
            case 4 -> new Equipment("steel helmet", "helmet", 2);
            case 5 -> new Equipment("steel armor", "armor", 3);
            default -> null;
        };
        return treasure;
    }

}

