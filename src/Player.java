import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the player character in the game, extending the base Character class.
 * Includes attributes related to health, strength, defense, speed, and inventory management.
 * Provides methods for leveling up, equipping items, and upgrading stats.
 */
public class Player extends Character implements Serializable {
    private static final long serialVersionUID = 1L;

    // Upgrade progression arrays for attack and defense.
    public int numAtkUpgrades = 0;
    public int numDefUpgrades = 0;
    public String[] atkUpgrades = new String[]{"Brute Strength", "Power", "Might", "Godlike Strength"};
    public String[] defUpgrades = new String[]{"Heavy Bones", "Stoneskin", "Steelskin", "Holy Aura"};

    // Player's inventory, equipped items, and key items.
    private List<Equipment> inventory = new ArrayList();
    private List<Equipment> equippedItems = new ArrayList();
    private List<Equipment> keyItems = new ArrayList();
    private int numBastionKeys = 0;
    private int brimCoin = 0;

    // References to specific equipment slots
    private Equipment boots;
    private Equipment helmet;
    private Equipment chestplate;
    private Equipment pants;
    private Equipment amulet;
    private Equipment sword;

    private int additionalSpeed = 0;  // Additional speed modifier.
    private transient GameLogic gameLogic;  // Reference to the game's logic.

    /**
     * Constructor to initialize the player with a name and an optional choice to skip trait selection.
     *
     * @param name           the player's name.
     * @param skipChooseTrait if true, skips the trait selection.
     */
    public Player(String name, boolean skipChooseTrait) {
        super(name, 40, 40, 0, 10, 5, 10, 0);
        if (!skipChooseTrait) {
            this.chooseTrait();
        }
    }

    /**
     * Allows the player to choose a trait, either attack or defense, based on their current upgrade level.
     */
    public void chooseTrait() {
        GameLogic.clearConsole();
        Story.boldText();
        GameLogic.printHeading("Choose a trait:");
        Story.resetTextColor();

        // Display available upgrade options
        String var10001 = this.atkUpgrades[this.numAtkUpgrades];
        System.out.println("(1) " + var10001);
        var10001 = this.defUpgrades[this.numDefUpgrades];
        System.out.println("(2) " + var10001);

        // User input
        int input = GameLogic.readInt("-> ", 2);
        GameLogic.clearConsole();

        // Apply the chosen upgrade
        if (input == 1) {
            String var10000 = this.atkUpgrades[this.numAtkUpgrades];
            GameLogic.printHeading("You chose " + var10000 + "!");
            ++this.numAtkUpgrades;
        } else {
            String var2 = this.defUpgrades[this.numDefUpgrades];
            GameLogic.printHeading("You chose " + var2 + "!");
            ++this.numDefUpgrades;
        }

        GameLogic.anythingToContinue();
    }

    /**
     * Levels up the player by spending XP and applies stat upgrades.
     */
    public void levelUp() {
        if (this.getXp() >= this.getXpToLevel(this.getLevel())) {
            this.ResetXp(this.getXp(), this.getXpToLevel(this.getLevel()));
            this.upgradeLevel();
            System.out.println("Level Up!");

            // Stat upgrade menu
            for (int i = 5; i > 0; --i) {
                System.out.println("You have " + i + " upgrade tokens.");
                System.out.println("Please select a stat to upgrade:");
                System.out.println("1. Health");
                System.out.println("2. Strength");
                System.out.println("3. Defense");
                System.out.println("4. Speed");
                switch (Character.scanner.next()) {
                    case "1":
                        System.out.println("Health has been upgraded.");
                        this.upgradeHP();
                        break;
                    case "2":
                        System.out.println("Strength has been upgraded.");
                        this.upgradeStrength();
                        break;
                    case "3":
                        System.out.println("Defense has been upgraded.");
                        this.upgradeDefense();
                        break;
                    case "4":
                        System.out.println("Speed has been upgraded.");
                        this.upgradeSpeed();
                        break;
                    default:
                        System.out.println("Incorrect input.");
                        ++i;
                }
            }

            // Allow trait selection every 5 levels
            if (this.getLevel() % 5 == 0) {
                this.chooseTrait();
            }
        } else {
            System.out.println("Insufficient XP to Level UP");
        }
    }

