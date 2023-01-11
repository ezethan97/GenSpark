import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class DragonCaveTest {

    @Test
    void introTest()
    {
        String intro = """
                You are in a land full of dragons. In front of you,
                you see two caves. In one cave, the dragon is friendly
                and will share his treasure with you. The other dragon
                is greedy and hungry and will eat you on sight.
                Which cave will you go into? (1 or 2)
                """;
        assertEquals(intro, DragonCave.intro());
    }

    @Test
    void choice1Test() throws Exception {
        String choice = """
                You approach the cave...
                It is dark and spooky...
                A large dragon jumps out in front of you! He opens his jaws and...
                Gobbles you down in one bite!
                """;
        assertEquals(choice, DragonCave.outcome(1));
    }

    @Test
    void choice2Test() throws Exception{
        String choice = """
                You approach the cave...
                It is dark and spooky...
                A large dragon jumps out in front of you! He opens his jaws and...
                Tells you where his treasure is!
                """;
        assertEquals(choice, DragonCave.outcome(2));
    }

    @Test
    void invalidChoiceTest() throws Exception{
        assertThrows(Exception.class, () -> DragonCave.outcome(3));
    }
}
