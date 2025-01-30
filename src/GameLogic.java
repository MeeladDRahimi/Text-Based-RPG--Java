import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GameLogic {
    static Scanner scanner;
    static Player player;
    static GameMap gameMap;
    static Set<String> defeatedBastions;
    private static boolean playerHasDefeatedBoundBoss;
    static GameState currentGameState;
    private static int currentAct;
    static GameState titleGameState;
    public static boolean isRunning;

    public GameLogic() {
    }

    public static int readInt(String prompt, int userChoice) {
        int input;
        do {
            System.out.println(prompt);

            try {
                input = Integer.parseInt(scanner.next());
            } catch (Exception var4) {
                input = -1;
                System.out.println("Please enter an integer!");
            }
        } while(input < 1 || input > userChoice);

        return input;
    }

    public static void clearConsole() {
        for(int i = 0; i < 100; ++i) {
            System.out.println();
        }

    }

    public static void printSeperator(int n) {
        for(int i = 0; i < n; ++i) {
            System.out.print("-");
        }

        System.out.println();
    }

    public static void printHeading(String title) {
        printSeperator(30);
        System.out.println(title);
        printSeperator(30);
    }

    public static void anythingToContinue() {
        System.out.println("\nEnter anything to continue...");
        scanner.next();
    }

    public static void startGame() {
        currentGameState = GameSaver.loadGame("gameSave.dat");
        if (currentGameState != null) {
            System.out.println("Welcome back, " + currentGameState.getPlayerName() + "!");
            anythingToContinue();
            player = new Player(currentGameState.getPlayerName(), true);
            player.setLevel(currentGameState.getPlayerLevel());
            currentAct = currentGameState.getCurrentAct();
            playerHasDefeatedBoundBoss = currentGameState.hasDefeatedBoundBoss();
            defeatedBastions = currentGameState.getDefeatedBastions();
            player.setXp(currentGameState.getPlayerXp());
            player.setHp(currentGameState.getPlayerHp());
            player.setMaxHp(currentGameState.getPlayerMaxHp());
            player.setDefense(currentGameState.getPlayerDefense());
            player.setStrength(currentGameState.getPlayerStrength());
            player.setSpeed(currentGameState.getPlayerSpeed());
            player.addCurrency(currentGameState.getBrim());
            player.setEquippedItems(currentGameState.getEquippedItems());
            player.setInventory(currentGameState.getInventory());
            player.setKeyItems(currentGameState.getKeyItems());
            player.setNumBastionKeys(currentGameState.getNumBastionKeys());
            player.numAtkUpgrades = currentGameState.getNumAtkUpgrades();
            player.numDefUpgrades = currentGameState.getNumDefUpgrades();
            player.atkUpgrades = currentGameState.getAtkUpgrades();
            player.defUpgrades = currentGameState.getDefUpgrades();
            System.out.println(getCurrentAct());
        } else {
            startNewGame();
        }

        isRunning = true;
        gameLoop();
    }

    public static void saveAndQuit() {
        currentGameState = new GameState(player, getCurrentAct(), playerHasDefeatedBoundBoss);
        GameSaver.saveGame(currentGameState, "gameSave.dat");
        System.out.println("Game saved. Exiting...");
        isRunning = false;
    }

    public static void characterInfo() {
        clearConsole();
        Story.boldText();
        printHeading("\t\tCHARACTER INFO");
        PrintStream var10000 = System.out;
        String var10001 = player.getName();
        var10000.println(var10001 + "\tLevel: " + player.getLevel() + "\tBrim: " + player.getBrim());
        printSeperator(30);
        Story.resetTextColor();
        var10000 = System.out;
        int var4 = player.getXp();
        var10000.println("XP: " + var4 + "/" + player.getXpToLevel(player.getLevel()));
        var10000 = System.out;
        var4 = player.getHp();
        var10000.println("HP: " + var4 + "/" + player.getMaxHp());
        var10000 = System.out;
        var4 = player.getStrength();
        var10000.println("Strength: " + var4 + "\t(" + player.getEffectiveDamage() + ")\nDefense: " + player.getDefense() + "\t(" + player.getEffectiveDefense() + ")");
        var10000 = System.out;
        var4 = player.getSpeed();
        var10000.println("Speed: " + var4 + "\t(" + player.getEffectiveSpeed() + ")");
        if (player.numAtkUpgrades > 0) {
            System.out.println("Offensive traits: " + player.atkUpgrades[player.numAtkUpgrades - 1]);
        }

        if (player.numDefUpgrades > 0) {
            System.out.println("Defensive traits: " + player.defUpgrades[player.numDefUpgrades - 1]);
        }

        anythingToContinue();
    }

    public static void printMenu() {
        clearConsole();
        Story.boldText();
        String var10000 = currentGameState.getPlayerName();
        printHeading(var10000 + " - Location: " + gameMap.getCurrRegionName());
        System.out.println("Choose an action:");
        printSeperator(30);
        Story.resetTextColor();
        System.out.println("(1) Travel");
        System.out.println("(2) Character Info");
        System.out.println("(3) Level UP");
        System.out.println("(4) Enter " + gameMap.getCurrRegionName() + "'s Bastion");
        System.out.println("(5) Equipment");
        System.out.println("(6) Shop");
        System.out.println("(7) Check Attained Key(s)");
        System.out.println("(8) Travel to Bound Gate...");
        if (currentAct == 4) {
            System.out.println("(9) Fight the Lord of Hell");
            System.out.println("(10) Save and Quit");
        } else {
            System.out.println("(9) Save and Quit");
        }

    }

    public static void gameLoop() {
        if (getCurrentAct() == 1) {
            ActOne actOne = new ActOne();
            gameMap = actOne.getGameMap();
            List<Equipment> equipmentList = actOne.getEquipmentList();
            boolean actOneCompleted = false;
            boolean actTwoCompleted = false;
            boolean actThreeCompleted = false;

            while(isRunning && currentAct == 1) {
                printMenu();
                int input = readInt("-> ", 9);
                if (input == 1) {
                    gameMap.travel();
                } else if (input == 2) {
                    characterInfo();
                } else if (input == 3) {
                    player.levelUp();
                } else if (input == 4) {
                    enterBastion(equipmentList);
                } else if (input == 5) {
                    manageEquipment();
                } else if (input == 6) {
                    actOne.visitShop(player);
                } else if (input == 7) {
                    checkKeys();
                } else if (input == 8) {
                    travelToBoundGate();
                    if (playerHasDefeatedBoundBoss) {
                        actOneCompleted = true;
                        startActTwo();
                    }
                } else if (input == 9) {
                    saveAndQuit();
                    break;
                }
            }
        }

        isRunning = true;
        if (getCurrentAct() == 2) {
            ActTwo actTwo = new ActTwo();
            currentAct = 2;
            gameMap = actTwo.getGameMap();
            List equipmentList = actTwo.getEquipmentList();
            boolean actOneCompleted = false;
            boolean actTwoCompleted = false;

            while(isRunning && currentAct == 2) {
                printMenu();
                int input = readInt("-> ", 9);
                if (input == 1) {
                    gameMap.travel();
                } else if (input == 2) {
                    characterInfo();
                } else if (input == 3) {
                    player.levelUp();
                } else if (input == 4) {
                    enterBastion(equipmentList);
                } else if (input == 5) {
                    manageEquipment();
                } else if (input == 6) {
                    actTwo.visitShop(player);
                } else if (input == 7) {
                    checkKeys();
                } else if (input == 8) {
                    travelToBoundGate();
                    if (playerHasDefeatedBoundBoss) {
                        actTwoCompleted = true;
                        startActThree();
                    }
                } else if (input == 9) {
                    saveAndQuit();
                    break;
                }
            }
        }

        isRunning = true;
        if (getCurrentAct() == 3) {
            ActThree actThree = new ActThree();
            currentAct = 3;
            gameMap = actThree.getGameMap();
            List equipmentList = actThree.getEquipmentList();
            boolean actTwoCompleted = false;
            boolean actThreeCompleted = false;

            while(isRunning && currentAct == 3) {
                printMenu();
                int input = readInt("-> ", 9);
                if (input == 1) {
                    gameMap.travel();
                } else if (input == 2) {
                    characterInfo();
                } else if (input == 3) {
                    player.levelUp();
                } else if (input == 4) {
                    enterBastion(equipmentList);
                } else if (input == 5) {
                    manageEquipment();
                } else if (input == 6) {
                    actThree.visitShop(player);
                } else if (input == 7) {
                    checkKeys();
                } else if (input == 8) {
                    travelToBoundGate();
                    if (playerHasDefeatedBoundBoss) {
                        actThreeCompleted = true;
                        startActFour();
                    }
                } else if (input == 9) {
                    saveAndQuit();
                    break;
                }
            }
        }

        isRunning = true;
        if (getCurrentAct() == 4) {
            ActFour actFour = new ActFour();
            currentAct = 4;
            gameMap = actFour.getGameMap();
            List equipmentList = actFour.getEquipmentList();
            boolean finalBossDefeated = false;
            boolean actThreeCompleted = false;

            while(isRunning && currentAct == 4) {
                printMenu();
                int input = readInt("-> ", 10);
                if (input == 1) {
                    gameMap.travel();
                } else if (input == 2) {
                    characterInfo();
                } else if (input == 3) {
                    player.levelUp();
                } else if (input == 4) {
                    enterBastion(equipmentList);
                } else if (input == 5) {
                    manageEquipment();
                } else if (input == 6) {
                    actFour.visitShop(player);
                } else if (input == 7) {
                    checkKeys();
                } else if (input == 8) {
                    travelToBoundGate();
                } else if (input != 9) {
                    if (input == 10) {
                        saveAndQuit();
                    }
                } else {
                    List<String> keys = player.getKey();
                    boolean hasLordKey = false;

                    for(String key : keys) {
                        if (key.contains("Lord")) {
                            hasLordKey = true;
                            break;
                        }
                    }

                    if (!hasLordKey) {
                        System.out.println(player.getName() + ": I need to find a key...");
                    }

                    if (hasLordKey) {
                        System.out.println("You use the Bound Key to unlock the gate.");
                        System.out.println("Prepare to fight the Lord of Hell!");
                        FinalEnemy finalBoss = new FinalEnemy();
                        new BattleSequence(player, finalBoss);
                        anythingToContinue();
                        if (finalBoss.getHp() <= 0) {
                            clearConsole();
                            finalBoss.switchToStageTwo();
                            new BattleSequence(player, finalBoss);
                            anythingToContinue();
                            clearConsole();
                            if (finalBoss.getHp() <= 0) {
                                player.removeLordInventoryKeys();
                                Story.endGameDialogue();
                            }
                        }
                    } else {
                        System.out.println("You must defeat the bound boss to reach the Lord of Hell");
                        anythingToContinue();
                    }
                }
            }
        }

    }

    private static void resetBoundBossStatus() {
        playerHasDefeatedBoundBoss = false;
    }

    private static void startActTwo() {
        defeatedBastions.clear();
        player.resetBastionKey();
        resetBoundBossStatus();
        System.out.println("Act 1 is complete! The journey continues to Act 2...");
        Story.ActTwoStory();
        currentAct = 2;
        anythingToContinue();
    }

    private static void startActThree() {
        defeatedBastions.clear();
        player.resetBastionKey();
        resetBoundBossStatus();
        System.out.println("Act 2 is complete! The journey continues to Act 3...");
        Story.ActThreeStory();
        currentAct = 3;
        anythingToContinue();
    }

    private static void startActFour() {
        defeatedBastions.clear();
        player.resetBastionKey();
        resetBoundBossStatus();
        System.out.println("Act 3 is complete! The journey continues to Act 4...");
        Story.ActFourStory();
        currentAct = 4;
        anythingToContinue();
    }

    private static void enterBastion(List<Equipment> equipmentList) {
        String currentBastion = gameMap.getCurrRegionName();
        if (defeatedBastions.contains(currentBastion)) {
            System.out.println("It's kinda empty in here.");
            anythingToContinue();
        } else {
            int numEnemies = 4;
            System.out.println("Entering " + gameMap.getCurrRegionName() + "'s Bastion...");

            while(player.getHp() > 0 && numEnemies > 0) {
                for(int i = numEnemies; i > 0; --i) {
                    Enemy enemy = new Enemy();
                    new BattleSequence(player, enemy);
                    if(player.getHp() <= 0){
                        anythingToContinue();
                        return;
                    }
                    if(enemy.getHp() > 0){
                        return;
                    }
                    --numEnemies;
                    player.setXp((int)((double)50.0F * ((double)1.0F + (double)currentAct * 0.2)));
                    player.addCurrency((int)((double)25.0F * ((double)1.0F + (double)currentAct * 0.2)));
                }
            }

            if (numEnemies == 0) {
                System.out.println("A key fragment shimmers in the shadows...");
                BastionBoss bastionBoss = new BastionBoss();
                new BattleSequence(player, bastionBoss);
                if (bastionBoss.getHp() > 0) {
                    System.out.println("You suck");
                    anythingToContinue();
                    return;
                }

                Equipment droppedItem = (Equipment)equipmentList.get(defeatedBastions.size());
                player.addToInventory(droppedItem);
                player.addToKey(new Equipment("Key Fragment - " + currentBastion, 0, 0, 0, 0));
                player.addBastionKey();
                player.setXp((int)((double)125.0F * ((double)1.0F + (double)currentAct * 0.2)));
                player.addCurrency(75);
                anythingToContinue();
            }

            defeatedBastions.add(currentBastion);
            System.out.println("You defeated the " + gameMap.getCurrRegionName() + " Bastion!");
            anythingToContinue();
        }
    }

    public static void manageEquipment() {
        clearConsole();
        boolean exit = false;

        while(!exit) {
            List<String> inventory = player.getInventory();
            List<String> equippedItems = player.getEquippedItems();
            Story.boldText();
            printSeperator(60);
            System.out.println("\t\t\t\t\t\tEQUIPMENT");
            printSeperator(60);
            Story.resetTextColor();

            for(int i = 0; i < inventory.size(); ++i) {
                String item = (String)inventory.get(i);
                Equipment selectedItem = player.getSelectedItem(i + 1);
                if (selectedItem != null && !item.contains("Key")) {
                    System.out.println(i + 1 + ". " + item + " (Strength: " + selectedItem.getStrengthBoost() + ", Defense: " + selectedItem.getDefenseBoost() + ", Speed: " + selectedItem.getSpeedBoost() + ")");
                }
            }

            for(int i = 0; i < equippedItems.size(); ++i) {
                String equippedItem = (String)equippedItems.get(i);
                Equipment selectedItem = player.getSelectedItem(inventory.size() + i + 1);
                if (selectedItem != null && !equippedItem.contains("Key")) {
                    PrintStream var10000 = System.out;
                    int var10001 = inventory.size() + i + 1;
                    var10000.println(var10001 + ". " + selectedItem.getName() + " (Equipped) (Strength: " + selectedItem.getStrengthBoost() + ", Defense: " + selectedItem.getDefenseBoost() + ", Speed: " + selectedItem.getSpeedBoost() + ")");
                }
            }

            System.out.println("Choose an item to equip or unequip, or press 0 to exit.");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) {
                exit = true;
                System.out.println("Exiting Inventory...");
                anythingToContinue();
                break;
            }

            if (choice >= 0 && choice <= inventory.size()) {
                Equipment selectedItem = player.getSelectedItem(choice);
                if (selectedItem != null && selectedItem.getName().toLowerCase().contains("key")) {
                    System.out.println("You cannot equip or unequip key items.");
                    anythingToContinue();
                    continue;
                }

                if (selectedItem != null && selectedItem.isEquipped()) {
                    System.out.println(selectedItem.getName() + " is already equipped.");
                    System.out.println("Would you like to unequip it? (Y/N)");
                    String unequipChoice = scanner.nextLine();
                    if (unequipChoice.equalsIgnoreCase("Y")) {
                        player.unequipItem(selectedItem);
                        System.out.println(selectedItem.getName() + " has been unequipped.");
                        anythingToContinue();
                    }
                } else if (selectedItem != null) {
                    player.equipItem(selectedItem);
                    System.out.println(selectedItem.getName() + " has been equipped.");
                    anythingToContinue();
                }
            } else if (choice > inventory.size() && choice <= inventory.size() + equippedItems.size()) {
                Equipment selectedItem = player.getSelectedItem(choice);
                if (selectedItem != null && selectedItem.getName().toLowerCase().contains("key")) {
                    System.out.println("You cannot equip or unequip key items.");
                    anythingToContinue();
                    continue;
                }

                if (selectedItem != null && selectedItem.isEquipped()) {
                    player.unequipItem(selectedItem);
                    System.out.println(selectedItem.getName() + " has been unequipped.");
                    anythingToContinue();
                }
            } else {
                System.out.println("Invalid choice! Please select a valid item.");
            }

            exit = true;
            System.out.println("Exiting menu...");
            anythingToContinue();
        }

    }

    public static void checkKeys() {
        List<String> keys = player.getKey();
        boolean hasKeys = false;
        Story.boldText();
        printHeading("\t\tKEY ITEMS");
        Story.resetTextColor();

        for(String item : keys) {
            if (item.contains("Key")) {
                System.out.println(item);
                hasKeys = true;
            }
        }

        if (!hasKeys) {
            System.out.println("You don't have any key items.");
        }

        anythingToContinue();
    }

    public static void travelToBoundGate() {
        List<String> keys = player.getKey();
        boolean hasBoundKey = false;

        for(String key : keys) {
            if (key.contains("Bound Key")) {
                hasBoundKey = true;
                break;
            }
        }

        if (hasBoundKey) {
            System.out.println("You use the Bound Key to unlock the gate.");
            BoundBoss boundBoss = new BoundBoss();
            new BattleSequence(player, boundBoss);
            if (boundBoss.getHp() <= 0) {
                if (currentAct == 1) {
                    Equipment droppedItem = new Equipment("Hellish Bound Pants", 6, 6, 6, 2500);
                    player.addToInventory(droppedItem);
                }

                if (currentAct == 2) {
                    Equipment droppedItem = new Equipment("Hellish Bound Chestplate", 9, 6, 6, 2500);
                    player.addToInventory(droppedItem);
                }

                if (currentAct == 3) {
                    Equipment droppedItem = new Equipment("Hellish Bound Boots", 12, 9, 9, 2500);
                    player.addToInventory(droppedItem);
                }

                if (currentAct == 4) {
                    Equipment droppedItem = new Equipment("Hellish Bound Helmet", 15, 12, 12, 2500);
                    player.addToInventory(droppedItem);
                    player.addToKey(new Equipment("Key to Lord of Hell's Hall", 0, 0, 0, 0));
                }

                player.setXp(500);
                player.addCurrency(200);
                System.out.println("You have defeated the Bound Boss and received your rewards!");
                playerHasDefeatedBoundBoss = true;
                player.removeBoundInventoryKeys();
            } else {
                System.out.println("You were defeated by the Bound Boss. Better luck next time!");
            }

            anythingToContinue();
        } else {
            System.out.println("You do not have a Bound Key to open the gate.");
            anythingToContinue();
        }

    }

    public static int getCurrentAct() {
        return currentAct;
    }

    private static List<Equipment> generateDefaultShopPool() {
        List<Equipment> defaultItemPool = new ArrayList();
        defaultItemPool.add(new Equipment("Iron Sword", 50, 10, 0, 0));
        defaultItemPool.add(new Equipment("Leather Shield", 40, 0, 5, 0));
        defaultItemPool.add(new Equipment("Boots of Speed", 30, 0, 0, 5));
        return defaultItemPool;
    }

    public static void showTitleScreen() {
        titleGameState = GameSaver.loadGame("gameSave.dat");
        clearConsole();
        if (titleGameState != null) {
            Story.boldRedTextColor();
            printHeading(titleGameState.getPlayerName().toUpperCase() + "'S INFERNO");
            Story.resetTextColor();
            System.out.println("By: Meelad Rahimi");
            System.out.println("(1) Load Game");
            System.out.println("(2) New Game");
            int choice = readInt("-> ", 2);
            if (choice == 1) {
                clearConsole();
                startGame();
            } else if (choice == 2) {
                System.out.println("\u001b[4mAre You Sure?");
                Story.resetTextColor();
                System.out.println("(1) Yes");
                System.out.println("(2) No");
                int choice1 = readInt("-> ", 2);
                if (choice1 == 1) {
                    deleteSaveFile();
                    startGame();
                } else if (choice1 == 2) {
                    showTitleScreen();
                }
            }
        } else {
            startGame();
        }

    }

    public static void deleteSaveFile() {
        String saveFilePath = "gameSave.dat";
        File saveFile = new File(saveFilePath);
        if (saveFile.exists()) {
            if (saveFile.delete()) {
                System.out.println("Save file deleted. Starting a new game...");
            } else {
                System.out.println("Failed to delete save file. Please try again.");
            }
        } else {
            System.out.println("No save file found. Starting a new game...");
        }

    }

    public static void startNewGame() {
        Story.printIntroPartOne();
        String name = scanner.next();
        boolean isName = false;

        while(!isName) {
            System.out.println("Your name is " + name + ", right?");
            System.out.println("(1.) Yes");
            System.out.println("(2.) No");
            int choice = readInt("-> ", 2);
            if (choice == 1) {
                isName = true;
            } else {
                System.out.println("Oh...whats your name then?");
                name = scanner.next();
            }
        }

        player = new Player(name, false);
        defeatedBastions = new HashSet();
        currentGameState = new GameState(player, 1, false);
        Story.printIntroPartTwo();
        Story.ActOneStory();
    }

    public static GameState getCurrentGameState() {
        return currentGameState;
    }

    static {
        scanner = new Scanner(System.in);
        defeatedBastions = new HashSet();
        playerHasDefeatedBoundBoss = false;
        currentAct = 1;
    }
}
