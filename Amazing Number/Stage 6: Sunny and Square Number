package numbers;

import java.util.ArrayList;
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
    
    // print result
    public static void showProperties(long number, boolean checkInput) {
        String isBuzz = "";
        String isDuck = "";
        String isPalindromic = "";
        String isGapful = "";
        String isEven = "";
        String isSpy = "";
        String isSquare = "";
        String isSunny = "";
        // check number to print output
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
            // print option which have 2 or more input
            System.out.println(number + " is " + isBuzz + isDuck + isPalindromic + isGapful + isSpy + isSquare + isSunny + isEven);
        }
        // print option which have only 1 input
        else {
            System.out.println("Properties of " + number);
            System.out.println("        buzz: " + isBuzz(number));
            System.out.println("        duck: " + isDuck(number));
            System.out.println(" palindromic: " + isPalindromic(number));
            System.out.println("      gapful: " + isGapful(number));
            System.out.println("         spy: " + isSpy(number));
            System.out.println("      square: " + isSquare(number));
            System.out.println("       sunny: " + isSunny(number));
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
    
    // find the properties of numbers based on the name of the property (more than 1 name of property
    public static ArrayList<Long> findNameProperty(long startNumber, long times, String nameNum) {
        long checkTimes = 0;
        ArrayList<Long> list = new ArrayList<Long>();
        String[] listProperties = {"even", "odd", "buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny"};
        do {
            // input contains 2 names of properties
            if (nameNum.contains(" ")) {
                String[] twoProperties = nameNum.split(" ");
                String firstInput = twoProperties[0];
                String secondInput = twoProperties[1];
                // show mutually exclusive properties error messages
                if (firstInput.equals("even") && secondInput.equals("odd")
                        || firstInput.equals("odd") && secondInput.equals("even")
                        || firstInput.equals("even") && secondInput.equals("odd")
                        || firstInput.equals("duck") && secondInput.equals("spy")
                        || firstInput.equals("spy") && secondInput.equals("duck")
                        || firstInput.equals("sunny") && secondInput.equals("square")
                        || firstInput.equals("square") && secondInput.equals("sunny")) {
                    System.out.println("The request contains mutually exclusive properties: [" + firstInput.toUpperCase() + ", " + secondInput.toUpperCase() + "]\n" +
                            "There are no numbers with these properties.");
                    break;
                }
                // check palindromic and the rest numbers
                if ((firstInput.equals("palindromic") || secondInput.equals("palindromic"))
                        && isPalindromic(startNumber)) {
                    if ((secondInput.equals("buzz") || firstInput.equals("buzz"))
                            && isBuzz(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("duck") || firstInput.equals("duck"))
                            && isDuck(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("gapful") || firstInput.equals("gapful"))
                            && isGapful(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("spy") || firstInput.equals("spy"))
                            && isSpy(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("square") || firstInput.equals("square"))
                            && isSquare(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("sunny") || firstInput.equals("sunny"))
                            && isSunny(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("even") || firstInput.equals("even"))
                            && isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("odd") || firstInput.equals("odd"))
                            && !isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                }
                // check gapful and the rest numbers (except: palindromic)
                else if ((firstInput.equals("gapfull") || secondInput.equals("gapful"))
                        && isGapful(startNumber)) {
                    if ((secondInput.equals("buzz") || firstInput.equals("buzz"))
                            && isBuzz(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("duck") || firstInput.equals("duck"))
                            && isDuck(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("spy") || firstInput.equals("spy"))
                            && isSpy(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("square") || firstInput.equals("square"))
                            && isSquare(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("sunny") || firstInput.equals("sunny"))
                            && isSunny(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("even") || firstInput.equals("even"))
                            && isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("odd") || firstInput.equals("odd"))
                            && !isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                }
                // check buzz and the rest numbers (except: palindromic, gapful)
                else if ((firstInput.equals("buzz") || secondInput.equals("buzz"))
                        && isBuzz(startNumber)) {
                    if ((secondInput.equals("duck") || firstInput.equals("duck"))
                            && isDuck(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("spy") || firstInput.equals("spy"))
                            && isSpy(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("square") || firstInput.equals("square"))
                            && isSquare(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("sunny") || firstInput.equals("sunny"))
                            && isSunny(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("even") || firstInput.equals("even"))
                            && isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("odd") || firstInput.equals("odd"))
                            && !isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                } 
                // check duck and the rest numbers (except: palindromic, gapful, buzz, spy)
                else if ((firstInput.equals("duck") || secondInput.equals("duck"))
                        && isDuck(startNumber)) {
                    if ((secondInput.equals("square") || firstInput.equals("square"))
                            && isSquare(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("sunny") || firstInput.equals("sunny"))
                            && isSunny(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("even") || firstInput.equals("even"))
                            && isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("odd") || firstInput.equals("odd"))
                            && !isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                }
                // check spy and the rest numbers (except: palindromic, gapful, buzz, duck)
                else if ((firstInput.equals("spy") || secondInput.equals("spy"))
                        && isSpy(startNumber)) {
                    if ((secondInput.equals("square") || firstInput.equals("square"))
                            && isSquare(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("sunny") || firstInput.equals("sunny"))
                            && isSunny(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("even") || firstInput.equals("even"))
                            && isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("odd") || firstInput.equals("odd"))
                            && !isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                }
                // check square and the rest numbers (except: palindromic, gapful, buzz, duck, sunny)
                else if ((firstInput.equals("square") || secondInput.equals("square"))
                        && isSquare(startNumber)) {
                    if ((secondInput.equals("even") || firstInput.equals("even"))
                            && isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("odd") || firstInput.equals("odd"))
                            && !isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                }
                // check sunny and the rest numbers (except: palindromic, gapful, buzz, duck, square)
                else if ((firstInput.equals("sunny") || secondInput.equals("sunny"))
                        && isSunny(startNumber)) {
                    if ((secondInput.equals("even") || firstInput.equals("even"))
                            && isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    } else if ((secondInput.equals("odd") || firstInput.equals("odd"))
                            && !isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                }
            // input contains 1 names of properties     
            } else {
                if (nameNum.equals("buzz")) {
                    if (isBuzz(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                } else if (nameNum.equals("duck")) {
                    if (isDuck(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                } else if (nameNum.equals("palindromic")) {
                    if (isPalindromic(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                } else if (nameNum.equals("gapful")) {
                    if (isGapful(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                } else if (nameNum.equals("spy")) {
                    if (isSpy(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                } else if (nameNum.equals("even")) {
                    if (isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                } else if (nameNum.equals("odd")) {
                    if (!isEvenOdd(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                } else if (nameNum.equals("square")) {
                    if (isSquare(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                } else if (nameNum.equals("sunny")) {
                    if (isSunny(startNumber)) {
                        checkTimes++;
                        list.add(startNumber);
                    }
                }
            }
            startNumber++;
        } while (checkTimes < times);
        return list;
    }
    // check input properties's name
    public static boolean findProperty(String property) {
        String[] listProperties = {"even", "odd", "buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny"};
        for (String s : listProperties) {
            if (property.equals(s)) {
                return true;
            }
        }
        return false;
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
                String thirdNum = "";
                String fourthNum = "";
                if (newNumberString.length == 3) {
                    thirdNum = newNumberString[2];
                    thirdNum = thirdNum.toLowerCase();
                } else if (newNumberString.length == 4) {
                    thirdNum = newNumberString[2];
                    thirdNum = thirdNum.toLowerCase();
                    fourthNum = newNumberString[3];
                    fourthNum = fourthNum.toLowerCase();
                }
                String twoProperties = thirdNum + " " + fourthNum;
                twoInputOrOne = true;

                if (firstNum < 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else if (secondNum < 0) {
                    System.out.println("The second parameter should be a natural number.");
                } else if ((!findProperty(thirdNum) && !thirdNum.isBlank() && newNumberString.length == 3) || (findProperty(fourthNum) && !findProperty(thirdNum) && !thirdNum.isBlank())) {
                    System.out.println("The property [" + thirdNum.toUpperCase() + "] is wrong.\n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
                } else if (!findProperty(fourthNum) && !fourthNum.isBlank() && findProperty(thirdNum)) {
                    System.out.println("The property [" + fourthNum.toUpperCase() + "] is wrong.\n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
                } else if (!findProperty(fourthNum) && !fourthNum.isBlank() && !findProperty(thirdNum)) {

                        System.out.println("The properties [" + thirdNum.toUpperCase() + ", " + fourthNum.toUpperCase() + "] are wrong.\n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
                } else {
                    // show 2 inputs
                    if (thirdNum.equals("")) {
                        for (int i = 0; i < secondNum; i++) {
                            showProperties((firstNum + i), twoInputOrOne);
                        }
                    } 
                    // show 4 inputs
                    else if (!fourthNum.isBlank()) {
                        for (long l : findNameProperty(firstNum, secondNum, twoProperties)) {
                            showProperties(l, twoInputOrOne);
                        }
                    } 
                    // show 3 inputs
                    else {
                        for (long l : findNameProperty(firstNum, secondNum, thirdNum)) {
                            showProperties(l, twoInputOrOne);
                        }
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
