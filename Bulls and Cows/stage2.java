package bullscows;

import java.util.Scanner;

public class Main {
    public static String grade(String guessNum, String secretNum) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < guessNum.length(); i++) {
            if (guessNum.charAt(i) == secretNum.charAt(i)) {
                bulls++;
            } else if (guessNum.contains(String.valueOf(secretNum.charAt(i)))) {
                cows++;
            }
        }

        if (bulls == 0 && cows > 0) {
            return String.format("Grade: %d cow(s). The secret code is %s.", cows, secretNum);
        } else if (bulls > 0 && cows == 0) {
            return String.format("Grade: %d bull(s). The secret code is %s.", bulls, secretNum);
        } else if (bulls > 0 && cows > 0) {
            return String.format("Grade: %d bull(s) and %d cow(s). The secret code is %s.", bulls, cows, secretNum);
        }
        return String.format("Grade: None. The secret code is %s.", secretNum);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String secretNum = "6578";
        String guessNum = scanner.nextLine();
        System.out.println(grade(guessNum, secretNum));
    }
}
