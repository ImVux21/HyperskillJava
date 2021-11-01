package amazingNumber;

import java.util.Scanner;

public class PropertiesOfNumber {
    public static void main(String[] args) {
        // write your code here


        System.out.println("Welcome to Amazing Numbers!");
        System.out.println(" ");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter 0 to exit.");
        System.out.println(" ");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a request: ");
        int num = input.nextInt();


        String newNumToString = Integer.toString(num);

        while (true) {

            char singleLetter;
            boolean even = false;
            boolean odd = false;
            boolean buzz = false;
            boolean duck = false;
            boolean palindromic = false;

            if (num >0) {
                // odd or even
                if(num % 2 == 0) {
                    even = true;
                } else {
                    odd = true;
                }

                // buzz number??
                if (num % 7 ==0 || num % 10 == 7) {
                    buzz = true;
                }

                // check duck number
                for(int i = 0; i < newNumToString.length(); i++) {
                    singleLetter = newNumToString.charAt(i);
                    if (singleLetter == '0') {
                        duck = true;
                    }
                }

                // check palindromic

                StringBuilder sb = new StringBuilder(newNumToString);
                if (newNumToString.equals(sb.reverse().toString())) {
                    palindromic = true;
                }

                // print main
                System.out.println("Properties of " + num);
                System.out.println("        even: " + even);
                System.out.println("        odd: " + odd);
                System.out.println("        buzz: " + buzz);
                System.out.println("        duck: " + duck);
                System.out.println("        palindromic: " + palindromic);
                System.out.print("Enter a request: ");
                num = input.nextInt();
            } else if (num < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                System.out.print("Enter a request: ");
                num = input.nextInt();
            } else {
                System.out.println("Goodbye!");
                break;
            }
        }


    }
}
