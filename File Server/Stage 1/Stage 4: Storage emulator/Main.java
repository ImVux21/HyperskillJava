package server;

import java.util.*;

public class Main {
    static List<File> list = new ArrayList<File>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isStopped = false;

        while (!isStopped) {
            String command = scanner.nextLine();
            String[] convertCommand = convertCommandMethod(command);
            int orderFile = 0;

            switch (convertCommand[0]) {
                case "add":
                    File file = new File(convertCommand[1]);
                    if (convertCommand.length > 1) {
                        try {
                            orderFile = createOrderFile(convertCommand[1]);
                            if (!isExistedFile(convertCommand[1]) && !isLimited(orderFile)) {
                                list.add(file);
                                System.out.printf("The file %s added successfully\n", file.getName());
                            } else {
                                System.out.printf("Cannot add the file %s\n", convertCommand[1]);
                            }
                        } catch (NumberFormatException e) {
                            System.out.printf("Cannot add the file %s\n", convertCommand[1]);
                        }
                    }
                    break;
                case "get":
                    if (isExistedFile(convertCommand[1])) {
                        System.out.printf("The file %s was sent\n", convertCommand[1]);
                    } else {
                        System.out.printf("The file %s not found\n", convertCommand[1]);
                    }
                    break;
                case "delete":
                    if (isExistedFile(convertCommand[1])) {
                        list.remove(getFile(convertCommand[1]));
                        System.out.printf("The file %s was deleted\n", convertCommand[1]);
                    } else {
                        System.out.printf("The file %s not found\n", convertCommand[1]);
                    }
                    break;
                case "exit":
                    isStopped = true;
                    break;
            }
        }
    }

    private static File getFile(String nameFile) {
        for (File f : list) {
            if (f.getName().equals(nameFile)) {
                return f;
            }
        }
        return null;
    }

    private static int createOrderFile(String s) {
        return Integer.parseInt(s.substring(4));
    }

    private static boolean isLimited(int orderFile) {
        return orderFile > 10;
    }

    private static boolean isExistedFile(String newFileName) {
        for (File file : list) {
            if (file.getName().equals(newFileName)) {
                return true;
            }
        }
        return false;
    }

    private static String[] convertCommandMethod(String command) {
        return command.split("\\s+");
    }
}
