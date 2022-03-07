package sorting;

import java.util.*;

public class Main {
    public static void typeLong(Scanner scanner) {
        long total = 0;
        long timesAppear = 0;
        long max = 0;

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            // write your code here
            if (number > max) {
                max = number;
                timesAppear = 0;
            }
            if (number == max) {
                timesAppear++;
            }
            total++;
        }

        long average = timesAppear / total * 100;

        System.out.println("Total numbers: " + total);
        System.out.printf("The greatest number: %d (%d time(s), %d%%)", max, timesAppear, average);
    }

    public static void typeLine(Scanner scanner) {
        long total = 0;
        long timesAppear = 0;
        String longest = "";

        while (scanner.hasNextLine()) {
            String number = scanner.nextLine();
            // write your code here
            if (number.length() > longest.length()) {
                longest = number;
                timesAppear = 0;
            }
            if (number.equals(longest)) {
                timesAppear++;
            }
            total++;
        }

        int average = (int) (((double) timesAppear / total) * 100);

        System.out.println("Total lines: " + total);
        System.out.printf("The longest line:\n%s\n(%d time(s), %d%%)", longest, timesAppear, average);
    }

    public static void typeWord(Scanner scanner) {
        long total = 0;
        long timesAppear = 0;
        String longest = "";

        while (scanner.hasNext()) {
            String number = scanner.next();
            // write your code here
            if (number.length() > longest.length()) {
                longest = number;
                timesAppear = 0;
            }
            if (number.equals(longest)) {
                timesAppear++;
            }
            total++;
        }

        int average = (int) (((double) timesAppear / total) * 100);

        System.out.println("Total words: " + total);
        System.out.printf("The longest word: %s (%d time(s), %d%%)", longest, timesAppear, average);
    }

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (args[1].equals("long")) {
            typeLong(scanner);
        } else if (args[1].equals("line")) {
            typeLine(scanner);
        } else {
            typeWord(scanner);
        }

    }
}
