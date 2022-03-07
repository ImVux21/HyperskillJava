package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = 0;
        int timesAppear = 0;
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

        System.out.println("Total numbers: " + total);
        System.out.printf("The greatest number: %d (%d time(s))", max, timesAppear);
    }
}
