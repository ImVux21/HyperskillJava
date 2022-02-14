package bullscows;

import java.util.Random;
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

        // add "s"
        String sBull = "";
        String sCow = "";
        if (bulls > 1) {
            sBull = "s";
        }
        if (cows > 1) {
            sCow = "s";
        }

        if (bulls == 0 && cows > 0) {
            return String.format("Grade: %d cow%s", cows, sCow);
        } else if (bulls > 0 && cows == 0) {
            return String.format("Grade: %d bull%s", bulls, sBull);
        } else if (bulls > 0 && cows > 0) {
            return String.format("Grade: %d bull%s and %d cow%s", bulls, sBull, cows, sCow);
        }
        return String.format("Grade: None. The secret code is %s.", secretNum);
    }

    public static StringBuilder createSecretCode(int digitsLength) {
        Random rand = new Random();
        StringBuilder secretNum = new StringBuilder();

        if (digitsLength <= 10) {
            while (secretNum.length() < digitsLength) {
                int number = rand.nextInt(10);
                if (secretNum.length() == 0 && number != 0) {
                    secretNum.append(number);
                }
                if (secretNum.length() > 0) {
                    if (!secretNum.toString().contains(String.valueOf(number))) {
                        secretNum.append(number);
                    }
                }
            }
        }
        else {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", digitsLength);
        }
        return secretNum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 1;

        System.out.println("Please, enter the secret code's length:");
        int lengthOfDigits = scanner.nextInt();
        scanner.nextLine();
        String secretCode = createSecretCode(lengthOfDigits).toString();

        System.out.println("Okay, let's start a game!");
        while (true) {
            System.out.println("Turn " + count++);
            String guessNum = scanner.nextLine();
            System.out.println(grade(guessNum, secretCode));
            if (secretCode.equals(guessNum)) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
        }
    }
}
