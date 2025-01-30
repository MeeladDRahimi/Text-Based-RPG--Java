import java.io.Serializable;

/**
 * Represents a piece of equipment that can be equipped by the player.
 * Equipment has various attributes such as name, strength boost, defense boost,
 * speed boost, and price. It also tracks whether the equipment is currently equipped.
 */
public class Equipment implements Serializable {
    private String name;  // Name of the equipment.
    private int strengthBoost;  // Boost to the player's strength when equipped.
    private int defenseBoost;  // Boost to the player's defense when equipped.
    private int speedBoost;  // Boost to the player's speed when equipped.
    private boolean isEquipped;  // Indicates whether the equipment is currently equipped.
    private int price;  // The price of the equipment.

    /**
     * Constructor to create a new piece of equipment.
     *
     * @param name          the name of the equipment.
     * @param strengthBoost the strength boost provided by the equipment.
     * @param defenseBoost  the defense boost provided by the equipment.
     * @param speedBoost    the speed boost provided by the equipment.
     * @param price         the price of the equipment.
     */
    public Equipment(String name, int strengthBoost, int defenseBoost, int speedBoost, int price) {
        this.name = name;
        this.strengthBoost = strengthBoost;
        this.defenseBoost = defenseBoost;
        this.speedBoost = speedBoost;
        this.isEquipped = false;  // Equipment is initially not equipped.
        this.price = price;
    }

    /**
     * Gets the name of the equipment.
     *
     * @return the name of the equipment.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the strength boost provided by the equipment.
     *
     * @return the strength boost.
     */
    public int getStrengthBoost() {
        return this.strengthBoost;
    }

    /**
     * Gets the defense boost provided by the equipment.
     *
     * @return the defense boost.
     */
    public int getDefenseBoost() {
        return this.defenseBoost;
    }

    /**
     * Checks if the equipment is currently equipped.
     *
     * @return true if the equipment is equipped, false otherwise.
     */
    public boolean isEquipped() {
        return this.isEquipped;
    }

    /**
     * Gets the speed boost provided by the equipment.
     *
     * @return the speed boost.
     */
    public int getSpeedBoost() {
        return this.speedBoost;
    }

    /**
     * Sets whether the equipment is equipped or not.
     *
     * @param equipped true if the equipment should be marked as equipped, false otherwise.
     */
    public void setEquipped(boolean equipped) {
        this.isEquipped = equipped;
    }

    /**
     * Gets the price of the equipment.
     *
     * @return the price of the equipment.
     */
    public int getPrice() {
        return this.price;
    }
}
