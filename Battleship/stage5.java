
import java.util.Scanner;

public class Main {

    enum Ships {
        CARRIER1("Aircraft Carrier", 5),
        BATTLESHIP1("Battleship", 4),
        CRUISER1("Submarine", 3),
        SUBMARINE1("Cruiser", 3),
        DESTROYER1("Destroyer", 2),
        CARRIER2("Aircraft Carrier", 5),
        BATTLESHIP2("Battleship", 4),
        CRUISER2("Submarine", 3),
        SUBMARINE2("Cruiser", 3),
        DESTROYER2("Destroyer", 2);

        public final String name;
        public final int length;
        public String[][] position;

        Ships(String name, int length) {
            this.name = name;
            this.length = length;
            this.position = new String[10][10];
        }
    }

    public static void createShips(int[] coordinates, String[][] position, String[][] fields) {
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

    public static boolean checkCloseness(int[] coordinates, String[][] fields) {
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
        boolean isChecked = false;
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                if (fields[i][j].equals("O")) {
                    isChecked = true;
                    break;
                }
            }
        }
        return isChecked;
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

    public static void addShips(String[][] fields, Ships[] ships ) {
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
                try {
                    String coordinates = input.nextLine();
                    System.out.println();
                    if (!checkLength(ships[count], convertCoordinate(coordinates))) {
                        System.out.println("Error! Wrong length of the " + ships[count].name + "! Try again:");
                    } else if (checkInvalidCoordinates(convertCoordinate(coordinates))) {
                        System.out.println("Error! Wrong ship location! Try again:");
                    } else if (checkCloseness(convertCoordinate(coordinates), fields)) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                    } else {
                        ships[count].position = createFields(); // create ship fields one by one
                        createShips(convertCoordinate(coordinates), ships[count].position, fields); // create ships on the fields
                        count++;
                        isChecked = false;
                    }
                } catch (Exception e) {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                }
            }
        }
    }

    public static Ships checkSpecifiedShip(int[] coordinates, Ships[] ships, String[][] fields) {
        // return a ship to consider the sank ship in ship's position
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (ships[i].position[coordinates[0]][coordinates[1]].equals(fields[coordinates[0]][coordinates[1]]) && fields[coordinates[0]][coordinates[1]].equals("O")) {
                count = i;
                break;
            }
        }
        return ships[count];
    }

    public static void hitShip(int[] coordinates, Ships[] ships, String[][] fields, String[][] fogsFields) {
        Ships subShip = checkSpecifiedShip(coordinates, ships, fields);
        if (fields[coordinates[0]][coordinates[1]].equals("O") || fields[coordinates[0]][coordinates[1]].equals("X")) {
            fields[coordinates[0]][coordinates[1]] = "X"; // hit at the field having ships
            fogsFields[coordinates[0]][coordinates[1]] = "X"; // hit at the fog field
            subShip.position[coordinates[0]][coordinates[1]] = "~"; // hit at the specified position of ship field

            if (checkWinner(fields)) {
                if (checkSankAShip(subShip.position)) {
                    System.out.println("You sank a ship!");
                } else {
                    System.out.println("You hit a ship!");
                }
            } else {
                System.out.println("You sank the last ship. You won. Congratulations!");
            }
        } else {
            fogsFields[coordinates[0]][coordinates[1]] = "M";
            System.out.println("You missed!");
        }
    }

    public static void pressEnter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        String[][] player1Fields = createFields();
        String[][] player2Fields = createFields();
        String[][][] playerFields = {player1Fields, player2Fields};
        String[][] fogsFields1 = createFields();
        String[][] fogsFields2 = createFields();
        String[][][] fogsFields = {fogsFields1, fogsFields2};
        Ships[] ships1 = {Ships.CARRIER1, Ships.BATTLESHIP1, Ships.CRUISER1, Ships.SUBMARINE1, Ships.DESTROYER1};
        Ships[] ships2 = {Ships.CARRIER2, Ships.BATTLESHIP2, Ships.CRUISER2, Ships.SUBMARINE2, Ships.DESTROYER2};
        Ships[][] ships = {ships1, ships2};

        int player = 1;

        while (player < 3) {
            System.out.println("Player " + player + ", place your ships on the game field");
            addShips(playerFields[player - 1], ships[player - 1]);
            pressEnter();
            player++;
        }
        // starting game
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        player = 0;
        while (true){
            if (player % 2 == 0) {
                player = 0;
            } else {
                player = 1;
            }
            showFields(fogsFields[player]);
            System.out.println("---------------------");
            showFields(playerFields[player]);
            System.out.println("Player " + (player + 1) + ", it's your turn:");
            boolean isChecked = true;
            while (isChecked) {
                try {
                    String coordinates = scanner.nextLine();
                    // checks out of fields
                    boolean checkFirstOutOfBound = convertCoordinate(coordinates)[0] >= 0 && convertCoordinate(coordinates)[0] < 10; // out of bounds are western and eastern
                    boolean checkSecondOutOfBound = convertCoordinate(coordinates)[1] >= 0 && convertCoordinate(coordinates)[1] < 10; // out of bounds are north and south

                    if (checkFirstOutOfBound && checkSecondOutOfBound) {
                        if (player % 2 == 0) {
                            hitShip(convertCoordinate(coordinates), ships2, player2Fields, fogsFields1);
                        } else {
                            hitShip(convertCoordinate(coordinates), ships1, player1Fields, fogsFields2);
                        }
                        player++;
                        isChecked = false;
                    } else {
                        System.out.println("Error! You entered the wrong coordinates! Try again:");
                    }
                } catch (Exception e) {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                }
            }
            if (checkWinner(player1Fields) && player % 2 == 0) {
                pressEnter();
            } else if (checkWinner(player2Fields) && player % 2 != 0) {
                pressEnter();
            }
            // Stop The Game
            if (!checkWinner(player1Fields)) {
                break;
            } else if (!checkWinner(player2Fields)) {
                break;
            }
        }

    }
}
