import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the first act in the game.
 * Manages the game map, available equipment, and the shop for the player to interact with.
 */
public class ActOne {
    private GameMap gameMap;
    private List<Equipment> equipmentList;
    private Shop shop;
    private List<Equipment> shopItemPool;

    /**
     * Constructor for ActOne. Initializes the game map, equipment list, and shop.
     */
    public ActOne() {
        Map<String, List<String>> map = new HashMap<>();
        // Initializes the map with connections between different locations.
        map.put("Ashen Field", List.of("The Emberwood", "Wailing Hollow", "Cinderfall Cliffs"));
        map.put("The Emberwood", List.of("Wailing Hollow", "Molten Mire", "Ashen Field"));
        map.put("Wailing Hollow", List.of("Ashen Field", "The Shivering Rift", "Molten Mire"));
        map.put("Cinderfall Cliffs", List.of("The Shivering Rift", "Ashen Field", "The Emberwood"));
        map.put("The Shivering Rift", List.of("Wailing Hollow", "Molten Mire", "Cinderfall Cliffs"));
        map.put("Molten Mire", List.of("The Emberwood", "Wailing Hollow", "The Shivering Rift"));

        // Sets the initial location of the player on the map
        this.gameMap = new GameMap(map, "Ashen Field");

        // Initializes the available equipment in the act
        this.equipmentList = List.of(
                new Equipment("Boots of Shimmer", 0, 2, 5, 100),
                new Equipment("Helmet of Lana", 0, 5, 0, 150),
                new Equipment("Chestplate of Heracles", 2, 3, 0, 200),
                new Equipment("Pants of Plague Matter", 0, 2, 0, 120),
                new Equipment("Amulet of Light", 0, 0, 3, 50),
                new Equipment("Sword of Kratos", 4, 0, 0, 300)
        );

        // Initializes the shop item pool with various items available for purchase or sell
        this.shopItemPool = List.of(
                new Equipment("Mystic Boots", 1, 2, 2, 120),
                new Equipment("Titanium Helmet", 0, 7, 0, 180),
                new Equipment("Hellspawn Gloves", 5, 5, 0, 250),
                new Equipment("Stealth Pants", 0, 3, 1, 100),
                new Equipment("Amulet of Darkness", 0, 0, 4, 200),
                new Equipment("Excalibur Sword", 6, 0, 0, 350),
                new Equipment("Shadow Boots", 1, 1, 3, 110),
                new Equipment("Golden Helmet", 0, 8, 0, 190),
                new Equipment("Infernal Chestplate", 4, 6, 0, 300),
                new Equipment("Frostbound Sword", 7, 0, 0, 400),
                new Equipment("Phantom Amulet", 0, 0, 5, 220),
                new Equipment("Thief's Pants", 0, 2, 2, 90),
                new Equipment("Knight's Boots", 2, 3, 1, 150),
                new Equipment("Dragon Helmet", 0, 10, 0, 350),
                new Equipment("Crimson Chestplate", 5, 7, 0, 320),
                new Equipment("Sword of Whispers", 8, 0, 0, 450),
                new Equipment("Moonlit Amulet", 0, 0, 6, 250),
                new Equipment("Assassin's Pants", 0, 4, 3, 130),
                new Equipment("Wanderer’s Boots", 2, 1, 2, 100),
                new Equipment("Runed Helmet", 0, 6, 1, 170),
                new Equipment("Guardian Chestplate", 3, 9, 0, 280),
                new Equipment("Stormbringer Sword", 9, 0, 0, 500),
                new Equipment("Celestial Amulet", 0, 0, 8, 300),
                new Equipment("Traveler’s Pants", 0, 2, 1, 80),
                new Equipment("Sorcerer’s Boots", 1, 2, 4, 140),
                new Equipment("Ancient Helmet", 0, 12, 0, 400),
                new Equipment("Warrior’s Chestplate", 6, 8, 0, 360),
                new Equipment("Cursed Sword", 10, 0, -2, 520),
                new Equipment("Lightbringer Amulet", 0, 0, 7, 280),
                new Equipment("Rogue’s Pants", 0, 3, 2, 120),
                new Equipment("Vindicator Boots", 3, 4, 1, 180)
        );

        // Initializes the shop with the item pool
        this.shop = new Shop(this.shopItemPool);
    }

