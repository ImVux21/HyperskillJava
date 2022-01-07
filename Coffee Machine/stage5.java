package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static boolean checkResource(int[] listMaterial, String option) {
        // check lack of all resources
        if (option.equals("1")) {
            if (listMaterial[0] < 250 && listMaterial[2] < 16 && listMaterial[3] < 1) {
                System.out.println("I have enough resources, making you a coffee!");
                return false;
            }
        } else if (option.equals("2")) {
            if (listMaterial[0] < 350 && listMaterial[1] < 75 && listMaterial[2] < 20 && listMaterial[3] < 1) {
                System.out.println("I have enough resources, making you a coffee!");
                return false;
            }
        } else if (option.equals("3")) {
            if (listMaterial[0] < 200 && listMaterial[1] < 100 && listMaterial[2] < 12 && listMaterial[3] < 1) {
                System.out.println("I have enough resources, making you a coffee!");
                return false;
            }
        }
        // check lack of each material
        if (listMaterial[0] < 250 || listMaterial[0] < 350 || listMaterial[0] < 200) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (listMaterial[1] < 75 || listMaterial[1] < 20) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (listMaterial[2] < 16 || listMaterial[2] < 20 || listMaterial[2] < 12) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        } else if (listMaterial[3] < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
        return true;
    }

    public static void buy(int[] listMaterial) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        String enterChoice = scanner.nextLine();

        if (enterChoice.equals("back")) {
            System.out.print("");
        } else if (checkResource(listMaterial, enterChoice)) {
            if (enterChoice.equals("1")) {
                listMaterial[0] -= 250;
                listMaterial[2] -= 16;
                listMaterial[3]--;
                listMaterial[4] += 4;
            } else if (enterChoice.equals("2")) {
                listMaterial[0] -= 350;
                listMaterial[1] -= 75;
                listMaterial[2] -= 20;
                listMaterial[3]--;
                listMaterial[4] += 7;
            } else if (enterChoice.equals("3")) {
                listMaterial[0] -= 200;
                listMaterial[1] -= 100;
                listMaterial[2] -= 12;
                listMaterial[3]--;
                listMaterial[4] += 6;
            }
        }
    }

    public static void fill(int[] listMaterial) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        int addWater = scanner.nextInt();
        listMaterial[0] += addWater;
        System.out.println("Write how many ml of milk you want to add:");
        int addMilk = scanner.nextInt();
        listMaterial[1] += addMilk;
        System.out.println("Write how many grams of coffee beans you want to add:");
        int addCoffeeBeans = scanner.nextInt();
        listMaterial[2] += addCoffeeBeans;
        System.out.println("Write how many disposable cups of coffee you want to add:");
        int addDisposableCups = scanner.nextInt();
        listMaterial[3] += addDisposableCups;
    }

    public static void take(int[] listMaterial) {
        int charge = listMaterial[4];
        listMaterial[4] = 0;
        System.out.println("I gave you $" + charge);
    }

    public static void remaining(int[] listMaterial) {
        System.out.println("The coffee machine has:\n" +
                listMaterial[0] + " ml of water\n" +
                listMaterial[1] + " ml of milk\n" +
                listMaterial[2] + " g of coffee beans\n" +
                listMaterial[3] + " disposable cups\n" +
                "$" + listMaterial[4] + " of money");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int water = 400, milk = 540, coffeeBeans = 120, disposableCups = 9, money = 550;
        int[] listMaterial = {water, milk, coffeeBeans, disposableCups, money};
        boolean isChecked = true;

        while (isChecked) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String choice = scanner.nextLine();
            switch (choice) {
                case "remaining":
                    remaining(listMaterial);
                    break;
                case "take":
                    take(listMaterial);
                    break;
                case "buy":
                    buy(listMaterial);
                    break;
                case "fill":
                    fill(listMaterial);
                    break;
                case "exit":
                    isChecked = false;
                    break;
            }
        }
    }
}
