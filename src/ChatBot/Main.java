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

        System.out.println("\n You know that i can count ? I can prove it !" +
                "\n Enter the number to which I should count");
        userNumber = sc.nextInt();

        for (int i = 0; i <= userNumber; i++) {
            System.out.println(i + "!");
        }
        System.out.println("See ?");

        System.out.println("\nDo you want to write some test ?" + " \n1 - YES" +
                "\n2 - NO");
        mainMenu = sc.nextInt();
        if (mainMenu == 1) {

            System.out.println("Okay, lets start!");
            System.out.println("Question 1: Java first version release date" + "\n1. 1990" +
                    "\n2. 1996" + "\n3. 1998");
            answer = sc.nextInt();
            if (answer == 2) {
                System.out.println("TRUE");
            } else {
                System.out.println("FALSE");
                System.out.println("Sorry, but you can't continue test");
                System.exit(0);
            }

            System.out.println("Question 2: What is NOT acces modificator ?" + "\n1. private" + "\n2. protected" +
                    "\n3. public" + "\n4. float");
            answer = sc.nextInt();
            if (answer == 4) {
                System.out.println("TRUE");
            } else {
                System.out.println("FALSE");
                System.out.println("Sorry, but you can't continue test");
                System.exit(0);
            }

            System.out.println("Question 3: Which of the presented data types for floating point values weighs more?" +
                    "\n1. double" + "\n2. float");
            answer = sc.nextInt();
            if (answer == 1) {
                System.out.println("TRUE");
            } else {
                System.out.println("FALSE");
                System.out.println("Sorry, but you can't continue test");
                System.exit(0);
            }

            System.out.println("It was awesome !" + "\nWanna other tests ?" +
                    "\n1. YES" + "\n2. NO");
            answer = sc.nextInt();
            if (answer == 1) {
                System.out.println("OTHER TEST WILL BE ADDED LATER");
            } else {
                System.out.println("Okay, have a nice day, Bro !!!");
                System.exit(0);
            }

        }
        if (mainMenu == 2) {
            System.out.println("Okay, have a nice day, Bro !!!");
        }
        while (mainMenu == 123) {
            System.out.println("My Lord is **Nesterov Vladyslav**");
        }
    }
}

