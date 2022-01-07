package cinema;

import java.util.Scanner;

public class Cinema {
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

    public static void main(String[] args) {
        // Write your code here
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = input.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = input.nextInt();
        System.out.println("Total income:\n" + "$" + totalIncome(rows, seats));
    }
}
