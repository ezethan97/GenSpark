import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {

        Random rand = new Random();
        String name = greeting(), result = "";
        do {
            int guesses = 0, current = 0, secret = rand.nextInt(20) + 1;
            while (guesses < 6 && result != "correct") {
                try {
                    current = input();
                    result = check(current, secret);
                    guesses++;
                    if(result != "correct")
                        System.out.println(result);
                    else
                        System.out.println("Good job, " + name + "! You guessed my number in " + guesses + " guesses");
                }
                catch (Exception e) {
                    System.out.println("Invalid input");
                }
            }
        }
        while (playAgain());
    }


    public static String greeting()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! What is your name?");
        String name = in.nextLine();
        System.out.println("Well, " + name + ", I am thinking of a number between 1 and 20.");
        return name;
    }

    public static int input()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Take a guess.");
        return Integer.parseInt(in.nextLine());
    }
    public static String check(int current, int secret) throws Exception {
            if (current < 1 || current > 20) throw new Exception();
            if (current > secret)
                return("Your guess is too high");
            if (current < secret)
                return("Your guess is too low");
            return "correct";

    }

    public static boolean playAgain(){
        boolean valid;
        do {
            try {
                valid = true;
                Scanner in = new Scanner(System.in);
                System.out.println("Would you like to play again? (y or n)");
                String again = in.nextLine();
                if (again.equals("y"))
                    return true;
                else if (again.equals("n")) {
                    return false;
                } else throw new Exception();
            }
            catch (Exception e) {
                System.out.println("Invalid input");
                valid = false;
            }
        }
        while(!valid);
        return valid;
    }
}