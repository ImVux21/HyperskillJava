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
        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = false;
        boolean check4 = false;
        boolean check5 = false;
        String firstName = "";
        String secondName = "";
        String thirdName = "";
        String fourthName = "";
        String fifthName = "";

        boolean checkAll = false;
        String[] listName = name.split(" ");
        if (listName.length == 1) {
            firstName = listName[0];
        } else if (listName.length == 2) {
            firstName = listName[0];
            secondName = listName[1];
        } else if (listName.length == 3) {
            firstName = listName[0];
            secondName = listName[1];
            thirdName = listName[2];
        } else if (listName.length == 4) {
            firstName = listName[0];
            secondName = listName[1];
            thirdName = listName[2];
            fourthName = listName[3];
        } else if (listName.length == 5) {
            firstName = listName[0];
            secondName = listName[1];
            thirdName = listName[2];
            fourthName = listName[3];
            fifthName = listName[4];
        }

        if (firstName.equals("even") && isEvenOdd(number)) {
            check1 = true;
        } else if (firstName.equals("odd") && !isEvenOdd(number)) {
            check1 = true;
        } else if (firstName.equals("buzz") && isBuzz(number)) {
            check1 = true;
        } else if (firstName.equals("duck") && isDuck(number)) {
            check1 = true;
        } else if (firstName.equals("palindromic") && isPalindromic(number)) {
            check1 = true;
        } else if (firstName.equals("gapful") && isGapful(number)) {
            check1 = true;
        } else if (firstName.equals("square") && isSquare(number)) {
            check1 = true;
        } else if (firstName.equals("spy") && isSpy(number)) {
            check1 = true;
        } else if (firstName.equals("sunny") && isSunny(number)) {
            check1 = true;
        } else if (firstName.equals("jumping") && isJumping(number)) {
            check1 = true;
        }
        if (secondName.equals("even") && isEvenOdd(number)) {
            check2 = true;
        } else if (secondName.equals("odd") && !isEvenOdd(number)) {
            check2 = true;
        } else if (secondName.equals("buzz") && isBuzz(number)) {
            check2 = true;
        } else if (secondName.equals("duck") && isDuck(number)) {
            check2 = true;
        } else if (secondName.equals("palindromic") && isPalindromic(number)) {
            check2 = true;
        } else if (secondName.equals("gapful") && isGapful(number)) {
            check2 = true;
        } else if (secondName.equals("square") && isSquare(number)) {
            check2 = true;
        } else if (secondName.equals("spy") && isSpy(number)) {
            check2 = true;
        } else if (secondName.equals("sunny") && isSunny(number)) {
            check2 = true;
        } else if (secondName.equals("jumping") && isJumping(number)) {
            check2 = true;
        }

        if (thirdName.equals("even") && isEvenOdd(number)) {
            check3 = true;
        } else if (thirdName.equals("odd") && !isEvenOdd(number)) {
            check3 = true;
        } else if (thirdName.equals("buzz") && isBuzz(number)) {
            check3 = true;
        } else if (thirdName.equals("duck") && isDuck(number)) {
            check3 = true;
        } else if (thirdName.equals("palindromic") && isPalindromic(number)) {
            check3 = true;
        } else if (thirdName.equals("gapful") && isGapful(number)) {
            check3 = true;
        } else if (thirdName.equals("square") && isSquare(number)) {
            check3 = true;
        } else if (thirdName.equals("spy") && isSpy(number)) {
            check3 = true;
        } else if (thirdName.equals("sunny") && isSunny(number)) {
            check3 = true;
        } else if (thirdName.equals("jumping") && isJumping(number)) {
            check3 = true;
        }

        if (fourthName.equals("even") && isEvenOdd(number)) {
            check4 = true;
        } else if (fourthName.equals("odd") && !isEvenOdd(number)) {
            check4 = true;
        } else if (fourthName.equals("buzz") && isBuzz(number)) {
            check4 = true;
        } else if (fourthName.equals("duck") && isDuck(number)) {
            check4 = true;
        } else if (fourthName.equals("palindromic") && isPalindromic(number)) {
            check4 = true;
        } else if (fourthName.equals("gapful") && isGapful(number)) {
            check4 = true;
        } else if (fourthName.equals("square") && isSquare(number)) {
            check4 = true;
        } else if (fourthName.equals("spy") && isSpy(number)) {
            check4 = true;
        } else if (fourthName.equals("sunny") && isSunny(number)) {
            check4 = true;
        } else if (fourthName.equals("jumping") && isJumping(number)) {
            check4 = true;
        }
        if (fifthName.equals("even") && isEvenOdd(number)) {
            check5 = true;
        } else if (fifthName.equals("odd") && !isEvenOdd(number)) {
            check5 = true;
        } else if (fifthName.equals("buzz") && isBuzz(number)) {
            check5 = true;
        } else if (fifthName.equals("duck") && isDuck(number)) {
            check5 = true;
        } else if (fifthName.equals("palindromic") && isPalindromic(number)) {
            check5 = true;
        } else if (fifthName.equals("gapful") && isGapful(number)) {
            check5 = true;
        } else if (fifthName.equals("square") && isSquare(number)) {
            check5 = true;
        } else if (fifthName.equals("spy") && isSpy(number)) {
            check5 = true;
        } else if (fifthName.equals("sunny") && isSunny(number)) {
            check5 = true;
        } else if (fifthName.equals("jumping") && isJumping(number)) {
            check5 = true;
        }
        if (check1 && listName.length == 1) {
            checkAll = true;
        } else if (check1 && check2 && listName.length == 2) {
            checkAll = true;
        } else if (check1 && check2 && check3 && listName.length == 3) {
            checkAll = true;
        } else if (check1 && check2 && check3 && check4 && listName.length == 4) {
            checkAll = true;
        } else if (check1 && check2 && check3 && check4 && check5 && listName.length == 5) {
            checkAll = true;
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

    // find and return wrong names of properties
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
                else if (checkName(newListName).size() == 1) {
                    System.out.println("The property [" + checkName(newListName).get(0).toUpperCase() + "] is wrong.\n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
                } else if (checkName(newListName).size() > 1) {
                    String wrongNames = "";
                    for (int i = 0; i < checkName(newListName).size(); i++) {
                        wrongNames += checkName(newListName).get(i) + ", ";
                    }
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
