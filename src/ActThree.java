/**
 * Represents Act Three of the game, which includes a game map, equipment, and a shop.
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActThree {
    private GameMap gameMap;
    private List<Equipment> equipmentList;
    private Shop shop;
    private List<Equipment> shopItemPool;

    /**
     * Constructs Act Three, initializing the game map, equipment list, and shop.
     *
     * Preconditions: None
     * Postconditions: Game map, equipment list, and shop are initialized.
     */
    public ActThree() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("The Cinderscape", List.of("Lavaheart Basin", "Fiendish Depths", "The Scorched Abyss"));
        map.put("Lavaheart Basin", List.of("The Cinderscape", "Fiendish Depths", "Demonforge Citadel"));
        map.put("Fiendish Depths", List.of("The Cinderscape", "Lavaheart Basin", "The Scorched Abyss"));
        map.put("The Scorched Abyss", List.of("The Cinderscape", "Fiendish Depths", "Demonforge Citadel"));
        map.put("Demonforge Citadel", List.of("Lavaheart Basin", "The Scorched Abyss", "The Infernal Crucible"));
        map.put("The Infernal Crucible", List.of("The Scorched Abyss", "Demonforge Citadel", "The Cinderscape"));
        this.gameMap = new GameMap(map, "The Cinderscape");

        this.equipmentList = List.of(
                new Equipment("Boots of the Scorching", 0, 4, 10, 177),
                new Equipment("Helmet of the Eternal Flame", 0, 10, 0, 267),
                new Equipment("Chestplate of the Infernal King", 4, 5, 0, 356),
                new Equipment("Pants of Lava Fury", 0, 4, 0, 213),
                new Equipment("Amulet of the Inferno", 0, 0, 6, 89),
                new Equipment("Sword of the Abyss", 7, 0, 0, 533)
        );

        this.shopItemPool = List.of(
                new Equipment("Molten Boots", 1, 4, 4, 213),
                new Equipment("Demon's Helmet", 0, 12, 0, 320),
                new Equipment("Hellspawn Chains", 9, 9, 0, 444),
                new Equipment("Phantom Pants", 0, 5, 3, 177),
                new Equipment("Amulet of the Underworld", 0, 0, 7, 355),
                new Equipment("Doombringer Sword", 10, 0, 0, 622)
        );

        this.shop = new Shop(this.shopItemPool);
    }

    /**
     * Retrieves the equipment list available in Act Three.
     *
     * @return The list of available equipment.
     */
    public List<Equipment> getEquipmentList() {
        return this.equipmentList;
    }

    /**
     * Retrieves the game map for Act Three.
     *
     * @return The game map object.
     */
    public GameMap getGameMap() {
        return this.gameMap;
    }

    /**
     * Allows the player to visit the shop and perform various shop-related actions.
     *
     * @param player The player interacting with the shop.
     *
     * Preconditions: The player must be in an area where the shop is accessible.
     * Postconditions: The player can buy, sell, or buy back items as needed.
     */
    public void visitShop(Player player) {
        while (true) {
            System.out.println("\nWelcome to the shop! What would you like to do?");
            System.out.println("1. Buy Items");
            System.out.println("2. Sell Items");
            System.out.println("3. Buy Back Items");
            System.out.println("4. Leave the Shop");
            int choice = GameLogic.readInt("Enter your choice: ", 4);

            switch (choice) {
                case 1:
                    this.shop.restockShop();
                    this.shop.showShopItems();
                    int buyChoice = GameLogic.readInt("Which item would you like to buy? (Enter number or 6 to cancel): ", 6);
                    if (buyChoice == 6) {
                        System.out.println("You decided not to buy anything.");
                        break;
                    }
                    this.shop.buyItem(player, buyChoice);
                    break;
                case 2:
                    List<String> inventory = player.getInventory();
                    if (inventory.isEmpty()) {
                        System.out.println("You have no items to sell.");
                    } else {
                        System.out.println("Your inventory:");
                        for (int i = 0; i < inventory.size(); ++i) {
                            String itemName = inventory.get(i);
                            Equipment selectedItem = player.getSelectedItem(i + 1);
                            if (selectedItem != null && !itemName.contains("Key")) {
                                System.out.println(i + 1 + ". " + itemName + " (Strength: " + selectedItem.getStrengthBoost() + ", Defense: " + selectedItem.getDefenseBoost() + ", Speed: " + selectedItem.getSpeedBoost() + ", Price: " + selectedItem.getPrice() * 0.5 + ")");
                            }
                        }
                        int sellChoice = GameLogic.readInt("Which item would you like to sell? (Enter number or " + (inventory.size() + 1) + " to cancel): ", inventory.size() + 1);
                        if (sellChoice == inventory.size() + 1) {
                            System.out.println("You decided not to sell anything.");
                            continue;
                        }
                        this.shop.sellItem(player, sellChoice);
                    }
                    break;
                case 3:
                    this.shop.showBuybackItems();
                    int buyBackChoice = GameLogic.readInt("Which item would you like to buy back? (Enter number or " + (this.shop.getBuybackInventorySize() + 1) + " to cancel): ", this.shop.getBuybackInventorySize() + 1);
                    if (buyBackChoice == this.shop.getBuybackInventorySize() + 1) {
                        System.out.println("You decided not to buy back anything.");
                        break;
                    }
                    this.shop.buyBackItem(player, buyBackChoice);
                    break;
                case 4:
                    System.out.println("You leave the shop. Come back anytime!");
                    GameLogic.anythingToContinue();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
