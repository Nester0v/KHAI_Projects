package Matrix;

import java.util.Scanner;

//@author: Nesterov Vladyslav
//@version: 0.0.1-21.12.2021

public class Matrix {
    static Scanner inp = new Scanner(System.in);
    static int cols = 0, rows = 0;
    static final int size = 10, N = 2;
    static int colRow[][] = new int[N][N];//1-номер матрицы
    static double matrix[][][] = new double[N][size][size];

    public static void main(String[] argv) {
        Matrix main = new Matrix();
        main.run();
    }

    private void run() {
        String act;
        while (true) {
            System.out.println(
                    "1. Add matrices\n" +
                            "2. Multiply matrix by a constant\n" +
                            "3. Multiply matrices\n" +
                            "4.Calculate determinant\n" +
                            "5.Transponse matrix\n" +
                            "6.Inverse matrix\n" +
                            "7. Exit");
            act = inp.nextLine();
            if (act.isEmpty()) {
                System.out.println("Nothing input.");
                continue;
            }
            switch (act.charAt(0)) {
                case '1':
                    setMatrix(2, act.charAt(0));
                    addMatrix();
                    break;
                case '2':
                    setMatrix(1, act.charAt(0));
                    multiply_const();
                    break;
                case '3':
                    while (true) {
                        setMatrix(2, act.charAt(0));
                        if (colRow[0][0] == colRow[1][1] && colRow[0][0] != -1) {
                            multiply();
                            break;
                        } else if ((colRow[0][0] == 101 && colRow[1][1] == 102) || cols == 1001 || rows == 1001)
                            continue;
                        else
                            System.out.println("Number of columns in 1 != number of rows in 2.");
                    }
                    break;
                case '4':
                    while (true) {
                        if (!setMatrix(1, act.charAt(0)) && (colRow[0][0] == 2 || colRow[0][0] == 3)) {
                            determinate("", matrix[0]);
                            break;
                        }
                    }
                    break;
                case '5':
                    setMatrix(1, act.charAt(0));
                    break;
                case '6':
                    while (true) {
                        if (!setMatrix(1, act.charAt(0)) && (colRow[0][0] == 2 || colRow[0][0] == 3)) {
                            double dt;
                            if (colRow[0][0] == 2) {
                                dt = determinate("", matrix[0]);
                                transponse(dt);
                            }
                            if (colRow[0][0] == 3) {
                                dt = determinate("", matrix[0]);
                                System.out.println(dt);
                                transponse(dt);
                            }
                            break;
                        }
                    }
                    break;
                case '7':
                    System.exit(0);
                default:
                    System.out.println("Incorrect input.");
            }
        }
    }

    static boolean setMatrix(int matrices, char b) {
        boolean error_status = false;
        zeros("all", 100);
        int i, y, ind, num = 0;
        boolean err = false;
        String[] names = {"first", "second"};
        String row = new String();
        StringBuilder buf = new StringBuilder();
        for (; num < matrices; num++) {
            System.out.println("Enter " + names[num] + " matrix size(10x10-max)\nColumns:");
            cols = chInp(inp.nextLine(), size);
            if (cols == 1001) {
                colRow[0][0] = -1;
                break;
            }
            System.out.println("Rows:");
            rows = chInp(inp.nextLine(), size);
            if (rows == 1001) {
                colRow[0][0] = -1;
                break;
            }
            if (b == '4') {
                if (cols != rows) {
                    System.out.println("Matrix must be square.");
                    error_status = true;
                    break;
                } else if (cols > 3) {
                    System.out.println("Maximum grade of matrix is 3.");
                    error_status = true;
                    break;
                }
            }
            colRow[num][0] = cols;
            colRow[num][1] = rows;
            if (b == '1' && num == 1) {
                if (colRow[num][0] != colRow[num - 1][0] || colRow[num][1] != colRow[num - 1][1]) {
                    System.out.println("Matrices sizes are not equal!");
                    num--;
                    continue;
                }
            }
            System.out.println("Enter matrix:");
            for (i = 0; i < rows; ) {
                row = inp.nextLine();
                if (row.isEmpty())
                    zeros(Integer.toString(i), num);
                else if (row.equalsIgnoreCase("back")) {
                    colRow[0][0] = 101;
                    colRow[1][1] = 102;
                    i = rows + 10;
                    break;
                } else {
                    for (ind = 0, y = 0; y < row.length(); y++) {
                        if (row.charAt(y) != '-' && !Character.isDigit(row.charAt(y)) && row.charAt(y) != ' ' && row.charAt(y) != '.' || !Character.isDigit(row.charAt(row.length() - 1))) {
                            System.out.println("Row is incorrectly input! Enter numbers only and space between them.");
                            zeros(Integer.toString(i), num);
                            buf.replace(0, buf.length(), "");
                            err = true;
                            break;
                        }
                        if (Character.isDigit(row.charAt(y)) || row.charAt(y) == '.' || row.charAt(y) == '-') {
                            buf.append(row.substring(y, y + 1));
                        }
                        if (row.charAt(y) == ' ' || y == row.length() - 1) {
                            matrix[num][i][ind] += Double.valueOf(buf.toString());
                            buf.replace(0, buf.length(), "");
                            ind++;
                            if (y == row.length() - 1) {
                                err = false;
                                break;
                            }
                        }
                    }
                    if (err == false)
                        i++;
                }
            }
            if (i == rows + 10)
                break;
            displayMatrix(matrices);
            if (b == '5') {
                int s;
                s = (colRow[0][0] > colRow[0][1]) ? colRow[0][0] : colRow[0][1];
                System.out.println("Enter transponding way(main, collateral diagonal):");
                transp(matrix[0], inp.nextLine(), s);
            }
        }
        return error_status;
    }

