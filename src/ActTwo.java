import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActTwo {
    private GameMap gameMap;
    private List<Equipment> equipmentList;
    private Shop shop;
    private List<Equipment> shopItemPool;

    public ActTwo() {
        Map<String, List<String>> map = new HashMap();
        map.put("The Crag of Souls", List.of("Hellfire Caverns", "Blighted Ash Wastes", "The Searing Plains"));
        map.put("Hellfire Caverns", List.of("The Crag of Souls", "The Searing Plains", "Infernal Spire"));
        map.put("The Searing Plains", List.of("The Crag of Souls", "Blighted Ash Wastes", "Infernal Spire"));
        map.put("Blighted Ash Wastes", List.of("Hellfire Caverns", "The Crag of Souls", "The Searing Plains"));
        map.put("Infernal Spire", List.of("Hellfire Caverns", "The Searing Plains", "The Inferno Forge"));
        map.put("The Inferno Forge", List.of("Infernal Spire", "The Searing Plains", "Blighted Ash Wastes"));
        this.gameMap = new GameMap(map, "The Crag of Souls");
        this.equipmentList = List.of(new Equipment("Boots of Ember", 0, 3, 7, 133), new Equipment("Helmet of Aegis", 0, 7, 0, 200), new Equipment("Chestplate of Inferno", 3, 4, 0, 267), new Equipment("Pants of Molten Grit", 0, 3, 0, 160), new Equipment("Amulet of Fire", 0, 0, 4, 67), new Equipment("Sword of the Titan", 5, 0, 0, 400));
        this.shopItemPool = List.of(new Equipment("Fiery Boots", 1, 3, 3, 160), new Equipment("Obsidian Helmet", 0, 9, 0, 240), new Equipment("Hellspawn Rings", 7, 7, 0, 333), new Equipment("Extra Stealth Pants", 0, 4, 2, 133), new Equipment("Amulet of the Abyss", 0, 0, 5, 267), new Equipment("Infernal Sword", 8, 0, 0, 467), new Equipment("Glacial Boots", 0, 4, 4, 180), new Equipment("Warlord's Helmet", 2, 10, 0, 290), new Equipment("Shadowweave Chestplate", 5, 10, 2, 400), new Equipment("Sword of the Eclipse", 10, 0, 1, 520), new Equipment("Starlit Amulet", 0, 0, 7, 320), new Equipment("Nightstalker Pants", 1, 5, 5, 220), new Equipment("Lava Boots", 2, 3, 3, 200), new Equipment("Helmet of Titans", 3, 12, 0, 400), new Equipment("Bloodforged Chestplate", 8, 9, 0, 480), new Equipment("Stormrend Sword", 12, 0, 0, 600), new Equipment("Emerald Amulet", 0, 1, 8, 360), new Equipment("Phantom Pants", 1, 6, 4, 240), new Equipment("Hardened Boots", 3, 5, 1, 240), new Equipment("Helmet of Insight", 0, 8, 3, 310), new Equipment("Venom Chestplate", 6, 8, 2, 420), new Equipment("Frostfang Sword", 9, 0, 3, 570), new Equipment("Amulet of Eternity", 0, 0, 9, 400), new Equipment("Assassin's Boots", 2, 2, 6, 280), new Equipment("Crusader's Helmet", 4, 11, 0, 450), new Equipment("Dragon's Chestplate", 9, 11, 0, 520), new Equipment("Riftblade Sword", 13, 0, 0, 700), new Equipment("Timeworn Amulet", 0, 0, 11, 450), new Equipment("Hell-Chain Boots", 3, 3, 5, 300));
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
                                System.out.println(i + 1 + ". " + itemName + " (Strength: " + selectedItem.getStrengthBoost() + ", Defense: " + selectedItem.getDefenseBoost() + ", Speed: " + selectedItem.getSpeedBoost() + ", Price: " + (double)selectedItem.getPrice() * (double)0.5F + ")");
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