    /**
     * Calculates the player's effective damage based on strength and equipment.
     *
     * @return the effective damage.
     */
    public int getEffectiveDamage() {
        int boost = (int)((double)this.getStrength() * ((double)1.0F + (double)0.25F * (double)this.numAtkUpgrades));

        for (Equipment item : this.equippedItems) {
            if (item != null && item.getStrengthBoost() > 0) {
                boost += item.getStrengthBoost();
            }
        }

        return boost;
    }

    /**
     * Calculates the player's effective defense based on defense and equipment.
     *
     * @return the effective defense.
     */
    public int getEffectiveDefense() {
        int boost = (int)((double)this.getDefense() * ((double)1.0F + (double)0.25F * (double)this.numDefUpgrades));

        for (Equipment item : this.equippedItems) {
            if (item != null && item.getDefenseBoost() > 0) {
                boost += item.getDefenseBoost();
            }
        }

        return boost;
    }

    /**
     * Calculates the player's effective speed based on speed and equipment.
     *
     * @return the effective speed.
     */
    public int getEffectiveSpeed() {
        int boost = this.getSpeed() + this.additionalSpeed;

        for (Equipment item : this.equippedItems) {
            if (item != null && item.getSpeedBoost() > 0) {
                boost += item.getSpeedBoost();
            }
        }

        return boost;
    }

    /**
     * Adds an item to the player's inventory if it is not already present.
     *
     * @param item the equipment item to be added.
     */
    public void addToInventory(Equipment item) {
        if (!this.inventory.contains(item)) {
            this.inventory.add(item);
            System.out.println(item.getName() + " has been added to your inventory.");
        } else {
            System.out.println(item.getName() + " is already in your inventory.");
        }
    }

    /**
     * Retrieves the current number of Bastion keys the player has.
     *
     * @return the number of Bastion keys.
     */
    public int getNumBastionKeys() {
        return this.numBastionKeys;
    }

    /**
     * Adds a Bastion key fragment to the player's inventory.
     * Once all fragments are collected, a full key is created.
     */
    public void addBastionKey() {
        ++this.numBastionKeys;
        System.out.println("You now have " + this.getNumBastionKeys() + " out of 6 key fragments");
        if (this.numBastionKeys == 6) {
            System.out.println("All 6 key fragments begin to glow and draw near to one another...");
            GameLogic.anythingToContinue();
            System.out.println("A blast of light and heat engulf you and you look down to see a complete 4th bound gate key");
            this.removeInventoryKeys();
            this.addToKey(new Equipment("Bound Key", 0, 0, 0, 0));
            GameLogic.player.setXp(250);
        }
    }

    /**
     * Resets the Bastion key count to 0.
     */
    public void resetBastionKey() {
        this.numBastionKeys = 0;
    }

    /**
     * Removes all key fragments from the player's inventory.
     */
    public void removeInventoryKeys() {
        this.keyItems.removeIf((item) -> item.getName().contains("Key Fragment"));
    }

    /**
     * Removes Bound keys from the player's inventory.
     */
    public void removeBoundInventoryKeys() {
        this.keyItems.removeIf((item) -> item.getName().contains("Bound"));
    }

    /**
     * Removes Lord keys from the player's inventory.
     */
    public void removeLordInventoryKeys() {
        this.keyItems.removeIf((item) -> item.getName().contains("Lord"));
    }

    /**
     * Adds a key item to the player's inventory.
     *
     * @param item the key item to be added.
     */
    public void addToKey(Equipment item) {
        if (!this.keyItems.contains(item)) {
            this.keyItems.add(item);
            System.out.println(item.getName() + " has been added to your inventory.");
        } else {
            System.out.println(item.getName() + " is already in your inventory.");
        }
    }

