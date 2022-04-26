package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static double calculateScore(String[] sentences, String originalString) {
        String[] totalOfWords = originalString.split(" ");
        String characters = originalString.replaceAll("\\s", ""); //replace all spaces in originalString

        System.out.printf("\nWords: %d\n" +
                "Sentences: %d\n" +
                "Characters: %d\n", totalOfWords.length, sentences.length, characters.length());

        return (4.71*((double) characters.length()/totalOfWords.length))+(0.5*((double) totalOfWords.length/sentences.length))-21.43;
    }

    public static void main(String[] args) {
        File f = new File(args[0]);
        try(Scanner scanner = new Scanner(f)) {
            String text = scanner.nextLine();
            String[] sentences = text.split("[?.!]");

            double score = Math.round(calculateScore(sentences, text) * 100.0) / 100.0;
            System.out.println("The text is:\n" + text);
            int roundScore = (int) (Math.round(score * 1.0) / 1.0);
            String people =
            roundScore == 1? "5-6":
                    roundScore == 2? "6-7":
                            roundScore == 3? "7-9":
                                    roundScore == 4? "9-10":
                                            roundScore == 5? "10-11":
                                                    roundScore == 6? "11-12":
                                                            roundScore == 7? "12-13":
                                                                    roundScore == 8? "13-14":
                                                                            roundScore == 9? "14-15":
                                                                                    roundScore == 10? "15-16":
                                                                                            roundScore == 11? "16-17":
                                                                                                    roundScore == 12? "17-18":
                                                                                                            roundScore == 13? "18-24":
                                                                                                                    "24+";

            System.out.printf("The score is: %f\n" +
                    "This text should be understood by %s-year-olds.", score, people);

        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }
}
