package ChatBot;

//@author: Nesterov Vladyslav
//@version: 0.0.1-11.10.2021


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run(args);
    }

    private int userAge;
    private int remainder3;
    private int remainder5;
    private int remainder7;
    private int userNumber;
    private int count;
    private int mainMenu;
    private int answer;

    private void run(String[] args) {

        System.out.println("=========================");

        BotInfo botinfo = new BotInfo("Bebra", "05.10.2021");

        System.out.println("Hello! My name is - " + botinfo.botName +
                "\nI was created: " + botinfo.botCreationDate);
        System.out.println("=========================");

        System.out.println("\nPlease, remind me your name :)");

        Scanner sc = new Scanner(System.in);
        System.out.println("What a great name you have, " + sc.nextLine());

        System.out.println("Let me guess your age.\n" +
                "Enter remainders of dividing your age by 3, 5 and 7.");

        remainder3 = sc.nextInt();
        remainder5 = sc.nextInt();
        remainder7 = sc.nextInt();
        System.out.println(
                "Remaining 3: " + remainder3 +
                        "\nRemaining 5: " + remainder5 +
                        "\nRemaining 7: " + remainder7);

        userAge = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;

        System.out.println("Your age is " + userAge + " ?" + " Thats a good time to start programming!");

    }
}

