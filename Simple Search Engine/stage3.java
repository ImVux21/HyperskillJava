package search;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> search(String all, String inforToSearch) {
        Scanner sc = new Scanner(all);
        ArrayList<String> result = new ArrayList<String>();
        while (sc.hasNextLine()) {
            String findPeople = sc.nextLine();
            if (findPeople.toLowerCase().contains(inforToSearch.toLowerCase())) {
                result.add(findPeople);
            }
        }
        return result;
    }

    public static void printMenu() {
        System.out.println("\n=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of people:");
        int numPeople = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter all people:");
        String all = "";
        for (int i = 0; i < numPeople; i++) {
            String name = scanner.nextLine();
            all += name + "\n";
        }

        int userSelected = 1;
        while (userSelected != 0) {
            printMenu();
            userSelected = Integer.parseInt(scanner.nextLine());
            switch (userSelected) {
                case 1:
                    System.out.println("\nEnter a name or email to search all suitable people.");
                    String inforSearch = scanner.nextLine();
                    ArrayList<String> result = search(all, inforSearch);
                    if (result.size() != 0) {
                        for (String s : result) {
                            System.out.println(s);
                        }
                    } else {
                        System.out.println("No matching people found.");
                    }
                    break;
                case 2:
                    System.out.println("\n=== List of people ===");
                    System.out.println(all);
                    break;
                case 0:
                    System.out.println("\nBye!");
                    break;
                default:
                    System.out.println("\nIncorrect option! Try again.");
            }
        }
    }
}
