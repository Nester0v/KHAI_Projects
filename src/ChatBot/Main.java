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

    }
}

