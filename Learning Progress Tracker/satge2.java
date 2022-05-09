package tracker;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static boolean isValidate(String email, String name) {
        boolean isValidEmail = false;
        boolean isValidFirstName = false;
        boolean isValidLastName = false;
        // check name
        String[] l = name.split(" ");
        String firstName = l[0];
        String lastName = name.substring(firstName.length()).trim();
        String regexFirstName = "\\w[^\\[$&+,:;=?@#|<>.^*()%!\\]]*\\w"; // [^\[$&+,:;=?@#|<>.^*()%!\]] everything{'-} except for special symbols
        String regexLastName = "\\w[^\\[$&+,:;=?@#|<>.^*()%!\\]]*\\w";
        if (firstName.matches(regexFirstName) && firstName.length() > 1
            && !(firstName.contains("'-") || firstName.contains("-'"))
            && !(firstName.contains("--") || firstName.contains("''"))) {
            isValidFirstName = true;
        }
        if (lastName.matches(regexLastName) && lastName.length() > 1
                && !(lastName.contains("'-") || lastName.contains("-'")) // error: 2 invalid symbols next to each others
                && !(lastName.contains("--") || lastName.contains("''"))) {
            isValidLastName = true;
        }
        // check email
        String regex = "[^\\[$&+,:;=?@#|<>^\\-'*()%!\\]]+@[^\\[$&+,:\\-';=?@#|<>.^*()%!\\]]+\\..+";
        if (email.matches(regex)) {
            isValidEmail = true;
        }

        if (l.length < 2) {
            System.out.println("Incorrect credentials.");
        }
        else if (!isValidFirstName) {
            System.out.println("Incorrect first name.");
        } else if (!isValidLastName) {
            System.out.println("Incorrect last name.");
        } else if (!isValidEmail) {
            System.out.println("Incorrect email.");
        }
        return isValidEmail && isValidFirstName && isValidLastName;
    }


    public static void addStudentCredentials(String information, Map<String, String> listStudents) {
        String[] l = information.split(" ");
        String email = l[l.length-1];
        String name = information.substring(0, (information.length()-email.length())).trim();

        if (isValidate(email, name)) {
            listStudents.put(name, email);
            System.out.println("The student has been added.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        boolean isChecked = true;
        boolean isChecked2 = true;
        while (isChecked) {
            int count = 0;
            String userEnterChoice = scanner.nextLine();

            if(userEnterChoice.equals("add students")) {
                System.out.println("Enter student credentials or 'back' to return:");
                Map<String, String> listStudents = new LinkedHashMap<>();
                while (isChecked2) {
                    String information = scanner.nextLine();
                    if (information.equals("back")) {
                        System.out.printf("Total %d students have been added.\n", listStudents.size());
                        isChecked2 = false;
                    } else {
                        addStudentCredentials(information, listStudents);
                    }
                    count++;
                }
            }
            else if(userEnterChoice.equals("back")) {
                if (count == 0) {
                    System.out.println("Enter 'exit' to exit the program.");
                }
            }
            else if(userEnterChoice.equals("exit")) {
                isChecked = false;
                System.out.println("Bye!");
            }
            else if(userEnterChoice.trim().equals("")) {
                System.out.println("No input.");
            }
            else {
                System.out.println("Unknown command!");
            }
        }
    }
}
