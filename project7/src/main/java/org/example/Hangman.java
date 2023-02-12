package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hangman {

    public enum state {
        GUESS, EXITLOOP, RESET, EXIT
    }
    public static void main(String[] args){
        state gameState = state.RESET;
        ArrayList<String> words = readFile();
        Random rand = new Random();
        Object[] gui = {};
        boolean again = true;
        ArrayList<String> guesses = new ArrayList<>();
        String word = "", wrong = "", correct = "";
        while (again) {
            switch (gameState) {
                case RESET:
                    guesses = new ArrayList<>();
                    word = words.get(rand.nextInt(words.size())).toLowerCase();
                    wrong = "Missed letters: ";
                    correct = word.chars().mapToObj(ch -> (char)ch).map(n -> '_').map(c -> c.toString()).collect(Collectors.joining());
                    System.out.println(correct);
                    System.out.println("H A N G M A N");
                    try {
                        gui = check(guesses, word, "", correct, wrong, -1);
                    }
                    catch (Exception e){
                    }
                    gameState = state.GUESS;
                    break;
                case GUESS:
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
                        if (!gui[1].toString().contains("_") || Integer.parseInt(gui[2].toString()) >= 7)
                            gameState = state.EXITLOOP;
                    }
                    catch (Exception e){
                        System.out.println("Invalid input");
                    }
                    break;

                case EXITLOOP:
                    System.out.println(ending(Integer.parseInt(gui[2].toString()), word));
                    saveScore(Integer.parseInt(gui[2].toString()));
                    gameState = state.EXIT;
                    break;

                case EXIT:
                    try{
                        again = playAgain();
                        gameState = state.RESET;
                    }
                    catch (Exception e) {
                        System.out.println("Invalid input");
                    }
                }
            }
    }

    public static ArrayList<String> readFile() {
        try {
            File f = new File("C:\\Users\\ET\\projects\\GenSpark\\project7\\src\\main\\java\\org\\example\\words.txt");
            Stream<String> words = Files.lines(f.toPath());
            return new ArrayList<String>(words.collect(Collectors.toList()));
        }
        catch (IOException e)
        {
            System.out.println("Invalid file");
        }
        return null;
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
        AtomicBoolean incorrect = new AtomicBoolean(true);
        AtomicInteger index = new AtomicInteger();
        String caseGuess = guess.toLowerCase();
        if (guessed.contains(guess))
            throw new Exception();
        right = right.chars().mapToObj(c -> String.valueOf ((char)c)).map(i -> {
                    if(Character.toString(word.charAt(index.get())).equals(caseGuess)){
                        i = caseGuess;
                        incorrect.set(false);
                    }
                    index.getAndIncrement();
                    return i;
                })
                .collect(Collectors.joining());
        if(incorrect.get()) {
            wrong += caseGuess;
            wrongGuesses++;
        }
        System.out.println(getDrawing(wrongGuesses));
        System.out.println(wrong + "\n" + right);
        return new Object[]{wrong.toString(), right.toString(), wrongGuesses};
    }

    public static void saveScore(int score)
    {
        String[] data = {};
        ArrayList<Integer> scores = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = s.nextLine();
        File f = new File("C:\\Users\\ET\\projects\\GenSpark\\project7\\src\\main\\java\\org\\example\\scores.txt");
        try {
            FileWriter fw = new FileWriter(f,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(name + " " + score);
            bw.newLine();
            bw.close();
        }
        catch (Exception e){
            System.out.println("File not found");
        }

        String lineSeparator = System.lineSeparator();
        try {
            data = Files.readString(f.toPath()).split(lineSeparator);
        } catch (IOException e) {
        }
        Arrays.stream(data).map(i -> i.split(" ")).forEach(j -> scores.add(Integer.valueOf(j[1])));
        Collections.sort(scores);
        if(score <= scores.get(0))
            System.out.println("You got the high score!");
    }

    public static boolean playAgain() throws Exception {
        boolean valid;
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to play again? (yes or no)");
        String again = in.nextLine();
        if (again.equals("yes"))
            return true;
        else if (again.equals("no")) {
            return false;
        } else throw new Exception();
    }

    public static String ending(int wrongGuesses, String word)
    {
        return wrongGuesses == 7?"You have been hanged! The word was \"" + word + "\"!":"Yes! The word is \"" + word + "\"! You have won!";
    }

    public static String getDrawing(int n)
    {
        return createDrawings()[n];
    }

    public static String[] createDrawings()
    {
        String[] drawings = {};
        File f = new File("C:\\Users\\ET\\projects\\GenSpark\\project7\\src\\main\\java\\org\\example\\art.txt");
        try {
            String lineSeparator = System.lineSeparator() + System.lineSeparator();
            drawings = Files.readString(f.toPath()).split(lineSeparator);
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return drawings;
    }
}