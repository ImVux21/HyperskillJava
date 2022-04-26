package readability;

import java.util.Scanner;

public class Main {
    private static double calculateAverage(String[] sentences) {
        int totalOfWords = 0;
        for (int i = 0; i < sentences.length; i++) {
            int count = 0;
            Scanner scanner = new Scanner(sentences[i]);
            while (scanner.hasNext()) {
                scanner.next();
                count++;
            }
            totalOfWords += count;
        }
        return (double) totalOfWords/sentences.length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String[] sentences = text.split("[^\\w\\s,]");// everything except for alphanumeric, whitespaces, and "," charcter

        double average = calculateAverage(sentences);
        if (average > 10) {
            System.out.println("HARD");
        } else {
            System.out.println("EASY");
        }
    }
}
