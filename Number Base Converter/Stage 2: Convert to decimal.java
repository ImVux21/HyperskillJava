package converter;

import java.util.Scanner;

public class Main {
    public static String convertFromDecimal(String valueString, int base) {
        int remainder;
        int value = Integer.parseInt(valueString);
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
        if (result.equals("")) {
            result = "0";
        }
        return result;
    }

    public static int convertToDecimal(String value, int base) {
        String digits = "0123456789ABCDEF";
        value = value.toUpperCase();
        String valueString = "";
        int newValue = 0;
        // reverse number
        if (base < 16) {
            for (int i = value.length() - 1; i >= 0; i--) {
                valueString += value.charAt(i);
            }
        }
        for (int i = 0; i < value.length(); i++) {
            if (base == 16) {
                char c = value.charAt(i);
                int d = digits.indexOf(c);
                newValue = 16 * newValue + d;
            } else if (base == 8) {
                int d = Integer.parseInt(String.valueOf(valueString.charAt(i)));
                newValue += (d * Math.pow(8, i));
            } else if (base == 2) {
                int d = Integer.parseInt(String.valueOf(valueString.charAt(i)));
                newValue += (d * Math.pow(2, i));
            }
        }
        return newValue;
    }

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Do you want to convert /from decimal or /to decimal? (To quit type /exit)");
            String str = scanner.next();
            scanner.nextLine();
            if (str.equals("/from")) {
                System.out.print("Enter number in decimal system: ");
                String decimal = scanner.nextLine();
                System.out.print("Enter target base: ");
                int base = scanner.nextInt();
                System.out.println("Conversion result: " + convertFromDecimal(decimal, base));
            } else if (str.equals("/to")) {
                System.out.print("Enter source number: ");
                String sourceNumber = scanner.nextLine();
                System.out.print("Enter source base: ");
                int base = scanner.nextInt();
                System.out.println("Conversion to decimal result: " + convertToDecimal(sourceNumber, base));
            } else if (str.equals("/exit")) {
                break;
            }
        }
    }
}
