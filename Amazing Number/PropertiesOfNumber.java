package amazingNumber;

import java.util.Scanner;

public class PropertiesOfNumber {
    public static void main(String[] args) {
        // write your code here
        Scanner input = new Scanner(System.in);
        System.out.println("Enter natural number: ");
        int num = input.nextInt();
        String newNumToString = Integer.toString(num);

        // initialize variables
        boolean check = true;
        char singleLetter;
        boolean even = false;
        boolean odd = false;
        boolean buzz = false;
        boolean duck = false;

        // odd or even
        if(num % 2 == 0) {
            even = true;
        } else {
            odd = true;
        }

        // buzz number( divisible by 7 or have last number is 7 )
        if (num % 7 ==0 || num % 10 == 7) {
            buzz = true;
        }

        // check duck number ( contain 0, ex:608,800...)
        for(int i=0; i<newNumToString.length(); i++) {
            singleLetter = newNumToString.charAt(i);
            if (singleLetter == '0') {
                duck = true;
            }
        }

        // print properties of number
        // while loop is remind users enter correct integer number
        while (check) {
             if (num <= 0) {
                System.out.println("This number is not natural!");
                System.out.println("Enter a integer again: ");
                num = input.nextInt();
             } else {
                 check = false;
                 System.out.println("Properties of " + num);
                 System.out.println("        even: " + even);
                 System.out.println("        odd: " + odd);
                 System.out.println("        buzz: " + buzz);
                 System.out.println("        duck: " + duck);
             }
        }

    }
}

