package controller;

public class AppController {
    private final ZakatService zakatService = new ZakatService();
    private final ConsoleHelper consoleHelper = new ConsoleHelper();

    private final MainScreen mainScreen = new MainScreen();
    private final InputScreen inputScreen = new InputScreen();
    private final MenuScreen menuScreen = new MenuScreen();
    private final ResultScreen resultScreen = new ResultScreen();

    public void run() {
         // **Welcome Screen**
        consoleHelper.clear();
        mainScreen.showGreeting();
        consoleHelper.pause();//?


         boolean shouldContinue;
        do {
        // **Read number of family members**
        consoleHelper.clear();
        inputScreen.show();
        int familyMembers = consoleHelper.readNumber();

        // **Show food category menu**
        consoleHelper.clear();
        menuScreen.show();
        int choice = consoleHelper.readNumberInRange(1, zakatService.getFoodList().size());

        // **Get kg-per-sa3 for chosen food **
        double kgPerSa3 = zakatService.getFoodList().get(choice - 1).getKgPerSa3();

        // ** Calculate total zakat weight **
        double totalKg = zakatService.calculateZakat(familyMembers, kgPerSa3);

        // ** Show result **
        consoleHelper.clear();
        resultScreen.show(totalKg);

        // ** Try again? **

       System.out.print("Do you want to try again? (1: Yes / 2: No): ");
       shouldContinue = (consoleHelper.readNumberInRange(1, 2) == 1);

        
        }while(shouldContinue);
}