import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player extends Character implements Serializable {
    private static final long serialVersionUID = 1L;
    public int numAtkUpgrades = 0;
    public int numDefUpgrades = 0;
    public String[] atkUpgrades = new String[]{"Brute Strength", "Power", "Might", "Godlike Strength"};
    public String[] defUpgrades = new String[]{"Heavy Bones", "Stoneskin", "Steelskin", "Holy Aura"};
    private List<Equipment> inventory = new ArrayList();
    private List<Equipment> equippedItems = new ArrayList();
    private List<Equipment> keyItems = new ArrayList();
    private int numBastionKeys = 0;
    private int brimCoin = 0;
    private Equipment boots;
    private Equipment helmet;
    private Equipment chestplate;
    private Equipment pants;
    private Equipment amulet;
    private Equipment sword;
    private int additionalSpeed = 0;
    private transient GameLogic gameLogic;

    public Player(String name, boolean skipChooseTrait) {
        super(name, 40, 40, 0, 10, 5, 10, 0);
        if (!skipChooseTrait) {
            this.chooseTrait();
        }

    }

    public void chooseTrait() {
        GameLogic.clearConsole();
        Story.boldText();
        GameLogic.printHeading("Choose a trait:");
        Story.resetTextColor();
        String var10001 = this.atkUpgrades[this.numAtkUpgrades];
        System.out.println("(1) " + var10001);
        var10001 = this.defUpgrades[this.numDefUpgrades];
        System.out.println("(2) " + var10001);
        int input = GameLogic.readInt("-> ", 2);
        GameLogic.clearConsole();
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

    public void levelUp() {
        if (this.getXp() >= this.getXpToLevel(this.getLevel())) {
            this.ResetXp(this.getXp(), this.getXpToLevel(this.getLevel()));
            this.upgradeLevel();
            System.out.println("Level Up!");

            for(int i = 5; i > 0; --i) {
                System.out.println("You have " + i + " upgrade tokens.");
                System.out.println("Please select a stat to upgrade:");
                System.out.println("1. Health");
                System.out.println("2. Strength");
                System.out.println("3. Defense");
                System.out.println("4. Speed");
                switch (scanner.next()) {
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

            if (this.getLevel() % 5 == 0) {
                this.chooseTrait();
            }
        } else {
            System.out.println("Insufficient XP to Level UP");
        }

    }

    public int getEffectiveDamage() {
        int boost = (int)((double)this.getStrength() * ((double)1.0F + (double)0.25F * (double)this.numAtkUpgrades));

        for(Equipment item : this.equippedItems) {
            if (item != null && item.getStrengthBoost() > 0) {
                boost += item.getStrengthBoost();
            }
        }

        return boost;
    }

    public int getEffectiveDefense() {
        int boost = (int)((double)this.getDefense() * ((double)1.0F + (double)0.25F * (double)this.numDefUpgrades));

        for(Equipment item : this.equippedItems) {
            if (item != null && item.getDefenseBoost() > 0) {
                boost += item.getDefenseBoost();
            }
        }

        return boost;
    }

    public int getEffectiveSpeed() {
        int boost = this.getSpeed() + this.additionalSpeed;

        for(Equipment item : this.equippedItems) {
            if (item != null && item.getSpeedBoost() > 0) {
                boost += item.getSpeedBoost();
            }
        }

        return boost;
    }

    public void addToInventory(Equipment item) {
        if (!this.inventory.contains(item)) {
            this.inventory.add(item);
            System.out.println(item.getName() + " has been added to your inventory.");
        } else {
            System.out.println(item.getName() + " is already in your inventory.");
        }

    }

    public int getNumBastionKeys() {
        return this.numBastionKeys;
    }

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

    public void resetBastionKey() {
        this.numBastionKeys = 0;
    }

    public void removeInventoryKeys() {
        this.keyItems.removeIf((item) -> item.getName().contains("Key Fragment"));
    }

    public void removeBoundInventoryKeys() {
        this.keyItems.removeIf((item) -> item.getName().contains("Bound"));
    }

    public void removeLordInventoryKeys() {
        this.keyItems.removeIf((item) -> item.getName().contains("Lord"));
    }

    public void addToKey(Equipment item) {
        if (!this.keyItems.contains(item)) {
            this.keyItems.add(item);
            System.out.println(item.getName() + " has been added to your inventory.");
        } else {
            System.out.println(item.getName() + " is already in your inventory.");
        }

    }

    public List<String> getKey() {
        List<String> items = new ArrayList();

        for(Equipment item : this.keyItems) {
            items.add(item.getName());
        }

        return items;
    }

    public void addCurrency(int amount) {
        this.brimCoin += amount;
    }

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

    public int getBrim() {
        return this.brimCoin;
    }

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

    public List<String> getInventory() {
        List<String> items = new ArrayList();

        for(Equipment item : this.inventory) {
            items.add(item.getName());
        }

        return items;
    }

    public List<String> getEquippedItems() {
        List<String> items = new ArrayList();

        for(Equipment item : this.equippedItems) {
            items.add(item.getName());
        }

        return items;
    }

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

    private void updateEquipmentReferences() {
        this.boots = null;
        this.helmet = null;
        this.chestplate = null;
        this.pants = null;
        this.amulet = null;
        this.sword = null;

        for(Equipment item : this.equippedItems) {
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

    public void removeItem(Equipment item) {
        this.inventory.remove(item);
    }

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public GameLogic getGameLogic() {
        return this.gameLogic;
    }

    public List<Equipment> getInventoryAsEquipment() {
        return new ArrayList(this.inventory);
    }

    public List<Equipment> getEquippedItemsAsEquipment() {
        return new ArrayList(this.equippedItems);
    }

    public List<Equipment> getKeyItemsAsEquipment() {
        return new ArrayList(this.keyItems);
    }

    public void setInventory(List<Equipment> inventory) {
        this.inventory = inventory;
    }

    public void setEquippedItems(List<Equipment> equippedItems) {
        this.equippedItems = equippedItems;
    }

    public void setKeyItems(List<Equipment> keyItems) {
        this.keyItems = keyItems;
    }

    public void setNumBastionKeys(int numBastionKeys) {
        this.numBastionKeys = numBastionKeys;
    }
}
