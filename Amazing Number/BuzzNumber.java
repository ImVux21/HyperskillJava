package amazingNumber;

import java.util.Scanner;

public class BuzzNumber {
    public static void main(String[] args) {
        // write your code here
        Scanner input = new Scanner(System.in);
        System.out.println("Enter natural number: ");
        int num = input.nextInt();
        boolean check = true;

        // while loop is remind users enter correct integer number
        while (check) {
            if (num <= 0) {
                System.out.println("This number is not natural!");
                System.out.println("Enter natural number again: ");
                num = input.nextInt();
            } else {
                check = false;
            }
        }

        // even number
        if (num % 2 ==0) {
            // buzz number is divisible by 7
            if (num % 7 == 0) {
                System.out.println("This number is Even.");
                System.out.println("It is a Buzz number.");
                System.out.println("Explanation: ");
                System.out.println(num + " is divisible by 7.");
            }
            // only is even number
            else {
                System.out.println("This number is Even.");
                System.out.println("It is not a Buzz number.");
                System.out.println("Explanation: ");
                System.out.println(num + " is neither divisible by 7 nor does it end with 7.");
            }
        }
        // odd number
        else {
            if (num % 7 == 0) {
                // buzz number are both divisible by 7 and end with 7
                if(num % 10 == 7) {
                    System.out.println("This number is Odd.");
                    System.out.println("It is a Buzz number.");
                    System.out.println("Explanation: ");
                    System.out.println(num + " is divisible by 7 and ends with 7.");
                }
                // buzz only divisible by 7
                else {
                    System.out.println("This number is Odd.");
                    System.out.println("It is a Buzz number.");
                    System.out.println("Explanation: ");
                    System.out.println(num + " is divisible by 7.");
                }
            }
            // buzz only end with 7
            else if (num % 10 == 7) {
                System.out.println("This number is Odd.");
                System.out.println("It is a Buzz number.");
                System.out.println("Explanation: ");
                System.out.println(num + " ends with 7.");
            }
            // onlu odd number
            else {
                System.out.println("This number is Odd.");
                System.out.println("It is not a Buzz number.");
                System.out.println("Explanation: ");
                System.out.println(num + " is neither divisible by 7 nor does it end with 7.");
            }
        }

    }
}

