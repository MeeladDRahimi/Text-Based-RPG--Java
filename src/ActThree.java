import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActThree {
    private GameMap gameMap;
    private List<Equipment> equipmentList;
    private Shop shop;
    private List<Equipment> shopItemPool;

    public ActThree() {
        Map<String, List<String>> map = new HashMap();
        map.put("The Cinderscape", List.of("Lavaheart Basin", "Fiendish Depths", "The Scorched Abyss"));
        map.put("Lavaheart Basin", List.of("The Cinderscape", "Fiendish Depths", "Demonforge Citadel"));
        map.put("Fiendish Depths", List.of("The Cinderscape", "Lavaheart Basin", "The Scorched Abyss"));
        map.put("The Scorched Abyss", List.of("The Cinderscape", "Fiendish Depths", "Demonforge Citadel"));
        map.put("Demonforge Citadel", List.of("Lavaheart Basin", "The Scorched Abyss", "The Infernal Crucible"));
        map.put("The Infernal Crucible", List.of("The Scorched Abyss", "Demonforge Citadel", "The Cinderscape"));
        this.gameMap = new GameMap(map, "The Cinderscape");
        this.equipmentList = List.of(new Equipment("Boots of the Scorching", 0, 4, 10, 177),
                new Equipment("Helmet of the Eternal Flame", 0, 10, 0, 267),
                new Equipment("Chestplate of the Infernal King", 4, 5, 0, 356),
                new Equipment("Pants of Lava Fury", 0, 4, 0, 213),
                new Equipment("Amulet of the Inferno", 0, 0, 6, 89),
                new Equipment("Sword of the Abyss", 7, 0, 0, 533));
        this.shopItemPool = List.of(new Equipment("Molten Boots", 1, 4, 4, 213),
                new Equipment("Demon's Helmet", 0, 12, 0, 320),
                new Equipment("Hellspawn Chains", 9, 9, 0, 444),
                new Equipment("Phantom Pants", 0, 5, 3, 177),
                new Equipment("Amulet of the Underworld", 0, 0, 7, 355),
                new Equipment("Doombringer Sword", 10, 0, 0, 622),
                new Equipment("Blazing Boots", 2, 4, 5, 230),
                new Equipment("Helmet of Flames", 2, 13, 2, 340),
                new Equipment("Dragon's Chestplate", 10, 11, 0, 460),
                new Equipment("Silent Pants", 1, 6, 4, 210),
                new Equipment("Amulet of the Rift", 0, 1, 8, 380),
                new Equipment("Sword of Fury", 11, 0, 1, 640),
                new Equipment("Inferno Boots", 2, 3, 6, 220),
                new Equipment("Helmet of the Inferno", 3, 14, 1, 390),
                new Equipment("Fiery Chestguard", 11, 12, 0, 500),
                new Equipment("Shadow Pants", 1, 5, 5, 200),
                new Equipment("Amulet of Shadows", 0, 0, 9, 420),
                new Equipment("Sword of Shadows", 12, 0, 0, 680),
                new Equipment("Volcanic Boots", 1, 5, 6, 240),
                new Equipment("Helmet of the Abyss", 0, 13, 1, 360),
                new Equipment("Titanium Chestplate", 12, 10, 0, 480),
                new Equipment("Ghostwalker Pants", 2, 6, 5, 230),
                new Equipment("Amulet of Fire", 0, 0, 10, 450),
                new Equipment("Flameforged Sword", 13, 0, 0, 700),
                new Equipment("Eruption Boots", 2, 4, 6, 250),
                new Equipment("Helmet of Devastation", 3, 15, 2, 400),
                new Equipment("Crimson Chestplate", 11, 13, 1, 520),
                new Equipment("Nightstalker Pants", 1, 7, 5, 240),
                new Equipment("Amulet of Chaos", 0, 1, 11, 460),
                new Equipment("Sword of Eternal Flames", 14, 0, 1, 750));
        this.shop = new Shop(this.shopItemPool);
    }

    public List<Equipment> getEquipmentList() {
        return this.equipmentList;
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }

    public void visitShop(Player player) {
        while(true) {
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

                        for(int i = 0; i < inventory.size(); ++i) {
                            String itemName = (String)inventory.get(i);
                            Equipment selectedItem = player.getSelectedItem(i + 1);
                            if (selectedItem != null && !itemName.contains("Key")) {
                                System.out.println(i + 1 + ". " + itemName + " (Strength: " + selectedItem.getStrengthBoost() + ", Defense: " + selectedItem.getDefenseBoost() + ", Speed: " + selectedItem.getSpeedBoost() + ", Price :" + (double)selectedItem.getPrice() * (double)0.5F + ")");
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
