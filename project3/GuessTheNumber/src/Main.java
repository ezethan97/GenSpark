import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int guesses;
        int current;
        Random rand = new Random();
        String again;
        int secret;
        try {
            do {
                guesses = 0;
                current = 0;
                secret = rand.nextInt(20) + 1;
                System.out.println("Hello! What is your name?");
                String name = in.nextLine();
                System.out.println("Well, " + name + ", I am thinking of a number between 1 and 20.");
                while (guesses < 6 && current != secret) {
                    System.out.println("Take a guess.");
                    current = Integer.parseInt(in.nextLine());
                    if (current < 1 || current > 20) throw new Exception();
                    if (current > secret)
                        System.out.println("Your guess is too high");
                    if (current < secret)
                        System.out.println("Your guess is too low");
                    guesses++;
                }
                if (current == secret)
                    System.out.println("Good job, " + name + "! You guessed my number in " + guesses + " guesses");
                System.out.println("Would you like to play again? (y or n)");
                again = in.nextLine();
                if(!again.toLowerCase().equals("y") || !again.toLowerCase().equals("n")) throw new Exception();
            } while (again.equals("y"));
        }
        catch(Exception e)
        {
            System.out.println("Invalid input");
        }
    }
}