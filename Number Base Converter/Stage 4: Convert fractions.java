package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static String convertFromDecimal(String valueString, String base) {
        BigDecimal[] remainder;
        char[] hexchars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String[] listBase = base.split(" ");
        int sourceBase = Integer.parseInt(listBase[0]);
        int targetBase = Integer.parseInt(listBase[1]);
        BigDecimal value = convertToDecimal(valueString, sourceBase);
        String result = "";
        String resultFraction = "";
        // fractional parts of BigDecimal
        BigDecimal fractionalPart = value.remainder(BigDecimal.ONE);
        // integer parts of BigDecimal
        value = new BigDecimal(value.toBigInteger());

        // convert fractional part of decimal to the other base
        do {
            BigInteger intFractionalPart = fractionalPart.toBigInteger();
            if (fractionalPart.compareTo(BigDecimal.ONE) > 0) {
                fractionalPart = fractionalPart.subtract(new BigDecimal(intFractionalPart));
            }
            fractionalPart = fractionalPart.multiply(BigDecimal.valueOf(targetBase));
            intFractionalPart = fractionalPart.toBigInteger();
            resultFraction += hexchars[intFractionalPart.intValue()];
        } while (resultFraction.length() < 5);

        // convert integer part of decimal to the other base
        while (value.compareTo(BigDecimal.ZERO) > 0) {
            remainder = value.divideAndRemainder(BigDecimal.valueOf(targetBase));
            result = hexchars[remainder[1].intValue()] + result;
            value = remainder[0];
        }

        if (result.equals("")) {
            result = "0";
        }
        if (!valueString.contains(".")) {
            return result.toLowerCase(Locale.ROOT);
        }
        return result.toLowerCase(Locale.ROOT) + "." + resultFraction.toLowerCase();
    }

    public static BigDecimal convertToDecimal(String value, int base) {
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        value = value.toUpperCase();
        BigDecimal newValue = BigDecimal.ZERO;
        BigDecimal newFraction = BigDecimal.ZERO;

        // convert fractional parts of the other base to decimal
        if (value.contains(".")) {
            String[] parts = value.split("\\.");
            value = parts[0];
            String fractions = parts[1];
            for (int i = 1; i <= fractions.length(); i++) {
                char c = fractions.charAt(i-1);
                BigDecimal d = BigDecimal.valueOf(digits.indexOf(c));
                newFraction = newFraction.add(d.multiply(BigDecimal.valueOf(Math.pow(base, - i))));
            }
        }

        // convert integer parts of the other base to decimal
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            BigDecimal d = BigDecimal.valueOf(digits.indexOf(c));
            newValue = (newValue.multiply(BigDecimal.valueOf(base))).add(d);
        }
        return newValue.add(newFraction).setScale(5, RoundingMode.HALF_UP);
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