    static void addMatrix() {
        int i, y;
        System.out.println("Summary matrix is:");
        for (i = 0; i < colRow[0][1]; i++) {
            for (y = 0; y < colRow[0][0]; y++) {
                if (matrix[0][i][y] % 1 == 0 && matrix[1][i][y] % 1 == 0)
                    System.out.printf("%.0f ", matrix[0][i][y] + matrix[1][i][y]);
                else
                    System.out.printf("%.2f ", matrix[0][i][y] + matrix[1][i][y]);
            }
        }
    }

    static void multiply_const() {
        int i, y;
        double multipl;
        System.out.println("Enter constant:");
        multipl = inp.nextDouble();
        inp.nextLine();
        System.out.println("The multiplied matrix is:");
        for (i = 0; i < colRow[0][1]; i++) {
            for (y = 0; y < colRow[0][0]; y++) {
                if (matrix[0][i][y] % 1 == 0)
                    System.out.printf("%.0f ", matrix[0][i][y] * multipl);
                else
                    System.out.printf("%.2f ", matrix[0][i][y] * multipl);
            }
        }
    }

    static void multiply() {
        double mult;
        int i, y, n;
        System.out.println("The result matrix is:");
        for (i = 0; i < colRow[0][1]; i++) {//i-ряды первой y-стобцы второй
            for (y = 0; y < colRow[1][0]; y++) {
                for (n = 0, mult = 0; n < colRow[0][1]; n++) {
                    mult += matrix[0][i][n] * matrix[1][n][y];
                }
                if (mult % 1 == 0)
                    System.out.printf("%.0f ", mult);
                else
                    System.out.printf("%.2f ", mult);
            }
        }
    }

    static double dt;

    static double determinate(String parameter, double[][] matrix) {
        final int sign[] = {1, -1};
        int i, y;
        int pos[][] = new int[3][2];
        int pos2[][] = {{0, 2}, {1, 3}};
        int pos3[][][] = {{{4, 7}, {5, 8}}, {{1, 7}, {2, 8}}, {{1, 4}, {2, 5}}};
        int SIZE = colRow[0][0];
        double determ = 0;
        if (colRow[0][0] == 2) {
            pos = pos2;
            colRow[0][0]--;
        }
        if (colRow[0][0] == 3)
            pos = pos3[0];
        if (parameter.equalsIgnoreCase("minors")) {
            double[][] matrixMinors = new double[3][3];
            for (i = 0; i < pos3.length; i++) {
                pos = pos3[i];
                for (y = 0; y < colRow[0][0]; y++) {
                    determ = ((matrix[pos[0][0] / SIZE][pos[0][0] % SIZE] * matrix[pos[1][1] / SIZE][pos[1][1] % SIZE]) - (matrix[pos[0][1] / SIZE][pos[0][1] % SIZE] * matrix[pos[1][0] / SIZE][pos[1][0] % SIZE]));
                    matrixMinors[i][y] = determ;
                    if (colRow[0][0] == 3 && y < 2) {
                        pos[y][0]--;
                        pos[y][1]--;
                    }
                }
            }
            display(matrixMinors, 3, "Minors(3rd grade)");
            matrixMinors = transp(matrixMinors, "main diagonal", 3);
            for (i = 1; i < matrixMinors.length * matrixMinors.length - 1; i += 2) {
                matrixMinors[i / matrixMinors.length][i % matrixMinors.length] *= -1;
            }
            display(matrixMinors, 3, "Multiplied by -1");
            multiplyByConst(matrixMinors, 1 / dt, 3);
        } else {
            for (i = 0; i < colRow[0][0]; i++) {
                determ += Matrix.matrix[0][0][i] * sign[i % 2] * ((Matrix.matrix[0][pos[0][0] / SIZE][pos[0][0] % SIZE] * Matrix.matrix[0][pos[1][1] / SIZE][pos[1][1] % SIZE]) - (Matrix.matrix[0][pos[0][1] / SIZE][pos[0][1] % SIZE] * Matrix.matrix[0][pos[1][0] / SIZE][pos[1][0] % SIZE]));
                if (colRow[0][0] == 1) {
                    determ /= Matrix.matrix[0][0][i];
                }
                if (i < colRow[0][0] - 1 && colRow[0][0] == 3) {
                    pos[i][0]--;
                    pos[i][1]--;
                }
            }
            dt = determ;
            System.out.printf("Determinant is: %.2f.\n", determ);
        }
        return determ;
    }

