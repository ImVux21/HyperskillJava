package tracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        StudentManager sm = new StudentManager();
        boolean stop = false;

        while (!stop) {
            String input = scanner.nextLine();

            if (hasInput(input)) {
                switch (input) {
                    case "add students": sm.addStudent(); break;
                    case "list": sm.listStudent(); break;
                    case "add points": sm.addPoint(); break;
                    case "find": sm.findStudent(); break;
                    case "back":
                        System.out.println("Enter 'exit' to exit the program.");
                        break;
                    case "exit": stop = true; break;
                    default: System.out.println("Unknown command.");
                }
            } else {
                System.out.println("No input.");
            }
        }
        System.out.println("Bye!");
    }

    private static boolean hasInput(String input) {
        return !input.isBlank(); // if blank, return false
    }
}
