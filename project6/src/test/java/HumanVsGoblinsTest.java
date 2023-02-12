import org.example.Equipment;
import org.example.Goblin;
import org.example.Grid;
import org.example.Human;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HumanVsGoblinsTest {
    @Test
    public void toStringTest()
    {
        Human h = new Human();
        h.equip(new Equipment("iron sword","weapon",2));
        h.equip(new Equipment("iron helmet","helmet",1));
        assertEquals("health: 20, strength: 3, resistance: 1, weapon: iron sword (+2dmg), armor: none, helmet: iron helmet (+1res)", h.toString());
        Goblin g = new Goblin(5,1);
        assertEquals("health: 5 strength: 1", g.toString());
        Grid map = new Grid(5,5);
        assertEquals("""
                x x x x x
                x x x x x
                x x x x x
                x x x x x
                x x x x x
                KEY
                x=empty space
                p=player
                g=goblin
                t=treasure
                """,map.toString());
    }

    @Test
    public void takeDamageTest()
    {
        Human h = new Human();
        h.takeDamage(1);
        assertEquals(19,h.getHealth());
        Goblin g = new Goblin(5,1);
        g.takeDamage(1);
        assertEquals(4,g.getHealth());
    }

    @Test
    void attackTest() {
        Human h = new Human();
        Goblin g = new Goblin(5,1);
        h.attack(g);
        g.attack(h);
        assertTrue(h.getHealth() >= 19);
        assertTrue(g.getHealth() >= 4);
    }

    @Test
    public void gridTest()
    {
        Grid g = new Grid(10,10);
        Object o = new Object();
        g.add(o,5,5);
        assertEquals(o,g.contains(5,5));
        g.move(5,5,"north");
        assertEquals(o,g.contains(5,4));
        g.move(5,4,"east");
        assertEquals(o,g.contains(6,4));
        g.move(6,4,"south");
        assertEquals(o,g.contains(6,5));
        g.move(6,5,"west");
        assertEquals(o,g.contains(5,5));
    }

}
