package TicTacToe;

//@author: Nesterov Vladyslav
//@version: 0.0.1-21.12.2021

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    static String[] board;
    static String turn;

    public static void main(String[] args) {
        TicTacToe main = new TicTacToe();
        main.run();
    }

    private void run() {

        Scanner in = new Scanner(System.in);
        board = new String[9];
        turn = "X";
        String winner = null;

        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }
        System.out.println("I===================I");
        System.out.println(" КРЕСТИКИ-НОЛИКИ 3Х3");
        System.out.println("I===================I");
        printBoard();

        System.out.println(
                "X ходит первым. Выберете номер клетки куда поставить X:");

        while (winner == null) {
            int numInput;


            try {
                numInput = in.nextInt();
                if (!(numInput > 0 && numInput <= 9)) {
                    System.out.println(
                            "Неправильное значение, повторите попытку:");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println(
                        "Неправильное значение, повторите попытку:");
                continue;
            }

            if (board[numInput - 1].equals(
                    String.valueOf(numInput))) {
                board[numInput - 1] = turn;

                if (turn.equals("X")) {
                    turn = "O";
                } else {
                    turn = "X";
                }

                printBoard();
                winner = checkWinner();
            } else {
                System.out.println(
                        "Введите в другую клетку, данная клетка уже занята:");
            }
        }
        if (winner.equalsIgnoreCase("ничья")) {
            System.out.println(
                    "Ничья! Спасибо за игру!");
        } else {
            System.out.println(
                    winner
                            + " - Победил! Спасибо за игру!");
        }
    }

    //Рисуем доску
    private void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | "
                + board[1] + " | " + board[2]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | "
                + board[4] + " | " + board[5]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | "
                + board[7] + " | " + board[8]
                + " |");
        System.out.println("|---|---|---|");
    }

    //Метод определяющий победителя
    static String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;

            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }
            //Победа Х
            if (line.equals("XXX")) {
                return "X";
            }

            //Победа O
            else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(board).contains(
                    String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return "ничья";
            }
        }

        System.out.println(
                turn + " ходит. Выберете номер клетки куда поставить "
                        + turn + ":");
        return null;
    }
}