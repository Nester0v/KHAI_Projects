package Hangman;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//@author: Nesterov Vladyslav
//@version: 0.0.1-11.10.2021

public class Hangman {

    public static void main(String[] args) {
        Hangman main = new Hangman();
        main.run();
    }

    private void run() {
        Hangman hangmanGame = new Hangman();
        hangmanGame.newGame();
        hangmanGame.play();
    }

    public static final String[] WORDS = {
            "COLLECTION", "THREAD", "STRING",
            "INT", "BOOLEAN", "FINAL", "PRIVATE",
            "PROTECTED", "IMPLEMENTS", "CLASS"
    };

    public static Random random = new Random();
    public static final int maxErrors = 8;
    private String wordToFind;
    private char[] wordFound;
    private int nbErrors;
    private ArrayList<String> letters = new ArrayList<>();

    private String nextWordToFind() {
        return WORDS[random.nextInt(WORDS.length)];
    }

    public void newGame() {
        nbErrors = 0;
        letters.clear();
        wordToFind = nextWordToFind();

        wordFound = new char[wordToFind.length()];

        for (int i = 0; i < wordFound.length; i++) {
            wordFound[i] = '_';
        }
    }

    public boolean wordFound() {
        return wordToFind.contentEquals(new String(wordFound));
    }

    private void enter(String c) {

        if (!letters.contains(c)) {

            if (wordToFind.contains(c)) {

                int index = wordToFind.indexOf(c);

                while (index >= 0) {
                    wordFound[index] = c.charAt(0);
                    index = wordToFind.indexOf(c, index + 1);
                }
            } else {
                nbErrors++;
            }
            letters.add(c);
        }
    }

    private String wordFoundContent() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < wordFound.length; i++) {
            stringBuilder.append(wordFound[i]);

            if (i < wordFound.length - 1) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public void play() {
        try (Scanner input = new Scanner(System.in)) {

            while (nbErrors < maxErrors) {
                System.out.println("\nВведите букву : ");

                String str = input.next();

                if (str.length() > 1) {
                    str = str.substring(0, 1);
                }

                enter(str);

                System.out.println("\n" + wordFoundContent());

                if (wordFound()) {
                    System.out.println("\nПоздравляю! Вы победили! :)");
                    break;
                } else {
                    System.out.println("\n=> Попыток осталось : " + (maxErrors - nbErrors));
                }
            }

            if (nbErrors == maxErrors) {
                System.out.println("\nСожалею. Вы проиграли! :(");
                System.out.println("=> Искомое слово : " + wordToFind);
            }
        }
    }
}