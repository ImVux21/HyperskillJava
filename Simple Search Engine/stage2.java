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

        System.out.println("\nEnter the number of search queries:");
        int numTimesSearch = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numTimesSearch; i++) {
            System.out.println("\nEnter data to search people:");
            String inforSearch = scanner.nextLine();
            ArrayList<String> result = search(all, inforSearch);
            if (result.size() != 0) {
                System.out.println("\nFound People:");
                for (String s : result) {
                    System.out.println(s);
                }
            } else {
                System.out.println("No matching people found.");
            }
        }
    }
}
