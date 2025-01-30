/**
 * Represents the game state, storing player attributes, inventory, and progress.
 */
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

    /**
     * Constructs a new GameState based on the player's current progress and attributes.
     *
     * @param player The player whose state is being saved.
     * @param currentAct The current act of the game.
     * @param hasDefeatedBoundBoss Whether the Bound Boss has been defeated.
     *
     * Preconditions: Player must not be null.
     * Postconditions: The game state is initialized with the player's data.
     */
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

    /**
     * Retrieves the player's name.
     * @return The player's name.
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Retrieves the player's level.
     * @return The player's level.
     */
    public int getPlayerLevel() {
        return this.playerLevel;
    }

    /**
     * Retrieves the current act of the game.
     * @return The current act number.
     */
    public int getCurrentAct() {
        return this.currentAct;
    }

    /**
     * Checks if the player has defeated the Bound Boss.
     * @return True if the Bound Boss is defeated, false otherwise.
     */
    public boolean hasDefeatedBoundBoss() {
        return playerHasDefeatedBoundBoss;
    }

    /**
     * Retrieves the set of defeated bastions.
     * @return A set of defeated bastion names.
     */
    public Set<String> getDefeatedBastions() {
        return this.defeatedBastions;
    }

    /**
     * Retrieves the player's brim count.
     * @return The player's brim count.
     */
    public int getBrim() {
        return this.playerBrim;
    }

    /**
     * Retrieves the player's current HP.
     * @return The player's HP.
     */
    public int getPlayerHp() {
        return this.playerHp;
    }

    /**
     * Retrieves the player's maximum HP.
     * @return The player's max HP.
     */
    public int getPlayerMaxHp() {
        return this.playerMaxHp;
    }

    /**
     * Retrieves the player's XP.
     * @return The player's XP.
     */
    public int getPlayerXp() {
        return this.playerXp;
    }

    /**
     * Retrieves the player's defense stat.
     * @return The player's defense value.
     */
    public int getPlayerDefense() {
        return this.playerDefense;
    }

    /**
     * Retrieves the player's speed stat.
     * @return The player's speed value.
     */
    public int getPlayerSpeed() {
        return this.playerSpeed;
    }

    /**
     * Retrieves the player's strength stat.
     * @return The player's strength value.
     */
    public int getPlayerStrength() {
        return this.playerStrength;
    }

    /**
     * Retrieves the player's inventory.
     * @return A list of inventory items.
     */
    public List<Equipment> getInventory() {
        return new ArrayList<>(this.inventory);
    }

    /**
     * Retrieves the player's equipped items.
     * @return A list of equipped items.
     */
    public List<Equipment> getEquippedItems() {
        return new ArrayList<>(this.equippedItems);
    }

    /**
     * Retrieves the player's key items.
     * @return A list of key items.
     */
    public List<Equipment> getKeyItems() {
        return new ArrayList<>(this.keyItems);
    }

    /**
     * Retrieves the number of bastion keys the player has.
     * @return The number of bastion keys.
     */
    public int getNumBastionKeys() {
        return this.numBastionKeys;
    }

    /**
     * Retrieves the number of attack upgrades.
     * @return The number of attack upgrades.
     */
    public int getNumAtkUpgrades() {
        return this.numAtkUpgrades;
    }

    /**
     * Sets the number of attack upgrades.
     * @param numAtkUpgrades The new number of attack upgrades.
     */
    public void setNumAtkUpgrades(int numAtkUpgrades) {
        this.numAtkUpgrades = numAtkUpgrades;
    }

    /**
     * Retrieves the number of defense upgrades.
     * @return The number of defense upgrades.
     */
    public int getNumDefUpgrades() {
        return this.numDefUpgrades;
    }

    /**
     * Retrieves the name of attack upgrades
     */
    public String[] getAtkUpgrades(){
        return this.atkUpgrades;
    }

    /**
     * Retrieves the name of defense upgrades
     */
    public String[] getDefUpgrades(){
        return this.defUpgrades;
    }

}
