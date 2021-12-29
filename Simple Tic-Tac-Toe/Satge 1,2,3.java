package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String checkWinners(String cells) {
        String[] letters = cells.split("");
        List<String> list = new ArrayList<String>();
        String line = null;
        for (int i = 0; i < cells.length(); i++) {
            switch (i) {
                case 0:
                    line = letters[0] + letters[1] + letters[2];
                    list.add(line);
                    break;
                case 1:
                    line = letters[3] + letters[4] + letters[5];
                    list.add(line);
                    break;
                case 2:
                    line = letters[6] + letters[7] + letters[8];
                    list.add(line);
                    break;
                case 3:
                    line = letters[0] + letters[3] + letters[6];
                    list.add(line);
                    break;
                case 4:
                    line = letters[1] + letters[4] + letters[7];
                    list.add(line);
                    break;
                case 5:
                    line = letters[0] + letters[4] + letters[8];
                    list.add(line);
                    break;
                case 6:
                    line = letters[2] + letters[4] + letters[6];
                    list.add(line);
                    break;
                case 7:
                    line = letters[2] + letters[5] + letters[8];
                    list.add(line);
                    break;
            }
        }

        // check both of them win
        for (int i = 0; i < list.size(); i++) {
            for (int j = 1; j < list.size(); j++) {
                if (list.get(i).equals("XXX") && list.get(j).equals("OOO")) {
                    return "Impossible";
                }
            }
        }
        // check X or O wins
        for (int i = 0; i < list.size(); i++) {
            for (int j = 1; j < list.size(); j++) {
                if (list.get(i).equals("XXX") && !list.get(j).equals("OOO")) {
                    return "X wins";
                } else if (list.get(i).equals("OOO") && !list.get(j).equals("XXX")) {
                    return "O wins";
                }
            }
        }

        // check impossible
        int amountX = 0;
        int amountO = 0;
        int amountEmpty = 0;
        for (String s : letters) {
            if (s.equals("O")) {
                amountO++;
            } else if (s.equals("X")) {
                amountX++;
            } else if (s.equals(" ") || s.equals("_")) {
                amountEmpty++;
            }
        }

        if ((amountO - amountX) > 1 || (amountX - amountO) > 1) {
            return "Impossible";
        }
        else if (amountEmpty > 0) {
            return "Game not finished";
        }
        return "Draw";
    }

    public static void main(String[] args) {
        // write your code here
        Scanner input = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String enter = input.nextLine();
        System.out.println("---------\n" +
                "| " + enter.charAt(0) + " " + enter.charAt(1) + " " + enter.charAt(2) + " |\n" +
                "| " + enter.charAt(3) + " " + enter.charAt(4) + " " + enter.charAt(5) + " |\n" +
                "| " + enter.charAt(6) + " " + enter.charAt(7) + " " + enter.charAt(8) + " |\n" +
                "---------");
        System.out.println(checkWinners(enter));
    }
}
