package converter;

import java.util.Scanner;
import java.math.BigInteger;

public class Main {
    public static String convertFromDecimal(String valueString, String base) {
        BigInteger[] remainder;
        String[] listBase = base.split(" ");
        int sourceBase = Integer.parseInt(listBase[0]);
        int targetBase = Integer.parseInt(listBase[1]);
        BigInteger value = convertToDecimal(valueString, sourceBase);

        String result = "";
        while (value.compareTo(BigInteger.ZERO) > 0) {
            if (targetBase >= 10 && targetBase <= 36) {
                char[] hexchars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
                remainder = value.divideAndRemainder(BigInteger.valueOf(targetBase));
                result = hexchars[remainder[1].intValue()] + result;
                value = remainder[0];
            } else if (targetBase <= 9) {
                remainder = value.divideAndRemainder(BigInteger.valueOf(targetBase));
                result += remainder[1];
                value = remainder[0];
            }
        }
        // reverse of octal, binary numbers
        if (targetBase <= 9) {
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

    public static BigInteger convertToDecimal(String value, int base) {
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        value = value.toUpperCase();
        String valueString = "";
        BigInteger newValue = BigInteger.ZERO;
        // reverse number
        if (base < 10) {
            for (int i = value.length() - 1; i >= 0; i--) {
                valueString += value.charAt(i);
            }
        }
        for (int i = 0; i < value.length(); i++) {
            if (base >= 10) {
                char c = value.charAt(i);
                BigInteger d = BigInteger.valueOf(digits.indexOf(c));
                newValue = (newValue.multiply(BigInteger.valueOf(base))).add(d);
            } else {
                int d = Integer.parseInt(String.valueOf(valueString.charAt(i)));
                BigInteger newD = BigInteger.valueOf(d);
                newValue = newValue.add(newD.multiply(BigInteger.valueOf((long) Math.pow(base, i))));
            }
        }
        return newValue;
    }

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        boolean isBack = true;
        String[] list = null;
        String source = null;
        String str = null;
        int target = 0;
        do {
            if (isBack) {
                System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
                str = scanner.nextLine();
                list = str.split(" ");
            }
            if (str.equals("/exit")) {
                break;
            }
            if (list.length == 2) {
                source = list[0];
                target = Integer.parseInt(list[1]);
            }
            System.out.print("Enter number in base " + source + " to convert to base " + target + " (To go back type /back) ");
            String value = scanner.nextLine();
            if (value.equals("/back")) {
                isBack = true;
            } else {
                isBack = false;
                System.out.println("Conversion result: " + convertFromDecimal(value, str));
            }

        } while (true);
    }
}
