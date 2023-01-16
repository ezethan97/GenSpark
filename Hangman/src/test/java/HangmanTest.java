import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

import org.example.Hangman;
import org.junit.jupiter.api.*;

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
        File f = new File("C:\\Users\\ET\\IdeaProjects\\Hangman\\src\\main\\java\\org\\example\\words.txt");
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
                    ===
                """, Hangman.getDrawing(0));
    }

    @Test
    void testPlayAgain()
    {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("yes".getBytes());
        System.setIn(in);
        assertTrue(Hangman.playAgain());
        in = new ByteArrayInputStream("no".getBytes());
        System.setIn(in);
        assertFalse(Hangman.playAgain());
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
        ArrayList<String> drawings = new ArrayList<>();
        drawings.add("""
                 +---+
                     |
                     |
                     |
                     |
                     |
                    ===
                """);
        drawings.add("""
                 +---+
                 O   |
                     |
                     |
                     |
                     |
                    ===
                """);
        drawings.add("""
                 +---+
                 O   |
                 |   |
                     |
                     |
                     |
                    ===
                """);
        drawings.add("""
                 +---+
                 O   |
                \\|   |
                     |
                     |
                     |
                    ===
                """);
        drawings.add("""
                 +---+
                 O   |
                \\|/  |
                     |
                     |
                     |
                    ===
                """);
        drawings.add("""
                 +---+
                 O   |
                \\|/  |
                 |   |
                     |
                     |
                    ===
                """);
        drawings.add("""
                 +---+
                 O   |
                \\|/  |
                 |   |
                /    |
                     |
                    ===
                """);
        drawings.add("""
                 +---+
                 O   |
                \\|/  |
                 |   |
                / \\  |
                     |
                    ===
                """);
        assertEquals(drawings,Hangman.createDrawings());
    }

}
