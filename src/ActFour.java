import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActFour {
    private GameMap gameMap;
    private List<Equipment> equipmentList;
    private Shop shop;
    private List<Equipment> shopItemPool;

    public ActFour() {
        Map<String, List<String>> map = new HashMap();
        map.put("The Blazing Chasm", List.of("Hellstorm Arena", "The Blood Furnace", "The Screaming Pit"));
        map.put("Hellstorm Arena", List.of("The Blazing Chasm", "The Blood Furnace", "The Eternal Flame"));
        map.put("The Blood Furnace", List.of("The Blazing Chasm", "Hellstorm Arena", "The Eternal Flame"));
        map.put("The Screaming Pit", List.of("The Blazing Chasm", "The Eternal Flame", "The Gate of Despair"));
        map.put("The Eternal Flame", List.of("Hellstorm Arena", "The Blood Furnace", "The Screaming Pit"));
        map.put("The Gate of Despair", List.of("The Screaming Pit", "The Eternal Flame", "Hellstorm Arena"));
        this.gameMap = new GameMap(map, "The Blazing Chasm");
        this.equipmentList = List.of(new Equipment("Boots of the Infernal Beast", 0, 5, 13, 236),
                new Equipment("Helmet of the Immortal Blaze", 0, 13, 0, 355),
                new Equipment("Chestplate of the Worldfire", 5, 7, 0, 474),
                new Equipment("Pants of Molten Rage", 0, 5, 0, 284),
                new Equipment("Amulet of the Apocalypse", 0, 0, 8, 118),
                new Equipment("Sword of the Eternal Flame", 9, 0, 0, 744));
        this.shopItemPool = List.of(new Equipment("Demon's Fury Boots", 1, 5, 5, 284),
                new Equipment("Helmet of Hell's Lord", 0, 15, 0, 426),
                new Equipment("Hellspawn Armor Plating", 12, 12, 0, 592),
                new Equipment("Spectral Pants", 0, 6, 4, 236),
                new Equipment("Amulet of the Void", 0, 0, 9, 474),
                new Equipment("Sword of Damnation", 13, 0, 0, 844),
                new Equipment("Flamestrider Boots", 2, 6, 6, 300),
                new Equipment("Helmet of Hellfire", 3, 16, 2, 450),
                new Equipment("Dragonlord Chestplate", 13, 14, 0, 680),
                new Equipment("Ghoststep Pants", 1, 7, 6, 270),
                new Equipment("Amulet of Infinite Chaos", 0, 2, 10, 520),
                new Equipment("Sword of Oblivion", 15, 0, 1, 900),
                new Equipment("Hellfire Boots", 3, 5, 7, 310),
                new Equipment("Helmet of Eternal Night", 4, 17, 2, 500),
                new Equipment("Fiery Chestplate", 14, 13, 0, 720),
                new Equipment("Phantomwalker Pants", 2, 8, 5, 280),
                new Equipment("Amulet of Eternal Nightmares", 0, 0, 11, 560),
                new Equipment("Sword of the Void", 16, 0, 1, 970),
                new Equipment("Infernal Strider Boots", 3, 6, 8, 320),
                new Equipment("Helmet of the Devourer", 5, 18, 3, 550),
                new Equipment("Warlord's Serum", 15, 15, 0, 800),
                new Equipment("Pants of the Abyss", 3, 7, 7, 290),
                new Equipment("Amulet of Infinite Flames", 0, 1, 12, 580),
                new Equipment("Dagger of the Eternal Flame", 17, 0, 2, 990),
                new Equipment("Ashwalker Boots", 2, 7, 9, 340),
                new Equipment("Helmet of the Dark Lord", 6, 19, 4, 600),
                new Equipment("Chestplate of Infernal Power", 16, 14, 0, 860),
                new Equipment("Spectral Hunter Pants", 2, 9, 8, 330),
                new Equipment("Amulet of Hell's Fury", 0, 0, 14, 650),
                new Equipment("Void Reaver Sword", 19, 0, 3, 1100));
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
