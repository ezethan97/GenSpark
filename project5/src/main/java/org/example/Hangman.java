package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws Exception {
        ArrayList<String> words = readFile();
        Random rand = new Random();
        do {
            ArrayList<String> guesses = new ArrayList<>();
            String word = words.get(rand.nextInt(words.size())).toLowerCase(), correct = "", wrong = "Missed letters: ";
            for (int i = 0; i < word.length(); i++)
                correct += "_";
            System.out.println("H A N G M A N");
            Object[] gui = check(guesses, word, "", correct, wrong, -1);
            while (Integer.parseInt(gui[2].toString()) < 7) {
                try {
                    String i = input();
                    try {
                        gui = check(guesses, word, i, correct, wrong, Integer.parseInt(gui[2].toString()));
                    } catch (Exception e) {
                        System.out.println("You have already guessed that letter, try again");
                    }
                    correct = gui[1].toString();
                    wrong = gui[0].toString();
                    guesses.add(i);
                    if (!gui[1].toString().contains("_")) break;
                }
                catch (Exception e){
                    System.out.println("Invalid input");
                }
            }
            System.out.println(ending(Integer.parseInt(gui[2].toString()), word));
        }
        while(playAgain());
    }

    public static ArrayList<String> readFile() throws FileNotFoundException {
        File f = new File("C:\\Users\\ET\\IdeaProjects\\Hangman\\src\\main\\java\\org\\example\\words.txt");
        ArrayList<String> words = new ArrayList<>();
        Scanner read = new Scanner(f);
        while(read.hasNextLine())
            words.add(read.nextLine());
        read.close();
        return words;
    }

    public static String input() throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.println("Guess a letter");
        String in = s.nextLine();
        if (in.length() != 1 || in.compareToIgnoreCase("a") < 0 || in.compareToIgnoreCase("z") > 0)
            throw new Exception();
        return in;
    }

    /**
     * Checks the letter guessed against the word to be guessed
     * @param guessed An arrayList containing the letters already guessed
     * @param word the word to be guessed
     * @param guess the letter guessed
     * @param right a string containing the word to be guessed with underscore characters replacing letters not already guessed
     * @param wrong a string containing the phrase "Missed Letters: " along with the incorrectly guessed letters separated by commas
     * @param wrongGuesses the number of incorrect guesses so far
     * @return an array containing the updated wrong string, the updated right string, and the updated number of incorrect guesses
     * @throws Exception
     */
    public static Object[] check(ArrayList<String> guessed, String word, String guess, String right, String wrong, int wrongGuesses) throws Exception {
        //List<String> letters = new ArrayList<>(Arrays.stream(word.split("")).toList());
        boolean incorrect = true;
        StringBuilder correct = new StringBuilder(right);
        if (guessed.contains(guess))
            throw new Exception();
        for(int i = 0; i < word.length(); i++)
        {
            if (Character.toString(word.charAt(i)).equals(guess)) {
                correct.setCharAt(i,word.charAt(i));
                incorrect = false;
            }
        }
        if(incorrect) {
            wrong += guess;
            wrongGuesses++;
        }
        System.out.println(getDrawing(wrongGuesses));
        System.out.println(wrong + "\n" + correct);
        return new Object[]{wrong.toString(), correct.toString(), wrongGuesses};
    }

    public static boolean playAgain(){
        boolean valid;
        do {
            try {
                valid = true;
                Scanner in = new Scanner(System.in);
                System.out.println("Would you like to play again? (yes or no)");
                String again = in.nextLine();
                if (again.equals("yes"))
                    return true;
                else if (again.equals("no")) {
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

    public static String ending(int wrongGuesses, String word)
    {
        return wrongGuesses == 7?"You have been hanged! The word was \"" + word + "\"!":"Yes! The word is \"" + word + "\"! You have won!";
    }

    public static String getDrawing(int n)
    {
        return createDrawings().get(n);
    }


    public static ArrayList<String> createDrawings()
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

        return drawings;
    }
}