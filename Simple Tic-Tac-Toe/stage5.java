package tictactoe;

import java.util.Scanner;

public class Main {
    public static boolean checkWinners(char[][] ch, char XO) {
        for (int i = 0; i < ch.length; i++) {
            if (ch[i][0] == ch[i][1] && ch[i][1] == ch[i][2] && ch[i][0] == XO) {
                return true;
            } else if (ch[0][i] == ch[1][i] && ch[0][i] == ch[2][i] && ch[0][i] == XO) {
                return true;
            } else if (ch[0][0] == ch[1][1] && ch[1][1] == ch[2][2] && ch[0][0] == XO
                    ||  ch[0][2] == ch[1][1] && ch[1][1] == ch[2][0] && ch[0][2] == XO) {
                return true;
            }
        }
        return false;
    }

    public static char[][] createGrid() {
        char[][] ch = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ch[i][j] = ' ';
            }
        }
        return ch;
    }

    public static void showCell(char[][] ch) {
        // show CELLS
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(ch[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void main(String[] args) {
        // write your code here
        boolean isChecked= true;
        Scanner input = new Scanner(System.in);
        char[][] grid;
        grid = createGrid();
        showCell(grid);
        int count = 0;

        while (isChecked) {
            System.out.print("Enter the coordinates: ");
            String coordinates = input.nextLine();

            try {
                String[] userInput = coordinates.split(" ");
                int firstIndex = Integer.parseInt(userInput[0]);
                int secondIndex = Integer.parseInt(userInput[1]);
                // X first, then O's turn
                // add X
                if ((firstIndex > 3 || secondIndex > 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
                else if ((grid[firstIndex - 1][secondIndex - 1] == ' ' || grid[firstIndex - 1][secondIndex - 1] == '_') && count % 2 == 0) {
                    grid[firstIndex - 1][secondIndex - 1] = 'X';
                    showCell(grid);
                    count++;
                }
                // add O
                else if ((grid[firstIndex - 1][secondIndex - 1] == ' ' || grid[firstIndex - 1][secondIndex - 1] == '_') && count % 2 != 0) {
                    grid[firstIndex - 1][secondIndex - 1] = 'O';
                    showCell(grid);
                    count++;
                } else if (grid[firstIndex - 1][secondIndex - 1] != ' ' || grid[firstIndex - 1][secondIndex - 1] != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                }

                // check who wins
                if (checkWinners(grid, 'X')) {
                    System.out.println("X wins");
                    isChecked = false;
                } else if (checkWinners(grid, 'O')) {
                    System.out.println("O wins");
                    isChecked = false;
                } else if (count == 9) {
                    System.out.println("Draw");
                    isChecked = false;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        }
    }


}
