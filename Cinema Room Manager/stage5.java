package cinema;

import java.util.Scanner;

public class Cinema {

    public static void showStatics(int rows, int seats, char[][] cinema) {
        int numberTickets = 0;
        int income = 0;
        // find The number of purchased tickets and current income.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                if (cinema[i][j] == 'B') {
                    numberTickets++;
                    if ((rows * seats) <= 60) {
                        income += 10;
                    } else {
                        if (i > (rows / 2)) {
                            income += 8;
                        }
                        else {
                            income += 10;
                        }
                    }
                }
            }
        }

        System.out.println("Number of purchased tickets: " + numberTickets);

        // calculate The number of purchased tickets represented as a percentage.
        float percentageOfTickets = (float) (numberTickets * 100) / (seats * rows);
        System.out.format("Percentage: %.2f", percentageOfTickets);
        System.out.println("%");

        // Current income.
        System.out.println("Current income: $" + income);

        // The total income that shows how much money the theatre will get if all the tickets are sold.
        System.out.println("Total income: $" + totalIncome(rows, seats));
        System.out.println("");
    }

    public static int totalIncome(int rows, int seats) {
        if ((rows * seats) <= 60) {
            return rows * seats * 10;
        } else {
            if (rows % 2 != 0) {
                int firstRow = rows / 2;
                int secondRow = firstRow + 1;
                return ((firstRow * 10) + (secondRow * 8)) * seats;
            } else {
                return ( 10 + 8) * seats * (rows / 2);
            }
        }
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
        boolean isChecked = true;
        int rowNumber = 0;
        System.out.println("Enter the number of rows:");
        int rows = input.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = input.nextInt();
        System.out.println("");
        char[][] cinema = createCinema(rows, seats);

        while (isChecked) {
            boolean checkError = true;
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            int choice = input.nextInt();
            System.out.println("");
            switch(choice) {
                case 1:
                    showCinema(rows, seats, cinema);
                    System.out.println("");
                    break;
                case 2:
                    while (checkError) {
                        System.out.println("Enter a row number:");
                        rowNumber = input.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int seatNumber = input.nextInt();
                        System.out.println("");
                        if (rowNumber > rows || seatNumber > seats) {
                            System.out.println("Wrong input!");
                        } else {
                            if (cinema[rowNumber - 1][seatNumber - 1] == 'S') {
                                cinema[rowNumber - 1][seatNumber - 1] = 'B';
                                checkError = false;
                                System.out.println("Ticket price: $" + checkPrice(rows, seats, rowNumber));
                            } else {
                                System.out.println("That ticket has already been purchased!");
                            }
                        }
                        System.out.println("");
                    }
                    break;
                case 3:
                    showStatics(rows, seats, cinema);
                    break;
                case 0:
                    isChecked = false;
                    break;
            }
        }
    }
}
