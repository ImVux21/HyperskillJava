package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> search(File f, String inforToSearch) {
        ArrayList<String> result = new ArrayList<String>();
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String findPeople = sc.nextLine();
                if (findPeople.toLowerCase().contains(inforToSearch.toLowerCase())) {
                    result.add(findPeople);
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return result;
    }

    public static void printMenu() {
        System.out.println("\n=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }

    public static void printAllPeople(File f) {
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine())
                System.out.println(sc.nextLine() +"\n");
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File f = new File(args[1]);

        int userSelected = 1;
        while (userSelected != 0) {
            printMenu();
            userSelected = Integer.parseInt(scanner.nextLine());
            switch (userSelected) {
                case 1:
                    System.out.println("\nEnter a name or email to search all suitable people.");
                    String inforSearch = scanner.nextLine();
                    ArrayList<String> result = search(f, inforSearch);
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
                    printAllPeople(f);
                    break;
                case 0:
                    System.out.println("\nBye!");
                    break;
                default:
                    System.out.println("\nIncorrect option! Try again.");
            }
        }
        f.delete();
    }
}
