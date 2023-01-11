import java.util.Scanner;

public class DragonCave {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println(intro());
        try {
            System.out.println(outcome(Integer.parseInt(in.nextLine())));
        }
        catch(Exception e)
        {
            System.out.println("Invalid input");
        }
    }
    public static String intro() {
        return """
                You are in a land full of dragons. In front of you,
                you see two caves. In one cave, the dragon is friendly
                and will share his treasure with you. The other dragon
                is greedy and hungry and will eat you on sight.
                Which cave will you go into? (1 or 2)
                """;
    }

    public static String outcome(int choice) throws Exception {
        if (choice == 1)
            return """
                You approach the cave...
                It is dark and spooky...
                A large dragon jumps out in front of you! He opens his jaws and...
                Gobbles you down in one bite!
                """;
        else if (choice == 2)
            return """
                You approach the cave...
                It is dark and spooky...
                A large dragon jumps out in front of you! He opens his jaws and...
                Tells you where his treasure is!
                """;
        else throw new Exception();
    }
}