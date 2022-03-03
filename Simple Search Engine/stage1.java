package search;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();
        String[] singleWords = firstLine.split(" ");
        boolean isChecked = true;

        for (int i = 0; i < singleWords.length; i++) {
            if (secondLine.equals(singleWords[i])) {
                System.out.println(i + 1);
                isChecked = false;
                break;
            }
        }

        if (isChecked) {
            System.out.println("Not Found");
        }
    }
}
