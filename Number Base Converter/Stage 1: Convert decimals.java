package converter;

import java.util.Scanner;

public class Main {
    public static String convertDecimal(int value, int base) {
        int remainder;
        String result = "";
        while (value > 0) {
            if (base == 16) {
                char[] hexchars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                remainder = value % base;
                result = hexchars[remainder] + result;
                value /= base;
            } else if (base == 8) {
                remainder = value % base;
                result += remainder;
                value /= base;
            } else if (base == 2) {
                remainder = value % base;
                result += remainder;
                value /= base;
            }
        }
        // reverse of octal, binary numbers
        if (base < 16) {
            String[] letter = result.split("");
            result = "";
            for (int i = letter.length - 1; i >= 0; i--) {
                result += letter[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number in decimal system: ");
        int decimal = scanner.nextInt();
        System.out.print("Enter target base: ");
        int base = scanner.nextInt();
        System.out.println("Conversion result: " + convertDecimal(decimal, base));
    }
}
