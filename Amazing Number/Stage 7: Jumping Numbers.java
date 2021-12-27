package numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    // even, odd
    public static boolean isEvenOdd(long number) {
        if (number % 2 == 0) {
            return true;
        }
        return false;
    }

    // buzz  not
    public static boolean isBuzz(long number) {
        if (number % 7 == 0 || number % 10 == 7) {
            return true;
        }
        return false;
    }

    //// duck or not
    public static boolean isDuck(long number) {
        String numberString = Long.toString(number);
        if (numberString.startsWith("0")) {
            for (int i = 1; i < numberString.length(); i++) {
                if (numberString.charAt(i) == '0') {
                    return true;
                }
            }
        } else {
            for (char x : numberString.toCharArray()) {
                if (x == '0') {
                    return true;
                }

            }
        }
        return false;
    }

    // palindromic or not
    public static boolean isPalindromic(long number) {
        String numberStringReversed = "";
        String numberString = Long.toString(number);
        for (int i = numberString.length() - 1; i >= 0; i--) {
            numberStringReversed += numberString.charAt(i);
        }
        if (numberStringReversed.equals(numberString)) {
            return true;
        }
        return false;
    }

    // gapful or not
    public static boolean isGapful(long number) {
        String numberString = Long.toString(number);
        if (number >= 100) {
            char firstNum = numberString.charAt(0);
            char lastNum = numberString.charAt(numberString.length() - 1);
            String newStringNum = String.valueOf(firstNum) + String.valueOf(lastNum);
            Long newNum = Long.parseLong(newStringNum);
            if (number % newNum == 0) {
                return true;
            }
        }
        return false;
    }

    //  spy or not
    public static boolean isSpy(long number) {
        String numberString = Long.toString(number);
        long sumAllDigit = 0;
        long productAllDigit = 1;
        for (int i = 0; i < numberString.length(); i++) {
            long lastDigit = number % 10;
            number /= 10;
            sumAllDigit += lastDigit;
            productAllDigit *= lastDigit;
        }
        if (sumAllDigit == productAllDigit) {
            return true;
        }
        return false;
    }

    // square or not
    public static boolean isSquare(long number) {
        long newNumber = (long) Math.sqrt(number);
        if (Math.pow(newNumber, 2) == number) {
            return true;
        }
        return false;
    }

    // sunny or not
    public static boolean isSunny(long number) {
        if (isSquare(number + 1)) {
            return true;
        }
        return false;
    }

    // jumping or not
    public static boolean isJumping (long number) {

        String numberString = Long.toString(number);
        int counter = 0;
        for (int i = 0; i < numberString.length() - 1; i ++){
            long num1 = Character.getNumericValue(numberString.charAt(i));
            long num2 = Character.getNumericValue(numberString.charAt(i+1));

            if (num1 + 1 == num2 || num1 - 1 == num2){
                counter ++;
            }
        }
        if (counter == numberString.length() - 1){
            return true;
        }

        return false;
    }

    public static void showProperties(long number, boolean checkInput) {
        String isBuzz = "";
        String isDuck = "";
        String isPalindromic = "";
        String isGapful = "";
        String isEven = "";
        String isSpy = "";
        String isSquare = "";
        String isSunny = "";
        String isJumping = "";

        // print result
        if (checkInput) {
            if (isBuzz(number)) {
                isBuzz = "buzz, ";
            }
            if (isDuck(number)) {
                isDuck = "duck, ";
            }
            if (isPalindromic(number)) {
                isPalindromic = "palindromic, ";
            }
            if (isGapful(number)) {
                isGapful = "gapful, ";
            }
            if (isSpy(number)) {
                isSpy = "spy, ";
            }
            if (isEvenOdd(number)) {
                isEven = "even";
            }
            if (!isEvenOdd(number)) {
                isEven = "odd";
            }
            if (isSquare(number)) {
                isSquare = "square, ";
            }
            if (isSunny(number)) {
                isSunny = "sunny, ";
            }
            if (isJumping(number)) {
                isJumping = "jumping, ";
            }

            System.out.println(number + " is " + isBuzz + isDuck + isPalindromic + isGapful + isSpy + isSquare + isSunny + isJumping + isEven);
        } else {
            System.out.println("Properties of " + number);
            System.out.println("        buzz: " + isBuzz(number));
            System.out.println("        duck: " + isDuck(number));
            System.out.println(" palindromic: " + isPalindromic(number));
            System.out.println("      gapful: " + isGapful(number));
            System.out.println("         spy: " + isSpy(number));
            System.out.println("      square: " + isSquare(number));
            System.out.println("       sunny: " + isSunny(number));
            System.out.println("     jumping: " + isJumping(number));
            System.out.println("        even: " + isEvenOdd(number));
            System.out.println("         odd: " + !isEvenOdd(number));
        }
    }

    public static void request() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties; \n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- two natural numbers and two properties to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    // check input and find given properties
    public static boolean findNameProperty(long number, String name) {
        boolean checkAll = true;
        String[] listName = name.split(" ");
        boolean[] checkBoolean = new boolean[listName.length];

        for (int i = 0; i < listName.length; i++) {
            checkBoolean[i] = false;
        }

        for (int i = 0; i < checkBoolean.length; i++) {
            if (listName[i].equals("even") && isEvenOdd(number)) {
                checkBoolean[i] = true;
            } else if (listName[i].equals("odd") && !isEvenOdd(number)) {
                checkBoolean[i] = true;
            } else if (listName[i].equals("buzz") && isBuzz(number)) {
                checkBoolean[i] = true;
            } else if (listName[i].equals("duck") && isDuck(number)) {
                checkBoolean[i] = true;
            } else if (listName[i].equals("palindromic") && isPalindromic(number)) {
                checkBoolean[i] = true;
            } else if (listName[i].equals("gapful") && isGapful(number)) {
                checkBoolean[i] = true;
            } else if (listName[i].equals("square") && isSquare(number)) {
                checkBoolean[i] = true;
            } else if (listName[i].equals("spy") && isSpy(number)) {
                checkBoolean[i] = true;
            } else if (listName[i].equals("sunny") && isSunny(number)) {
                checkBoolean[i] = true;
            } else if (listName[i].equals("jumping") && isJumping(number)) {
                checkBoolean[i] = true;
            }
        }
        for (boolean b : checkBoolean) {
            if (b == false) {
                checkAll = false;
            }
        }
        return checkAll;
    }


    public static List<Long> findAmazingNumbers(long firstNumber, long times, String name) {
        long checkTimes = 0;
        List<Long> result = new ArrayList<Long>();
        do {
            if (name.contains("even") && name.contains("odd")) {
                System.out.println("The request contains mutually exclusive properties: [EVEN, ODD]\n" +
                        "There are no numbers with these properties.");
                break;
            } else if (name.contains("duck") && name.contains("spy")) {
                System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]\n" +
                        "There are no numbers with these properties.");
                break;
            } else if (name.contains("square") && name.contains("sunny")) {
                System.out.println("The request contains mutually exclusive properties: [SQUARE, SUNNY]\n" +
                        "There are no numbers with these properties.");
                break;
            }
            if (findNameProperty(firstNumber, name)) {
                result.add(firstNumber);
                checkTimes++;
            }
            firstNumber++;
        } while (checkTimes < times);
        return result;
    }

    public static boolean findProperty(String property) {
        String[] listProperties = {"even", "odd", "buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny", "jumping"};
        for (String s : listProperties) {
            if (property.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> checkName(String[] name) {
        List<String> nameOfProperty = new ArrayList<String>();
        for (String s : name) {
            if (!findProperty(s.toLowerCase())) {
                nameOfProperty.add(s);
            }
        }
        return nameOfProperty;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!");
        request();

        while (true) {
            System.out.println("");
            System.out.print("Enter a request: ");
            boolean twoInputOrOne = false;

            String numberString = input.nextLine();
            // user choose 2 or 3  inputs
            if (numberString.contains(" ")) {
                String[] newNumberString = numberString.split(" ");
                long firstNum = Long.parseLong(newNumberString[0]);
                long secondNum = Long.parseLong(newNumberString[1]);

                String name = "";
                String[] newListName = new String[newNumberString.length - 2];
                for (int i = newNumberString.length - 1; i > 1; i--) {
                    name += newNumberString[i];
                    name += " ";
                    newListName[i - 2] = newNumberString[i];
                }
                // remove space at last position
                name = name.trim().toLowerCase(Locale.ROOT);

                twoInputOrOne = true;

                if (firstNum < 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else if (secondNum < 0) {
                    System.out.println("The second parameter should be a natural number.");
                } else if (newNumberString.length == 2) {
                    for (int i = 0; i < secondNum; i++) {
                        showProperties((firstNum + i), twoInputOrOne);
                    }
                }
                // have a wrong name
                else if (checkName(newListName).size() == 1) {
                    System.out.println("The property [" + checkName(newListName).get(0).toUpperCase() + "] is wrong.\n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
                }
                // having morw than a wrong name
                else if (checkName(newListName).size() > 1) {
                    String wrongNames = "";
                    for (int i = 0; i < checkName(newListName).size(); i++) {
                        wrongNames += checkName(newListName).get(i) + ", ";
                    }
                    // remove ", " at last position
                    wrongNames = wrongNames.substring(0, wrongNames.length() - 2);
                    System.out.println("The properties [" + wrongNames.toUpperCase() + "] are wrong.\n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
                } else {
                    for (long l : findAmazingNumbers(firstNum, secondNum, name)) {
                        showProperties(l, twoInputOrOne);
                    }
                }
            }
            // users choose 1 input
            else {
                long number = Long.parseLong(numberString);
                if (number > 0) {
                    showProperties(number, twoInputOrOne);
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
