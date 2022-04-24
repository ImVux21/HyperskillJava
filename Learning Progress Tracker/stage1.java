package tracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        String userEnter = "";
        while (!userEnter.equals("exit")) {
            userEnter = scanner.nextLine();
            if (userEnter.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else if (userEnter.trim().equals("")) {
                System.out.println("No input.");
            } else {
                System.out.println("Error: unknown command!");
            }
        }
    }
}