    /**
     * Retrieves the names of all key items in the player's inventory.
     *
     * @return the list of key item names.
     */
    public List<String> getKey() {
        List<String> items = new ArrayList();

        for (Equipment item : this.keyItems) {
            items.add(item.getName());
        }

        return items;
    }

    /**
     * Adds currency (brim coins) to the player's total.
     *
     * @param amount the amount of currency to add.
     */
    public void addCurrency(int amount) {
        this.brimCoin += amount;
    }

    /**
     * Spends currency if the player has enough.
     *
     * @param amount the amount of currency to spend.
     * @return true if the currency was spent successfully, false otherwise.
     */
    public boolean spendCurrency(int amount) {
        if (this.brimCoin >= amount) {
            this.brimCoin -= amount;
            System.out.println(amount + " Brim Spent.");
            GameLogic.anythingToContinue();
            return true;
        } else {
            System.out.println("Not enough Brim!");
            GameLogic.anythingToContinue();
            return false;
        }
    }

    /**
     * Retrieves the player's current brim coin balance.
     *
     * @return the amount of brim coins.
     */
    public int getBrim() {
        return this.brimCoin;
    }

    /**
     * Equips an item from the player's inventory.
     * If the item conflicts with already equipped items, the old item is unequipped.
     *
     * @param item the item to be equipped.
     */
    public void equipItem(Equipment item) {
        if (item != null && !this.equippedItems.contains(item)) {
            if (item.getName().contains("Boots") && this.boots != null) {
                this.unequipItem(this.boots);
            } else if (item.getName().contains("Helmet") && this.helmet != null) {
                this.unequipItem(this.helmet);
            } else if (item.getName().contains("Chestplate") && this.chestplate != null) {
                this.unequipItem(this.chestplate);
            } else if (item.getName().contains("Pants") && this.pants != null) {
                this.unequipItem(this.pants);
            } else if (item.getName().contains("Amulet") && this.amulet != null) {
                this.unequipItem(this.amulet);
            } else if (item.getName().contains("Sword") && this.sword != null) {
                this.unequipItem(this.sword);
            }

            this.inventory.remove(item);
            this.equippedItems.add(item);
            item.setEquipped(true);
            System.out.println(item.getName() + " has been equipped.");
            this.updateStats(item, "Equip");
            this.updateEquipmentReferences();
        } else {
            System.out.println("This item is already equipped.");
        }
    }

    /**
     * Unequips an item currently equipped by the player.
     *
     * @param item the item to be unequipped.
     */
    public void unequipItem(Equipment item) {
        if (item != null && this.equippedItems.contains(item)) {
            this.equippedItems.remove(item);
            item.setEquipped(false);
            if (!this.inventory.contains(item)) {
                this.inventory.add(item);
            }

            System.out.println(item.getName() + " has been unequipped.");
        } else {
            System.out.println("This item is not equipped.");
        }

        this.updateStats(item, "Unequip");
        this.updateEquipmentReferences();
    }

    /**
     * Retrieves the list of all items in the player's inventory by name.
     *
     * @return the list of item names.
     */
    public List<String> getInventory() {
        List<String> items = new ArrayList();

        for (Equipment item : this.inventory) {
            items.add(item.getName());
        }

        return items;
    }

    /**
     * Retrieves the list of all equipped items in the player's inventory by name.
     *
     * @return the list of equipped item names.
     */
    public List<String> getEquippedItems() {
        List<String> items = new ArrayList();

        for (Equipment item : this.equippedItems) {
            items.add(item.getName());
        }

        return items;
    }

    /**
     * Retrieves an item by its index from either the inventory or equipped items.
     *
     * @param choice the index of the item (1-based).
     * @return the selected item.
     */
    public Equipment getSelectedItem(int choice) {
        if (choice >= 1 && choice <= this.inventory.size()) {
            return (Equipment)this.inventory.get(choice - 1);
        } else if (choice > this.inventory.size() && choice <= this.inventory.size() + this.equippedItems.size()) {
            return (Equipment)this.equippedItems.get(choice - this.inventory.size() - 1);
        } else {
            System.out.println("Invalid choice! Please select a valid item.");
            return null;
        }
    }