    static double[][] transponse(double determinant) {
        final int TRANSP_S = 3;
        int size, i, y, ind_t = 1;
        double matrix_t[][] = new double[TRANSP_S][TRANSP_S], temp;
        if (colRow[0][0] == 1 || colRow[0][0] == 2) {
            size = 2;
            for (i = size - 1; i >= 0; i--) {
                for (y = size - 1; y >= 0; y--) {
                    matrix_t[ind_t ^ i][ind_t ^ y] = matrix[0][i][y];
                }
            }
            matrix_t[0][1] *= -1;
            matrix_t[1][0] *= -1;
            temp = matrix_t[1][0];
            matrix_t[1][0] = matrix_t[0][1];
            matrix_t[0][1] = temp;
            multiplyByConst(matrix_t, 1 / determinant, size);
        }
        if (colRow[0][0] == 3) {
            determinate("minors", matrix[0]);
        }
        return matrix_t;
    }

    static double[][] transp(double[][] matrix, String line, int size) {
        double[][] tr_matrix = new double[size][size];
        int i, y;
        if (line.equalsIgnoreCase("main diagonal")) {
            for (i = 0; i < size; i++) {
                for (y = 0; y < size; y++) {
                    tr_matrix[y][i] = matrix[i][y];
                }
            }
        }
        if (line.equalsIgnoreCase("collateral diagonal")) {
            for (i = 0; i < size; i++) {
                for (y = 0; y < size; y++) {
                    tr_matrix[i][y] = matrix[size - 1 - y][size - 1 - i];
                }
            }
        }
        if (line.equalsIgnoreCase("horizont")) {
            for (i = 0; i < size; i++) {
                for (y = 0; y < size; y++) {
                    tr_matrix[i][y] = matrix[size - 1 - i][y];
                }
            }
        }
        if (line.equalsIgnoreCase("vertical")) {
            for (i = 0; i < size; i++) {
                for (y = 0; y < size; y++) {
                    tr_matrix[i][y] = matrix[i][size - 1 - y];
                }
            }
        }
        display(tr_matrix, size, "Transponed by " + line);
        return tr_matrix;
    }

    static void multiplyByConst(double matrix[][], double multipl, int size) {
        int i, y;
        for (i = 0; i < size; i++) {
            for (y = 0; y < size; y++) {
                matrix[i][y] *= multipl;
            }
        }
        display(matrix, size, "Inverse");
    }

    static void display(double[][] matr, int size, String name) {
        int i, y;
        System.out.println(name + " matrix:");
        for (i = 0; i < size; i++) {
            for (y = 0; y < size; y++) {
                if (matr[i][y] % 1 != 0) {
                    System.out.printf("%.2f ", matr[i][y]);
                } else if ((matr[i][y]) % 0.1 == 0) {
                    System.out.printf("%.1f ", matr[i][y]);
                } else {
                    System.out.printf("%.0f ", matr[i][y]);
                }
            }
            System.out.println(" ");
        }
    }

    static void displayMatrix(int matrices) {
        int i, y, b;
        for (b = 0; b < matrices; b++) {
            System.out.println((b + 1) + "matrix:");
            for (i = 0; i < colRow[b][1]; i++)
                for (y = 0; y < colRow[b][0]; y++) {
                    if (matrix[b][i][y] % 1 != 0)
                        System.out.printf("%.2f", matrix[b][i][y]);
                    else
                        System.out.printf("%.0f", matrix[b][i][y]);
                    if ((y + 1) % colRow[b][0] == 0)
                        System.out.println("");
                    else
                        System.out.printf(" ");
                }
        }
    }

    static int chInp(String a, int lim) {
        int i, val = -1;
        boolean r = true;
        if (a.equalsIgnoreCase("back")) {
            val = 1001;
        }
        while (val != 1001) {
            for (i = 0; i < a.length(); i++) {
                if (!Character.isDigit(a.charAt(i))) {
                    System.out.println("Enter numbers only!");
                    break;
                }
            }
            if (i < a.length()) {
                System.out.println("Enter again:");
                a = inp.nextLine();
            } else {
                val = Integer.valueOf(a);
                if (val < 0 || val > lim) {
                    System.out.println("Enter values 1 to " + lim + ".");
                    a = inp.nextLine();
                } else
                    break;
            }

        }
        return val;
    }

    static void zeros(String str_num, int num) {
        int i, y, b;
        if (str_num.equalsIgnoreCase("all")) {
            if (num == 100) {
                for (b = 0; b < N; b++)
                    for (i = 0; i < size; i++)
                        for (y = 0; y < size; y++)
                            matrix[b][i][y] = 0;
            } else {
                for (i = 0; i < rows; i++)
                    for (y = 0; y < cols; y++)
                        matrix[num][i][y] = 0;
            }
        } else if (num != 100) {
            for (y = 0; y < cols; y++)
                matrix[num][Integer.valueOf(str_num)][y] = 0;
        }
    }
}