    /**
     * Gets the list of equipment available in Act One.
     *
     * @return a list of Equipment objects
     */
    public List<Equipment> getEquipmentList() {
        return this.equipmentList;
    }

    /**
     * Gets the game map for Act One.
     *
     * @return the GameMap object
     */
    public GameMap getGameMap() {
        return this.gameMap;
    }

    /**
     * Allows the player to visit the shop and interact with available items.
     * The player can buy, sell, or buy back items in the shop.
     *
     * @param player the Player object interacting with the shop
     */
    public void visitShop(Player player) {
        while (true) {
            System.out.println("\nWelcome to the shop! What would you like to do?");
            System.out.println("1. Buy Items");
            System.out.println("2. Sell Items");
            System.out.println("3. Buy Back Items");
            System.out.println("4. Leave the Shop");

            // Read player input for action selection
            int choice = GameLogic.readInt("Enter your choice: ", 4);

            switch (choice) {
                case 1:
                    // Restock shop items and show available items for purchase
                    this.shop.restockShop();
                    this.shop.showShopItems();

                    // Allow the player to choose an item to buy
                    int buyChoice = GameLogic.readInt("Which item would you like to buy? (Enter number or 6 to cancel): ", 6);
                    if (buyChoice == 6) {
                        System.out.println("You decided not to buy anything.");
                        break;
                    }

                    // Attempt to purchase the selected item
                    this.shop.buyItem(player, buyChoice);
                    break;
                case 2:
                    // Display inventory and allow selling items
                    List<String> inventory = player.getInventory();
                    if (inventory.isEmpty()) {
                        System.out.println("You have no items to sell.");
                    } else {
                        System.out.println("Your inventory:");

                        // Show items in the inventory with their stats
                        for (int i = 0; i < inventory.size(); ++i) {
                            String itemName = inventory.get(i);
                            Equipment selectedItem = player.getSelectedItem(i + 1);
                            if (selectedItem != null && !itemName.contains("Key")) {
                                System.out.println(i + 1 + ". " + itemName + " (Strength: " + selectedItem.getStrengthBoost() + ", Defense: " + selectedItem.getDefenseBoost() + ", Speed: " + selectedItem.getSpeedBoost() + ", Price: " + (double) selectedItem.getPrice() * (double) 0.5F + ")");
                            }
                        }

                        // Allow the player to select an item to sell
                        int sellChoice = GameLogic.readInt("Which item would you like to sell? (Enter number or " + (inventory.size() + 1) + " to cancel): ", inventory.size() + 1);
                        if (sellChoice == inventory.size() + 1) {
                            System.out.println("You decided not to sell anything.");
                            continue;
                        }

                        // Attempt to sell the selected item
                        this.shop.sellItem(player, sellChoice);
                    }
                    break;
                case 3:
                    // Show available items for buyback
                    this.shop.showBuybackItems();
                    int buyBackChoice = GameLogic.readInt("Which item would you like to buy back? (Enter number or " + (this.shop.getBuybackInventorySize() + 1) + " to cancel): ", this.shop.getBuybackInventorySize() + 1);
                    if (buyBackChoice == this.shop.getBuybackInventorySize() + 1) {
                        System.out.println("You decided not to buy back anything.");
                        break;
                    }

                    // Attempt to buy back the selected item
                    this.shop.buyBackItem(player, buyBackChoice);
                    break;
                case 4:
                    // Exit the shop
                    System.out.println("You leave the shop. Come back anytime!");
                    GameLogic.anythingToContinue();
                    return;
                default:
                    // Handle invalid input
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
