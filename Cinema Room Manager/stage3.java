package cinema;

import java.util.Scanner;

public class Cinema {
    public static char[][] checkSeats(int rows, int seats, char[][] cinema) {
        cinema[rows - 1][seats - 1] = 'B';
        return cinema;
    }

    public static int checkPrice(int rows, int seats, int rowNumber) {
        if ((rows * seats) <= 60) {
            return 10;
        } else {
            if (rowNumber > (rows / 2)) {
                return 8;
            }
             else {
                return 10;
            }
        }
    }

    public static char[][] createCinema(int rows, int seats) {
        char[][] cinema = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 'S';
            }
        }
        return cinema;
    }

    public static void showCinema(int rows, int seats, char[][] cinema) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 0; i < seats; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println("");
        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        // Write your code here
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = input.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = input.nextInt();
        char[][] cinema = createCinema(rows, seats);
        showCinema(rows, seats, createCinema(rows, seats));
        System.out.println("");
        System.out.println("Enter a row number:");
        int rowNumber = input.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = input.nextInt();
        System.out.println("");
        System.out.print("Ticket price: $" + checkPrice(rows, seats, rowNumber));
        showCinema(rows, seats, checkSeats(rowNumber, seatNumber, cinema));
    }
}
