
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameState implements Serializable {
    private String playerName;
    private int playerLevel;
    private int playerHp;
    private int playerMaxHp;
    private int playerBrim;
    private int playerStrength;
    private int playerDefense;
    private int playerSpeed;
    private int playerXp;
    private List<Equipment> inventory;
    private List<Equipment> equippedItems;
    private List<Equipment> keyItems;
    private Set<String> defeatedBastions;
    private int numBastionKeys;
    private int currentAct;
    private static boolean playerHasDefeatedBoundBoss;
    private int numAtkUpgrades;
    private int numDefUpgrades;
    private String[] atkUpgrades;
    private String[] defUpgrades;

    public GameState(Player player, int currentAct, boolean hasDefeatedBoundBoss) {
        this.playerName = player.getName();
        this.playerLevel = player.getLevel();
        this.playerHp = player.getHp();
        this.playerMaxHp = player.getMaxHp();
        this.playerBrim = player.getBrim();
        this.playerStrength = player.getStrength();
        this.playerDefense = player.getDefense();
        this.playerSpeed = player.getSpeed();
        this.inventory = player.getInventoryAsEquipment();
        this.equippedItems = player.getEquippedItemsAsEquipment();
        this.keyItems = player.getKeyItemsAsEquipment();
        this.playerXp = player.getXp();
        this.defeatedBastions = GameLogic.defeatedBastions;
        this.currentAct = GameLogic.getCurrentAct();
        this.numBastionKeys = player.getNumBastionKeys();
        this.numAtkUpgrades = player.numAtkUpgrades;
        this.numDefUpgrades = player.numDefUpgrades;
        this.atkUpgrades = player.atkUpgrades;
        this.defUpgrades = player.defUpgrades;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public int getPlayerLevel() {
        return this.playerLevel;
    }

    public int getCurrentAct() {
        return this.currentAct;
    }

    public boolean hasDefeatedBoundBoss() {
        return playerHasDefeatedBoundBoss;
    }

    public Set<String> getDefeatedBastions() {
        return this.defeatedBastions;
    }

    public int getBrim() {
        return this.playerBrim;
    }

    public int getPlayerHp() {
        return this.playerHp;
    }

    public int getPlayerMaxHp() {
        return this.playerMaxHp;
    }

    public int getPlayerXp() {
        return this.playerXp;
    }

    public int getPlayerDefense() {
        return this.playerDefense;
    }

    public int getPlayerSpeed() {
        return this.playerSpeed;
    }

    public int getPlayerStrength() {
        return this.playerStrength;
    }

    public List<Equipment> getInventory() {
        return new ArrayList(this.inventory);
    }

    public List<Equipment> getEquippedItems() {
        return new ArrayList(this.equippedItems);
    }

    public List<Equipment> getKeyItems() {
        return new ArrayList(this.keyItems);
    }

    public int getNumBastionKeys() {
        return this.numBastionKeys;
    }

    public int getNumAtkUpgrades() {
        return this.numAtkUpgrades;
    }

    public void setNumAtkUpgrades(int numAtkUpgrades) {
        this.numAtkUpgrades = numAtkUpgrades;
    }

    public int getNumDefUpgrades() {
        return this.numDefUpgrades;
    }

    public void setNumDefUpgrades(int numDefUpgrades) {
        this.numDefUpgrades = numDefUpgrades;
    }

    public String[] getAtkUpgrades() {
        return this.atkUpgrades;
    }

    public void setAtkUpgrades(String[] atkUpgrades) {
        this.atkUpgrades = atkUpgrades;
    }

    public String[] getDefUpgrades() {
        return this.defUpgrades;
    }

    public void setDefUpgrades(String[] defUpgrades) {
        this.defUpgrades = defUpgrades;
    }
}

