package battleship;

import java.util.Scanner;

public class Main {
    static String[][] fields = createFields();
    static String[][] fogsFields = createFields();

    enum Ships {
        CARRIER("Aircraft Carrier", 5),
        BATTLESHIP("Battleship", 4),
        CRUISER("Submarine", 3),
        SUBMARINE("Cruiser", 3),
        DESTROYER("Destroyer", 2);

        public final String name;
        public final int length;
        public String[][] position;

        Ships(String name, int length) {
            this.name = name;
            this.length = length;
            this.position = new String[10][10];
        }
    }
    static Ships[] ships = {Ships.CARRIER, Ships.BATTLESHIP, Ships.CRUISER, Ships.SUBMARINE, Ships.DESTROYER};

    public static void createShips(int[] coordinates, String[][] position) {
        // check horizontal
        if (coordinates[0] == coordinates[2]) {
            for (int i = coordinates[1]; i <= coordinates[3]; i++) {
                fields[coordinates[0]][i] = "O";
                position[coordinates[0]][i] = "O";
            }
        }
        // check vertical coordinates
        else if (coordinates[1] == coordinates[3]) {
            for (int i = coordinates[0]; i <= coordinates[2]; i++) {
                fields[i][coordinates[1]] = "O";
                position[i][coordinates[1]] = "O";
            }
        }

        showFields(fields);
    }

    public static boolean checkLength(Ships ship, int[] coordinates) {
        return ship.length == (coordinates[3] - coordinates[1] + 1) || ship.length == (coordinates[2] - coordinates[0] + 1);
    }

    public static boolean checkInvalidCoordinates(int[] coordinates) {
        return coordinates[0] != coordinates[2] && coordinates[1] != coordinates[3];
    }

    public static boolean checkCloseness(int[] coordinates) {
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

    public static boolean checkWinner(String[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                if (fields[i][j].equals("O")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkSankAShip(String[][] position) {
        boolean isChecked = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (position[i][j].equals("O")) {
                    isChecked = false;
                    break;
                }
            }
        }
        return isChecked;
    }

    public static int[] convertCoordinate(String coordinates) {
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] splitCoordinates;
        int[] convertCoordinates;
        if (coordinates.contains(" ")) { // coordinates of adding turn
            splitCoordinates = coordinates.split(" ");
            String[] firstCoordinates = splitCoordinates[0].split("");
            String[] secondCoordinates = splitCoordinates[1].split("");
            convertCoordinates = new int[4];

            for (int i = 0; i < 26; i++) {
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
            int temp;
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
        }
        // coordinates of hitting turn
        else {
            convertCoordinates = new int[2];
            splitCoordinates = coordinates.split("");
            for (int i = 0; i < 26; i++) {
                if (splitCoordinates[0].equals(alphabet[i])) {
                    convertCoordinates[0] = Integer.parseInt(String.valueOf(i)); // value of letters vertical line
                    convertCoordinates[1] = Integer.parseInt(coordinates.substring(1)) - 1; // value of numbers horizontal line
                }
            }
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
        System.out.println();
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print(alphabet[i] + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(fields[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void addShips() {
        Scanner input = new Scanner(System.in);
        int count = 0;
        // Write your code here
        showFields(fields);
        while (count < ships.length) {
            boolean isChecked = true;
            System.out.println();
            System.out.printf("Enter the coordinates of the %s (%d cells):", ships[count].name, ships[count].length);
            System.out.println();
            while (isChecked) {
                String coordinates = input.nextLine();
                System.out.println();
                if (!checkLength(ships[count], convertCoordinate(coordinates))) {
                    System.out.println("Error! Wrong length of the " + ships[count].name + "! Try again:");
                } else if (checkInvalidCoordinates(convertCoordinate(coordinates))) {
                    System.out.println("Error! Wrong ship location! Try again:");
                } else if (checkCloseness(convertCoordinate(coordinates))) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                }
                else {
                    ships[count].position = createFields(); // create ship fields one by one
                    createShips(convertCoordinate(coordinates), ships[count].position); // create ships on the fields
                    count++;
                    isChecked = false;
                }
            }
        }
    }

    public static Ships checkSpecifiedShip(int[] coordinates) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (ships[i].position[coordinates[0]][coordinates[1]].equals(fields[coordinates[0]][coordinates[1]]) && fields[coordinates[0]][coordinates[1]].equals("O")) {
                count = i;
                break;
            }
        }
        return ships[count];
    }

    public static void hitShip(int[] coordinates) {
        Ships subShip = checkSpecifiedShip(coordinates);
        if (fields[coordinates[0]][coordinates[1]].equals("O") || fields[coordinates[0]][coordinates[1]].equals("X")) {
            fields[coordinates[0]][coordinates[1]] = "X"; // hit at the field having ships
            fogsFields[coordinates[0]][coordinates[1]] = "X"; // hit at the fog field
            subShip.position[coordinates[0]][coordinates[1]] = "~"; // hit at the specified position of ship field

            showFields(fogsFields);
            if (checkWinner(fields)) {
                if (checkSankAShip(subShip.position)) {
                    System.out.println("You sank a ship! Specify a new target:");
                } else {
                    System.out.println("You hit a ship! Try again:");
                }
            } else {
                System.out.println("You sank the last ship. You won. Congratulations!");
            }
        } else {
            fogsFields[coordinates[0]][coordinates[1]] = "M";
            showFields(fogsFields);
            System.out.println("You missed. Try again:");
        }
    }

    public static void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("The game starts!");
        showFields(fogsFields);
        System.out.println("Take a shot!");
        do {
            String coordinates = scanner.nextLine();
            // checks out of fields
            boolean checkFirstOutOfBound = convertCoordinate(coordinates)[0] >= 0 && convertCoordinate(coordinates)[0] < 10; // out of bounds are western and eastern
            boolean checkSecondOutOfBound = convertCoordinate(coordinates)[1] >= 0 && convertCoordinate(coordinates)[1] < 10; // out of bounds are north and south

            if (checkFirstOutOfBound && checkSecondOutOfBound) {
                hitShip(convertCoordinate(coordinates));
            } else {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }
        } while (checkWinner(fields));
    }

    public static void main(String[] args){
        addShips();
        startGame();
    }
}
