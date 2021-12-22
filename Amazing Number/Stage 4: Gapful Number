
import java.util.Scanner;

public class Main {

    public static void numbersProperties(long number, boolean twoInputOrOne ) {
        String isBuzz = "";
        String isDuck = "";
        String isPalindromic = "";
        String isGapful = "";
        String isEven = "";


        boolean even = false;
        boolean buzz = false;
        boolean duck = false;
        boolean palindromic = false;
        boolean gapful = false;

        String numberStringReversed = "";
        String numberString = Long.toString(number);

        // even, odd
        if (number % 2 == 0) {
            even = true;
        }


        // buzz  not
        if (number % 7 == 0 || number % 10 == 7) {
            buzz = true;
        }


        // duck or not
        if (numberString.startsWith("0")) {
            for (int i = 1; i < numberString.length(); i++) {
                if (numberString.charAt(i) == '0') {
                    duck = true;
                }
            }
        } else {
            for (char x : numberString.toCharArray()) {
                if (x == '0') {
                    duck = true;
                }

            }
        }

        // palindromic or not
        for (int i = numberString.length() - 1; i >= 0; i--) {
            numberStringReversed += numberString.charAt(i);
        }
        if (numberStringReversed.equals(numberString)) {
            palindromic = true;
        }

        // gapful or not
        if (number >= 100) {
            char firstNum = numberString.charAt(0);
            char lastNum = numberString.charAt(numberString.length() - 1);
            String newStringNum = String.valueOf(firstNum) + String.valueOf(lastNum);
            Long newNum = Long.parseLong(newStringNum);
            if (number % newNum == 0) {
                gapful = true;
            }
        }

        if (twoInputOrOne) {
            // print result
            if (buzz) {
                isBuzz = "buzz, ";
            }
            if (duck) {
                isDuck = "duck, ";
            }
            if (palindromic) {
                isPalindromic = "palindromic, ";
            }
            if (gapful) {
                isGapful = "gapful, ";
            }
            if (even) {
                isEven = "even";
            }
            if (!even) {
                isEven = "odd";
            }

            System.out.println(number + " is " + isBuzz + isDuck + isPalindromic + isGapful + isEven);
        } else {
            System.out.println("Properties of " + number);
            System.out.println("        buzz: " + buzz);
            System.out.println("        duck: " + duck);
            System.out.println(" palindromic: " + palindromic);
            System.out.println("      gapful: " + gapful);
            System.out.println("        even: " + even);
            System.out.println("         odd: " + !even);
        }
    }

    public static void request() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!");
        request();

        while (true) {
            System.out.println("");
            System.out.print("Enter a request: ");
            boolean twoInputOrOne = false;
            boolean isChecked = false;

            String numberString = input.nextLine();
            if (numberString.contains(" ")) {
                String[] newNumberString = numberString.split(" ");
                long firstNum = Long.parseLong(newNumberString[0]);
                int secondNum = Integer.parseInt(newNumberString[1]);
                twoInputOrOne = true;

                if (firstNum < 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else if (secondNum < 0) {
                    System.out.println("The second parameter should be a natural number.");
                }

                for (int i = 0; i < secondNum; i++) {
                    numbersProperties((firstNum + i), twoInputOrOne);
                }
            } else {
                long number = Long.parseLong(numberString);
                if (number > 0) {
                    numbersProperties(number, twoInputOrOne);
                } else if (number == 0) {
                    System.out.println("");
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
        }
    }
}
