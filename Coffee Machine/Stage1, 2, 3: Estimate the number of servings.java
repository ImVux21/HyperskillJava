package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void calculateAmountCupsOfCoffee(int[] listMaterials) {
        int availableCups = Math.min(Math.min(listMaterials[0] / 200, listMaterials[1] / 50), listMaterials[2] / 15);
        if (availableCups > listMaterials[3]) {
            System.out.println("Yes, I can make that amount of coffee (and even " + (availableCups - listMaterials[3]) + " more than that)");
        } else if (availableCups == listMaterials[3]) {
            System.out.println("Yes, I can make that amount of coffee ");
        } else if (availableCups < listMaterials[3]) {
            System.out.println("No, I can make only " + availableCups +" cup(s) of coffee");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has:");
        int amountOfWater = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        int amountOfMilk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int amountOfBeans = scanner.nextInt();
        System.out.println("Write how many cups of coffee you will need:");
        int amountOfCupsNeed = scanner.nextInt();
        int[] listMaterials = {amountOfWater, amountOfMilk, amountOfBeans, amountOfCupsNeed};
        calculateAmountCupsOfCoffee(listMaterials);
    }
}
