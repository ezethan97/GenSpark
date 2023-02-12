import org.example.Hangman;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class HangmanTest {

    @Test
    void testInput() throws Exception {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("a".getBytes());
        System.setIn(in);
        assertEquals("a", Hangman.input());
        in = new ByteArrayInputStream("0".getBytes());
        assertThrows(Exception.class, () -> Hangman.input());
        in = new ByteArrayInputStream("az".getBytes());
        assertThrows(Exception.class, () -> Hangman.input());
        System.setIn(sysInBackup);
    }

    @Test
    void testReadFile() throws FileNotFoundException {
        File f = new File("C:\\Users\\ET\\projects\\GenSpark\\project7\\src\\main\\java\\org\\example\\words.txt");
        ArrayList<String> words = new ArrayList<>();
        Scanner read = new Scanner(f);
        while(read.hasNextLine())
            words.add(read.nextLine());
        assertEquals(words, Hangman.readFile());
    }

    @Test
    void testCheck() throws Exception {
        ArrayList<String> guessed = new ArrayList<>();
        guessed.add("a");
        guessed.add("b");
        guessed.add("c");
        guessed.add("d");
        guessed.add("e");

        assertArrayEquals(new Object[]{"Missed letters: abcdf", "_e__", 5},
                Hangman.check(guessed, "test", "f", "_e__", "Missed letters: abcd", 4));
        assertArrayEquals(new Object[]{"Missed letters: abcd", "te_t", 4},
                Hangman.check(guessed, "test", "t", "_e__", "Missed letters: abcd", 4));
        assertThrows(Exception.class, () -> Hangman.check(guessed, "test", "e", "_e__", "Missed letters: abcd", 4));


    }

    @Test
    void testGetDrawing()
    {
        assertEquals("""
                  +---+
                      |
                      |
                      |
                      |
                      |
                     ===""".replaceAll("\n", "").replaceAll("\r", ""), Hangman.getDrawing(0).replaceAll("\n", "").replaceAll("\r", ""));
    }

    @Test
    void testPlayAgain()
    {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("yes".getBytes());
        System.setIn(in);
        try {
            assertTrue(Hangman.playAgain());
            in = new ByteArrayInputStream("no".getBytes());
            System.setIn(in);
            assertFalse(Hangman.playAgain());
        } catch (Exception e) {
        }
        in = new ByteArrayInputStream("maybe".getBytes());
        System.setIn(in);
        assertThrows(Exception.class, () -> Hangman.playAgain());
        System.setIn(sysInBackup);
    }

    @Test
    void testEnding()
    {
        assertEquals("You have been hanged! The word was \"test\"!", Hangman.ending(7,"test"));
        assertEquals("Yes! The word is \"test\"! You have won!", Hangman.ending(6,"test"));
    }

    @Test
    void testCreateDrawings()
    {
        String[] drawings = new String[8];
        drawings[0] = """
                +---+
                    |
                    |
                    |
                    |
                    |
                   ===""";
        drawings[1] = """
                 +---+
                 0   |
                     |
                     |
                     |
                     |
                    ===""";
        drawings[2] = """
                 +---+
                 0   |
                 |   |
                     |
                     |
                     |
                    ===""";
        drawings[3] = """
                 +---+
                 0   |
                \\|   |
                     |
                     |
                     |
                    ===""";
        drawings[4] = """
                 +---+
                 0   |
                \\|/  |
                     |
                     |
                     |
                    ===""";
        drawings[5] = """
                 +---+
                 0   |
                \\|/  |
                 |   |
                     |
                     |
                    ===""";
        drawings[6] = """
                 +---+
                 0   |
                \\|/  |
                 |   |
                /    |
                     |
                    ===""";
        drawings[7] = """
                 +---+
                 0   |
                \\|/  |
                 |   |
                / \\  |
                     |
                    ===""";
        assertEquals(drawings[0].replaceAll("\n", "").replaceAll("\r", ""),Hangman.createDrawings()[0].replaceAll("\n", "").replaceAll("\r", ""));
        assertEquals(drawings[1].replaceAll("\n", "").replaceAll("\r", ""),Hangman.createDrawings()[1].replaceAll("\n", "").replaceAll("\r", ""));
        assertEquals(drawings[2].replaceAll("\n", "").replaceAll("\r", ""),Hangman.createDrawings()[2].replaceAll("\n", "").replaceAll("\r", ""));
        assertEquals(drawings[3].replaceAll("\n", "").replaceAll("\r", ""),Hangman.createDrawings()[3].replaceAll("\n", "").replaceAll("\r", ""));
        assertEquals(drawings[4].replaceAll("\n", "").replaceAll("\r", ""),Hangman.createDrawings()[4].replaceAll("\n", "").replaceAll("\r", ""));
        assertEquals(drawings[5].replaceAll("\n", "").replaceAll("\r", ""),Hangman.createDrawings()[5].replaceAll("\n", "").replaceAll("\r", ""));
        assertEquals(drawings[6].replaceAll("\n", "").replaceAll("\r", ""),Hangman.createDrawings()[6].replaceAll("\n", "").replaceAll("\r", ""));
        assertEquals(drawings[7].replaceAll("\n", "").replaceAll("\r", ""),Hangman.createDrawings()[7].replaceAll("\n", "").replaceAll("\r", ""));
    }

}
