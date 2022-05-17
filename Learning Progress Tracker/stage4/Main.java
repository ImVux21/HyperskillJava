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
                    case "add students": sm.addStudentCommand(); break;
                    case "list": sm.listStudentCommand(); break;
                    case "add points": sm.addPointCommand(); break;
                    case "find": sm.findStudentCommand(); break;
                    case "statistics": Course.calculateStatisticsCommand(); break;
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
