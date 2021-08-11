package tictactoe;

import java.util.Scanner;

public class Main {
    final static int X_WINS = 264;
    final static int O_WINS = 237;
    final static int DRAW = 222;
    final static int GAME_NOT_FINISHED = 111;
    final static int IMPOSSIBLE = 333;

    public static void main(String[] args) {
        char[][] cells = new char[3][3];
        char fig = 'X';

        initCells(cells);

        printCells(cells);

        while (check(cells) == GAME_NOT_FINISHED) {
            makeMove(cells, fig);
            fig = fig == 'X' ? 'O' : 'X';
            printCells(cells);
        }

        switch (check(cells)) {
            case X_WINS:
                System.out.println("X wins");
                break;
            case O_WINS:
                System.out.println("O wins");
                break;
            case DRAW:
                System.out.println("Draw");
                break;
            default:
                System.out.println("Impossible");
                break;
        }
    }

    public static void initCells(char[][]cells) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = ' ';
            }
        }
    }

    public static void printCells(char[][] cells) {
        System.out.printf("---------%n");
        System.out.printf("| %c %c %c |%n", cells[0][0], cells[0][1], cells[0][2]);
        System.out.printf("| %c %c %c |%n", cells[1][0], cells[1][1], cells[1][2]);
        System.out.printf("| %c %c %c |%n", cells[2][0], cells[2][1], cells[2][2]);
        System.out.printf("---------%n");
    }

    public static void readCells(char[][] cells) {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.print("Enter cells: ");
        input = scanner.nextLine();
        input = input.replace('_', ' ');

        for (int i = 0; i < input.length(); i++) {
            cells[i / 3][i % 3] = input.charAt(i);
        }
    }

    public static int win(char[][] c) {
        int temp;
        final int X_WINS = 264;
        final int O_WINS = 237;

        for (int i = 0; i < 3; i++) {
            temp = c[i][0] + c[i][1] + c[i][2];

            if (temp == X_WINS || temp == O_WINS) {
                return temp;
            }

            temp = c[0][i] + c[1][i] + c[2][i];

            if (temp == X_WINS || temp == O_WINS) {
                return temp;
            }
        }

        temp = c[0][0] + c[1][1] + c[2][2];

        if (temp == X_WINS || temp == O_WINS) {
            return temp;
        }

        temp = c[0][2] + c[1][1] + c[2][0];

        return temp;
    }

    public static boolean impossibleByDoubleWin(char[][] c) {
        boolean xWins = false;
        boolean oWins = false;
        int temp;
        final int X_WINS = 264;
        final int O_WINS = 237;

        for (int i = 0; i < 3; i++) {
            temp = c[i][0] + c[i][1] + c[i][2];

            if (temp == X_WINS) {
                xWins = true;
            }

            if (temp == O_WINS) {
                oWins = true;
            }

            temp = c[0][i] + c[1][i] + c[2][i];

            if (temp == X_WINS) {
                xWins = true;
            }

            if (temp == O_WINS) {
                oWins = true;
            }
        }

        temp = c[0][0] + c[1][1] + c[2][2];

        if (temp == X_WINS) {
            xWins = true;
        }

        if (temp == O_WINS) {
            oWins = true;
        }

        temp = c[0][2] + c[1][1] + c[2][0];

        if (temp == X_WINS) {
            xWins = true;
        }

        if (temp == O_WINS) {
            oWins = true;
        }

        return xWins && oWins;
    }

    public static void makeMove(char[][] cells, char fig){
        Scanner s = new Scanner(System.in);
        int row = 0;
        int col = 0;
        String rowStr;
        String colStr;
        boolean goodInput = false;

        while(!goodInput) {
            System.out.print("Enter the coordinates: ");
            rowStr = s.next();

            if(!rowStr.matches("\\d+")) {
                System.out.println("You should enter numbers!");
                continue;
            }

            colStr = s.next();

            if(!colStr.matches("\\d+")) {
                System.out.println("You should enter numbers!");
                continue;
            }

            row = Integer.parseInt(rowStr);
            col = Integer.parseInt(colStr);

            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            row--;
            col--;

            if (cells[row][col] != ' '){
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            goodInput = true;
        }

        cells[row][col] = fig;
    }

    public static int check(char[][] cells){
        int numX = 0;
        int numO = 0;
        int res;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == 'X'){
                    numX++;
                }

                if (cells[i][j] == 'O'){
                    numO++;
                }
            }
        }

        if (win(cells) == X_WINS) {
            res = X_WINS;
        } else if (win(cells) == O_WINS) {
            res = O_WINS;
        } else if (numX + numO < 9){
            res = GAME_NOT_FINISHED;
        } else {
            res = DRAW;
        }

        return res;
    }
}