    /**
     * Updates the player's stats based on equipping or unequipping an item.
     *
     * @param item     the item being equipped or unequipped.
     * @param upOrDown whether the item is being equipped or unequipped.
     */
    public void updateStats(Equipment item, String upOrDown) {
        if (upOrDown == "Equip") {
            System.out.println("Updated Stats:");
            System.out.println("Effective Damage: " + this.getEffectiveDamage());
            System.out.println("Effective Defense: " + this.getEffectiveDefense());
            System.out.println("Effective Speed: " + this.getEffectiveSpeed());
        } else {
            System.out.println("Updated Stats:");
            System.out.println("Effective Damage: " + this.getEffectiveDamage());
            System.out.println("Effective Defense: " + this.getEffectiveDefense());
            System.out.println("Effective Speed: " + this.getEffectiveSpeed());
        }
    }

    /**
     * Updates the references to the player's equipped equipment.
     * Helps manage equipped item references.
     */
    private void updateEquipmentReferences() {
        this.boots = null;
        this.helmet = null;
        this.chestplate = null;
        this.pants = null;
        this.amulet = null;
        this.sword = null;

        // Assign items to specific slots
        for (Equipment item : this.equippedItems) {
            if (item.getName().contains("Boots")) {
                this.boots = item;
            } else if (item.getName().contains("Helmet")) {
                this.helmet = item;
            } else if (item.getName().contains("Chestplate")) {
                this.chestplate = item;
            } else if (item.getName().contains("Pants")) {
                this.pants = item;
            } else if (item.getName().contains("Amulet")) {
                this.amulet = item;
            } else if (item.getName().contains("Sword")) {
                this.sword = item;
            }
        }
    }

    /**
     * Removes an item from the player's inventory.
     *
     * @param item the item to be removed.
     */
    public void removeItem(Equipment item) {
        this.inventory.remove(item);
    }

    /**
     * Sets the game logic object for the player.
     *
     * @param gameLogic the game logic to be set.
     */
    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    /**
     * Retrieves the game logic object for the player.
     *
     * @return the game logic.
     */
    public GameLogic getGameLogic() {
        return this.gameLogic;
    }

    /**
     * Retrieves the player's inventory as a list of Equipment objects.
     *
     * @return the list of Equipment objects.
     */
    public List<Equipment> getInventoryAsEquipment() {
        return new ArrayList(this.inventory);
    }

    /**
     * Retrieves the player's equipped items as a list of Equipment objects.
     *
     * @return the list of equipped Equipment objects.
     */
    public List<Equipment> getEquippedItemsAsEquipment() {
        return new ArrayList(this.equippedItems);
    }

    /**
     * Retrieves the player's key items as a list of Equipment objects.
     *
     * @return the list of key Equipment objects.
     */
    public List<Equipment> getKeyItemsAsEquipment() {
        return new ArrayList(this.keyItems);
    }

    /**
     * Sets the player's inventory with a new list of equipment.
     *
     * @param inventory the new inventory list.
     */
    public void setInventory(List<Equipment> inventory) {
        this.inventory = inventory;
    }

    /**
     * Sets the player's equipped items with a new list of equipment.
     *
     * @param equippedItems the new equipped items list.
     */
    public void setEquippedItems(List<Equipment> equippedItems) {
        this.equippedItems = equippedItems;
    }

    /**
     * Sets the player's key items with a new list of equipment.
     *
     * @param keyItems the new key items list.
     */
    public void setKeyItems(List<Equipment> keyItems) {
        this.keyItems = keyItems;
    }

    /**
     * Sets the player's key items with a new list of equipment.
     *
     * @param numBastionKeys the num of bastionkeys a player has
     */
    public void setNumBastionKeys(int numBastionKeys) {
        this.numBastionKeys = numBastionKeys;
    }

}
