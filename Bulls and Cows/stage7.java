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
        return "Grade: None";
    }

    public static StringBuilder createSecretCode(int digitsLength, int possibleSymbols) {
        Random rand = new Random();
        StringBuilder secretNum = new StringBuilder(digitsLength);
        String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
        String subChars = chars.substring(0, possibleSymbols);

        String codeHider = "";
        for (int i = 0; i < digitsLength; i++) {
            codeHider += "*"; // create hidden code : **** to print
        }

        if (possibleSymbols < 11) {
            System.out.printf("The secret is prepared: %s (%s-%s).\n", codeHider, subChars.charAt(0), subChars.charAt(possibleSymbols - 1));
        } else if (possibleSymbols > 10) {
            System.out.printf("The secret is prepared: %s (0-9, %s-%s).\n", codeHider, subChars.charAt(10), subChars.charAt(possibleSymbols - 1));
        }

        if (digitsLength <= subChars.length()) {
            while (secretNum.length() < digitsLength) {
                int number = rand.nextInt(subChars.length());
                if (!secretNum.toString().contains(String.valueOf(subChars.charAt(number)))) {
                    secretNum.append(subChars.charAt(number));
                }
            }
        }
        return secretNum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 1;

        String possibleSymbolsString = null;
        String lengthOfDigitsString = null;
        int lengthOfDigits = 0;
        int possibleSymbols = 0;
        boolean isChecked = true; // occurs error then stop program


        try {
            System.out.println("Input the length of the secret code:");
            lengthOfDigitsString = scanner.nextLine();
            lengthOfDigits = Integer.parseInt(lengthOfDigitsString);
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.\n", lengthOfDigitsString);
            isChecked = false;
        }
        if (isChecked) {
            try {
                System.out.println("Input the number of possible symbols in the code:");
                possibleSymbolsString = scanner.nextLine();
                possibleSymbols = Integer.parseInt(possibleSymbolsString);
            } catch (NumberFormatException e) {
                System.out.printf("Error: \"%s\" isn't a valid number.\n", possibleSymbolsString);
                isChecked = false;
            }
        }

        if (isChecked) {

            if (lengthOfDigits > possibleSymbols) {
                System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", lengthOfDigits, possibleSymbols);
            } else if (possibleSymbols > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            } else if (lengthOfDigits < 1) {
                System.out.println("Error");
            } else {
                String secretCode = createSecretCode(lengthOfDigits, possibleSymbols).toString();

                System.out.println("Okay, let's start a game!");
                while (true) {
                    System.out.println("Turn " + (count++) + ":");
                    String guessNum = scanner.nextLine();
                    System.out.println(grade(guessNum, secretCode));
                    if (secretCode.equals(guessNum)) {
                        System.out.println("Congratulations! You guessed the secret code.");
                        break;
                    }
                }
            }
        }
    }
}
