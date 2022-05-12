package tracker;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentManager {
    Map<String, Student> map = new LinkedHashMap<>();
    private int id = 10000;
    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        int count = 0;
        System.out.println("Enter student credentials or 'back' to return");

        while (!stop) {
            String info = scanner.nextLine();
            if (info.equals("back")) {
                stop = true;
                System.out.printf("Total %d students have been added.\n", count);
            } else {
                if (isCorrectCredentials(info)) {
                    String[] parts = info.split("\\s+");
                    String firstName = parts[0];
                    String email = parts[parts.length - 1];
                    String lastName = info.substring(firstName.length(), (info.length()-email.length())).trim();
                    if (!emailExists(email)) {
                        Student student = new Student(firstName, lastName, email);
                        map.put(String.valueOf(id), student);
                        System.out.println("The student has been added.");
                        count++;
                        id++;
                    } else {
                        System.out.println("This email is already taken.");
                    }
                }
            }
        }

    }

    private boolean emailExists(String email) {
        for (Student student : map.values()) {
            if (student.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCorrectCredentials(String info) {
        String[] parts = info.split("\\s+");

        if (parts.length < 3) {
            System.out.println("Incorrect credentials.");
            return false;
        }

        String firstName = parts[0];
        String email = parts[parts.length - 1];
        String lastName = info.substring(firstName.length(), (info.length()-email.length())).trim();
        if (!isCorrectEmail(email)) {
            System.out.println("Incorrect email.");
            return false;
        }

        if (!isCorrectFirstName(firstName)) {
            System.out.println("Incorrect first name.");
            return false;
        }

        if (!isCorrectLastName(lastName)) {
            System.out.println("Incorrect last name.");
            return false;
        }

        return true;
    }

    private boolean isCorrectLastName(String lastName) {
        String[] parts = lastName.split("\\s+");

        for (String s : parts) {
            if (!isCorrectWord(s)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCorrectFirstName(String firstName) {
        return isCorrectWord(firstName);
    }

    private boolean isCorrectWord(String word) {
        if (word.length() < 2) {
            return false;
        }

        String regex1 = "[a-zA-Z][a-zA-Z'\\-]*[a-zA-Z]";
        if (!word.matches(regex1)) {
            return false;
        }

        String regex2 = ".*--.*|.*''.*|.*'-.*|.*-'.*";
        if (word.matches(regex2)) {
            return false;
        }
        
        return true;
    }

    private boolean isCorrectEmail(String email) {
        String regex = "[^@]*@\\w+\\.\\w+";
        return email.matches(regex);
    }

    public void listStudent() {
        if (map.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Students:");
            for (String id : map.keySet()) {
                System.out.println(id);
            }
        }
    }

    public void addPoint() {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        System.out.println("Enter an id and points or 'back' to return:");

        while (!stop) {
            String input = scanner.nextLine();
            String[] parts = input.split("\\s+");
            String id = parts[0];
            String pointString = input.substring(id.length()).trim();

            if (input.equals("back")) {
                stop = true;
            } else {
                if (isCorrectPointsFormat(input)) {
                    if (idExists(id)) {
                        updatePoints(id, pointString);
                    } else {
                        System.out.printf("No student is found for id=%s.\n", id);
                    }
                } else {
                    System.out.println("Incorrect points format.");
                }
            }
        }
    }

    private void updatePoints(String id, String pointString) {
        Student student = map.get(id);
        int[] points = convertPoints(pointString);
        for (int i = 0; i < points.length; i++) {
            student.getPoints()[i] += points[i];
        }
        System.out.println("Points updated.");
    }

    private boolean idExists(String id) {
        return map.containsKey(id);
    }

    private int[] convertPoints(String pointString) {
        int[] points = new int[4];
        String[] parts = pointString.split("\\s+");

        for (int i = 0; i < points.length; i++) {
            points[i] = Integer.parseInt(parts[i]);
        }
        return points;
    }

    private boolean isCorrectPointsFormat(String input) {
        String[] parts = input.split("\\s+");

        if (parts.length != 5) {
            return false;
        }

        try {
            for (int i = 1; i < parts.length; i++) {
                int point = Integer.parseInt(parts[i]);

                if (point < 0) {
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void findStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an id or 'back' to return:");
        boolean stop = false;

        while (!stop) {
            String id = scanner.nextLine();
            if (id.equals("back")) {
                stop = true;
            } else {
                Student student = map.get(id);
                int javaPoint = student.getPoints()[0];
                int dsaPoint = student.getPoints()[1];
                int databasesPoint = student.getPoints()[2];
                int springPoint = student.getPoints()[3];
                if (idExists(id)) {
                    System.out.printf("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n",
                            id, javaPoint, dsaPoint, databasesPoint, springPoint);
                } else {
                    System.out.printf("No student is found for id=%s.\n", id);
                }
            }
        }
    }
}
