package org.example;

public class Grid {
    private final int length;
    private final int width;

    private Object[][] field;

    public Grid(int length, int width)
    {
        this.length = length;
        this.width = width;
        field = new Object[length][width];
    }

    public Object contains(int x, int y)
    {
        return field[x][y];
    }

    public void add(Object o, int x, int y)
    {
        field[x][y] = o;
    }

    public void remove(int x, int y)
    {
        field[x][y] = null;
    }

    public void move(int x, int y, String direction)
    {
        Object o = contains(x,y);
        if(o != null)
        {
            switch (direction) {
                case "north" -> {
                    if (contains(x, y - 1) == null) {
                        add(o, x, y - 1);
                        remove(x, y);
                    } else if (contains(x, y - 1).getClass().equals(Human.class)) {
                        Goblin g = (Goblin) o;
                        g.attack((Human) contains(x, y - 1));
                    } else if (contains(x, y - 1).getClass().equals(Goblin.class)) {
                        Human h = (Human) o;
                        h.attack((Goblin) contains(x, y - 1));
                    } else if (contains(x, y - 1).getClass().equals(Equipment.class) && o.getClass().equals(Human.class)) {
                        Human h = (Human) o;
                        h.equip((Equipment) contains(x, y - 1));
                        add(o, x, y - 1);
                        remove(x,y);
                    }
                    else if (contains(x, y - 1).getClass().equals(Equipment.class) && o.getClass().equals(Goblin.class)) {
                        add(o, x, y - 1);
                        remove(x,y);
                        System.out.println("The goblin has destroyed a treasure chest");
                    }
                }
                case "south" -> {
                    if (contains(x, y + 1) == null) {
                        add(o, x, y + 1);
                        remove(x, y);
                    } else if (contains(x, y + 1).getClass().equals(Human.class)) {
                        Goblin g = (Goblin) o;
                        g.attack((Human) contains(x, y + 1));
                    } else if (contains(x, y + 1).getClass().equals(Goblin.class)) {
                        Human h = (Human) o;
                        h.attack((Goblin) contains(x, y + 1));
                    } else if (contains(x, y + 1).getClass().equals(Equipment.class) && o.getClass().equals(Human.class)) {
                        Human h = (Human) o;
                        h.equip((Equipment) contains(x, y + 1));
                        add(o, x, y + 1);
                        remove(x, y);
                    }
                    else if (contains(x, y + 1).getClass().equals(Equipment.class) && o.getClass().equals(Goblin.class)) {
                        add(o, x, y + 1);
                        remove(x,y);
                        System.out.println("The goblin has destroyed a treasure chest");
                    }
                }
                case "east" -> {
                    if (contains(x + 1, y) == null) {
                        add(o, x + 1, y);
                        remove(x, y);
                    } else if (contains(x + 1, y).getClass().equals(Human.class)) {
                        Goblin g = (Goblin) o;
                        g.attack((Human) contains(x + 1, y));
                    } else if (contains(x + 1, y).getClass().equals(Goblin.class)) {
                        Human h = (Human) o;
                        h.attack((Goblin) contains(x + 1, y));
                    } else if (contains(x + 1, y).getClass().equals(Equipment.class) && o.getClass().equals(Human.class)) {
                        Human h = (Human) o;
                        h.equip((Equipment) contains(x + 1, y));
                        add(o, x + 1, y);
                        remove(x, y);
                    }
                    else if (contains(x + 1, y).getClass().equals(Equipment.class) && o.getClass().equals(Goblin.class)) {
                        add(o, x, y - 1);
                        remove(x,y);
                        System.out.println("The goblin has destroyed a treasure chest");
                    }
                }
                case "west" -> {
                    if (contains(x - 1, y) == null) {
                        add(o, x - 1, y);
                        remove(x, y);
                    } else if (contains(x - 1, y).getClass().equals(Human.class)) {
                        Goblin g = (Goblin) o;
                        g.attack((Human) contains(x - 1, y));
                    } else if (contains(x - 1, y).getClass().equals(Goblin.class)) {
                        Human h = (Human) o;
                        h.attack((Goblin) contains(x - 1, y));
                    } else if (contains(x - 1, y).getClass().equals(Equipment.class) && o.getClass().equals(Human.class)) {
                        Human h = (Human) o;
                        h.equip((Equipment) contains(x - 1, y));
                        add(o, x - 1, y);
                        remove(x, y);
                    }
                    else if (contains(x - 1, y).getClass().equals(Equipment.class) && o.getClass().equals(Goblin.class)) {
                        add(o, x - 1, y);
                        remove(x,y);
                        System.out.println("The goblin has destroyed a treasure chest");
                    }
                }
                default -> System.out.println("Invalid movement");
            }
        }
    }

    private String key()
    {
        return """
                KEY
                x=empty space
                p=player
                g=goblin
                t=treasure
                """;
    }

    public String toString(){
        String s = "";
        for(int y = 0; y < field.length; y++)
        {
            for(int x = 0; x < field[y].length; x++)
            {
                if(field[x][y] == null)
                    s += "x";
                else if(field[x][y].getClass() == Human.class)
                    s += "p";
                else if(field[x][y].getClass() == Goblin.class)
                    s += "g";
                else if (field[x][y].getClass() == Equipment.class)
                    s += "t";
                if(x < field[y].length - 1)
                    s += " ";
            }
            s += "\n";
        }
        s+= key();
        return s;
    }
}
