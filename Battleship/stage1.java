package battleship;

import java.util.Scanner;

public class Main {
    static String[][] fields = createFields();
    enum Ships {
        CARRIER("Aircraft Carrier", 5),
        BATTLESHIP("Battleship", 4),
        CRUISER("Submarine", 3),
        SUBMARINE("Cruiser", 3),
        DESTROYER("Destroyer", 2);

        public final String name;
        public final int length;

        Ships(String name, int length) {
            this.name = name;
            this.length = length;
        }
    }

    public static void createShips(int[] coordinates) {
        // check horizontal
        if (coordinates[0] == coordinates[2]) {
            for (int i = coordinates[1]; i <= coordinates[3]; i++) {
                fields[coordinates[0]][i] = "O";
            }
            showFields(fields);
        }
        // check vertical coordinates
        else if (coordinates[1] == coordinates[3]) {
            for (int i = coordinates[0]; i <= coordinates[2]; i++) {
                fields[i][coordinates[1]] = "O";
            }
            showFields(fields);
        }
    }

    public static boolean checkLength(Ships ship, int[] coordinates) {
        return ship.length == (coordinates[3] - coordinates[1] + 1) || ship.length == (coordinates[2] - coordinates[0] + 1);
    }

    public static boolean checkLocation(int[] coordinates) {
        return coordinates[0] != coordinates[2] && coordinates[1] != coordinates[3];
    }

    public static boolean checkClose(int[] coordinates) {
        boolean isSouthSide = coordinates[0] > 0; // eliminate out of bound along with horizontal
        boolean isNorthSide = coordinates[2] < 9; // eliminate out of bound along with horizontal
        boolean isWestSide = coordinates[1] > 0; // eliminate out of bound along with vertical
        boolean isEastSide = coordinates[3] < 9; // eliminate out of bound along with vertical

        // horizontal
        if (coordinates[0] == coordinates[2]) {
            for (int i = coordinates[1] ; i <= coordinates[3]; i++) {
                // Southern of Ship
                if (isSouthSide)
                    if (fields[coordinates[0] - 1][i].equals("O")) {
                        return true;
                    }
                //Northern of Ship
                if (isNorthSide)
                    if (fields[coordinates[0] + 1][i].equals("O")) {
                        return true;
                    }
            }
            // Western of Ship
            if (isWestSide)
                if (fields[coordinates[0]][coordinates[1] - 1].equals("O")) {
                    return true;
                }
            // Eastern of Ship
            if (isEastSide)
                if (fields[coordinates[0]][coordinates[3] + 1].equals("O")) {
                    return true;
                }
        }
        // vertical
        if (coordinates[1] == coordinates[3]) {
            // Southern of Ship
            if (isSouthSide)
                if (fields[coordinates[0] - 1][coordinates[1]].equals("O")) {
                    return true;
                }
            //Northern of Ship
            if (isNorthSide)
                if (fields[coordinates[2] + 1][coordinates[1]].equals("O")) {
                    return true;
                }
            if (isWestSide)
                for (int i = coordinates[0]; i <= coordinates[2]; i++) {
                    // Western of Ship
                    if (fields[i][coordinates[1]].equals("O")) {
                        return true;
                    }
                }
            if (isEastSide)
                for (int i = coordinates[0]; i <= coordinates[2]; i++) {
                    // Eastern of Ship
                    if (fields[i][coordinates[3]].equals("O")) {
                        return true;
                    }
                }
        }

        return false;
    }

    public static int[] convertCoordinate(String coordinates) {
        String[] splitCoordinates = coordinates.split(" ");
        String[] firstCoordinates = splitCoordinates[0].split("");
        String[] secondCoordinates = splitCoordinates[1].split("");
        int[] convertCoordinates = new int[4];

        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (int i = 0; i < 10; i++) {
            if (firstCoordinates[0].equals(alphabet[i])) {
                convertCoordinates[0] = Integer.parseInt(String.valueOf(i)); // value of letters vertical line
                convertCoordinates[1] = Integer.parseInt(splitCoordinates[0].substring(1)) - 1; // value of numbers horizontal line
            }
            if (secondCoordinates[0].equals(alphabet[i])) {
                convertCoordinates[2] = Integer.parseInt(String.valueOf(i));
                convertCoordinates[3] = Integer.parseInt(splitCoordinates[1].substring(1)) - 1;
            }
        }
        // exchange coordinates ascending numbers
        int temp = 0;
        if (convertCoordinates[0] > convertCoordinates[2]) {
            temp = convertCoordinates[0];
            convertCoordinates[0] = convertCoordinates[2];
            convertCoordinates[2] = temp;
        }
        if (convertCoordinates[1] > convertCoordinates[3]) {
            temp = convertCoordinates[1];
            convertCoordinates[1] = convertCoordinates[3];
            convertCoordinates[3] = temp;
        }

        return convertCoordinates;
    }

    public static String[][] createFields() {
        String[][] fields = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                fields[i][j] = "~";
            }
        }
        return fields;
    }

    public static void showFields(String[][] fields) {
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print(alphabet[i] + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(fields[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int count = 0;
        // Write your code here
        Ships[] ships = {Ships.CARRIER, Ships.BATTLESHIP, Ships.CRUISER, Ships.SUBMARINE, Ships.DESTROYER};
        showFields(fields);
        while (count < 5) {
            boolean isChecked = true;
            System.out.println();
            System.out.printf("Enter the coordinates of the %s (%d cells):", ships[count].name, ships[count].length);
            System.out.println();
            while (isChecked) {
                String coordinates = input.nextLine();
                System.out.println();
                if (!checkLength(ships[count], convertCoordinate(coordinates))) {
                    System.out.println("Error! Wrong length of the " + ships[count].name + "! Try again:");
                } else if (checkLocation(convertCoordinate(coordinates))) {
                    System.out.println("Error! Wrong ship location! Try again:");
                } else if (checkClose(convertCoordinate(coordinates))) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                }
                else {
                    createShips(convertCoordinate(coordinates));
                    count++;
                    isChecked = false;
                }
            }
        }
    }
}
