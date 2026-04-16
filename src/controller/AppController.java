package controller;

import service.ZakatService;
import console.ConsoleHelper;
import ui.*;
import data.FoodData;

public class AppController {

    public static void run() {
        showWelcomeScreen();
        boolean shouldContinue;
        do {
            processZakatCycle();
            System.out.println();
            System.out.print("Do you want to try again? (1: Yes / 2: No): ");
            shouldContinue = (ConsoleHelper.readIntInRange(1, 2) == 1);
        } while (shouldContinue);
    }

    private static void showWelcomeScreen() {
        // ** welcome screen **
        ConsoleHelper.clear();
        MainScreen.show();
        ConsoleHelper.pause();
    }

    private static void processZakatCycle() {
        // ** Read number of family members **
        ConsoleHelper.clear();
        InputScreen.show();
        int familyMembers = ConsoleHelper.readInt();

        // ** show food category menu **
        ConsoleHelper.clear();
        MenuScreen.show();
        int choice = ConsoleHelper.readIntInRange(1, FoodData.getList().size());
        // ** Get kg per person chosen food **
        double kgPerPerson = FoodData.getList().get(choice - 1).kiloPerSa3;

        // **Calculate total zakat weight**
        double totalKg = ZakatService.calculate(familyMembers, kgPerPerson);

        // ** Show result **
        ConsoleHelper.clear();
        ResultScreen.show(totalKg);
    }
}