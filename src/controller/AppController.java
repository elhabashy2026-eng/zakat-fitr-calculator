package controller;

import service.ZakatService;
import util.ConsoleHelper;
import ui.*;
import data.FoodData;

public class AppController {

    private final ZakatService zakatService = new ZakatService();
    private final ConsoleHelper consoleHelper = new ConsoleHelper();
    private final MainScreen mainScreen = new MainScreen();
    private final InputScreen inputScreen = new InputScreen();
    private final MenuScreen menuScreen = new MenuScreen();
    private final ResultScreen resultScreen = new ResultScreen();

    public void run() {
        showWelcomeScreen();
        boolean shouldContinue;
        do {
            processZakatCycle();
            System.out.println();
            System.out.print("Do you want to try again? (1: Yes / 2: No): ");
            shouldContinue = (consoleHelper.readIntInRange(1, 2) == 1);
        } while (shouldContinue);
    }

    private void showWelcomeScreen() {
        // ** welcome screen **
        consoleHelper.clear();
        mainScreen.showGreeting();
        consoleHelper.pause();
    }

    private void processZakatCycle() {
        // ** Read number of family members **
        consoleHelper.clear();
        inputScreen.show();
        int familyMembers = consoleHelper.readInt();

        // ** show food category menu **
        consoleHelper.clear();
        menuScreen.show();
        int choice = consoleHelper.readIntInRange(1, FoodData.getList().size());
        // ** Get kg per person chosen food **
        double kgPerPerson = FoodData.getList().get(choice - 1).kiloPerSa3;

        // **Calculate total zakat weight**
        double totalKg = zakatService.calculate(familyMembers, kgPerPerson);

        // ** Show result **
        consoleHelper.clear();
        resultScreen.show(totalKg);
    }
}