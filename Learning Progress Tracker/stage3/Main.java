package tracker;

import java.util.*;

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

    public static boolean alreadyExistsEmail(String email, Map<Integer, Students> listStudents) {
        for (Students s : listStudents.values()) {
            if (s.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
    public static void addStudentCredentials(String information, Map<Integer, Students> listStudents, int id) {
        String[] l = information.split(" ");
        String email = l[l.length-1];
        String name = information.substring(0, (information.length()-email.length())).trim();

        if (isValidate(email, name)) {
            if (alreadyExistsEmail(email, listStudents)) {
                listStudents.put(id, new Students(name, email, new int[]{0, 0, 0, 0}));
                System.out.println("The student has been added.");
            } else {
                System.out.println("This email is already taken.");
            }
        }
    }

    public static int isIds(String id, Map<Integer, Students> listStudents) {
        for (int i : listStudents.keySet())
            if (Integer.parseInt(id) == i)
                return i;
        return 0;
    }

    public static void addPoints(String points, Map<Integer, Students> listStudents) {
        String[] l = points.split(" ");
        boolean isChecked = true;
        int count = 1;
        for (int i = 0; i < 4; i++) {
            if (l.length != 5 || !l[count].matches("\\d+") || Integer.parseInt(l[count]) < 0) {
                isChecked = false;
                System.out.println("Incorrect points format.");
                break;
            }
            count++;
        }

        count = 1;
        if (isChecked)
            try {
                if (isIds(l[0], listStudents) == 0) {
                    System.out.printf("No student is found for id=%s\n", l[0]);
                } else {
                    for (int i = 0; i < 4; i++) {
                        listStudents.get(isIds(l[0], listStudents)).getPoints()[i] += Integer.parseInt(l[count]);
                        count++;
                    }
                    System.out.println("Points updated.");
                }
            } catch (NumberFormatException e) {
                System.out.printf("No student is found for id=%s\n", l[0]);
            }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Students> listStudents = new LinkedHashMap<>();

        System.out.println("Learning Progress Tracker");
        boolean isChecked = true;

        while (isChecked) {
            boolean isChecked2 = true;
            int count = 0;
            int originalId = 10000;
            String userEnterChoice = scanner.nextLine();

            if(userEnterChoice.equals("add students")) {
                System.out.println("Enter student credentials or 'back' to return:");

                while (isChecked2) {
                    String information = scanner.nextLine();
                    if (information.equals("back")) {
                        System.out.printf("Total %d students have been added.\n", listStudents.size());
                        isChecked2 = false;
                    } else {
                        addStudentCredentials(information, listStudents, originalId);
                        originalId++;
                    }
                    count++;
                }
            }
            else if (userEnterChoice.equals("list")) { // add IDs
                // add id's students by starting with 10000
                if (listStudents.size() > 0) {
                    System.out.println("Students:");
                    for (var entry : listStudents.entrySet()) {
                        System.out.println(entry.getKey());
                    }
                } else {
                    System.out.println("No students found.");
                }
            } else if (userEnterChoice.equals("add points")) {
                System.out.println("Enter an id and points or 'back' to return:");
                while (isChecked2) {
                    String points = scanner.nextLine();
                    if (!points.equals("back")) {
                        addPoints(points, listStudents);
                    } else {
                        isChecked2 = false;
                    }
                }
            }
            else if (userEnterChoice.equals("find")) {
                System.out.println("Enter an id or 'back' to return:");
                while (isChecked2) {
                    String id = scanner.nextLine();
                    if (id.equals("back")) {
                        isChecked2 = false;
                    }
                    else if (isIds(id, listStudents) != 0) {
                        int[] listPoint = listStudents.get(isIds(id, listStudents)).getPoints();
                        System.out.printf(id + " points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n", listPoint[0], listPoint[1], listPoint[2], listPoint[3]);
                    } else {
                        System.out.printf("No student is found for id=%s.\n", id);
                    }
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
