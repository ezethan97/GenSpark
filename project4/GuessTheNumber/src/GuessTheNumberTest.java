import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class GuessTheNumberTest {

    @Test
    void checkGreeting(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Test name".getBytes());
        System.setIn(in);
        assertEquals("Test name", GuessTheNumber.greeting());
        System.setIn(sysInBackup);
    }

    @Test
    void checkInput(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("10".getBytes());
        System.setIn(in);
        assertEquals(10, GuessTheNumber.input());
        in = new ByteArrayInputStream("a".getBytes());
        System.setIn(in);
        assertThrows(NumberFormatException.class, GuessTheNumber::input);
        System.setIn(sysInBackup);
    }
    @Test
    void testCheckHigh() throws Exception {
        assertEquals("Your guess is too high",GuessTheNumber.check(20,10));
    }
    @Test
    void testCheckLow() throws Exception {
        assertEquals("Your guess is too low",GuessTheNumber.check(1,10));
    }
    @Test
    void testCheckCorrect() throws Exception {
        assertEquals("correct",GuessTheNumber.check(10,10));
    }
    @Test
    void testCheckInvalidInput()
    {
        assertThrows(Exception.class, () -> GuessTheNumber.check(0, 10));
        assertThrows(Exception.class, () -> GuessTheNumber.check(21, 10));
    }

    @Test
    void testPlayAgain()
    {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("y".getBytes());
        System.setIn(in);
        assertTrue(GuessTheNumber.playAgain());
        in = new ByteArrayInputStream("n".getBytes());
        System.setIn(in);
        assertFalse(GuessTheNumber.playAgain());
        System.setIn(sysInBackup);
    }
}
