package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean checkFirstDigit = false;
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        StringBuilder secretNum = new StringBuilder();
        int digits = scanner.nextInt();

        if (digits <= 10) {
            while (secretNum.length() < digits) {
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
            System.out.printf("The random secret number is %s.", secretNum);
        }
        else {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", digits);
        }
    }
}
