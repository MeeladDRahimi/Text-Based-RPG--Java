import java.util.Scanner;

/**
 * Abstract class representing a character in the game.
 * This class contains attributes and methods related to the character's stats, level progression, and more.
 */
public abstract class Character {

    // Scanner to handle input from the user.
    static Scanner scanner;

    // Character attributes
    private String name;  // Character's name
    private int maxHp;    // Maximum health points
    private int hp;       // Current health points
    private int xp;       // Experience points
    private int strength; // Strength stat
    private int defense;  // Defense stat
    private int speed;    // Speed stat
    private int level;    // Current level
    private int xpToLevel; // Experience points needed to level up

    /**
     * Constructor to initialize the character with specific stats.
     *
     * @param name      The name of the character.
     * @param maxHp    The maximum health points of the character.
     * @param hp       The current health points of the character.
     * @param xp       The experience points the character has.
     * @param strength The strength stat of the character.
     * @param defense  The defense stat of the character.
     * @param speed    The speed stat of the character.
     * @param level    The level of the character.
     */
    public Character(String name, int maxHp, int hp, int xp, int strength, int defense, int speed, int level) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.xp = xp;
        this.strength = strength;
        this.defense = defense;
        this.speed = speed;
        this.level = level;
    }

    // Getter methods to access character attributes
    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getXp() {
        return this.xp;
    }

    public int getLevel() {
        return this.level;
    }

    // Setter methods to update character attributes
    public void setLevel(int playerLevel) {
        this.level = playerLevel;
    }

    /**
     * Calculates how many XP are required to level up based on the character's current level.
     * For level 0, it requires 100 XP, and for each level above that, it increases by 100 XP.
     *
     * @param level The current level of the character.
     * @return The XP required to reach the next level.
     */
    public int getXpToLevel(int level) {
        if (level == 0) {
            this.xpToLevel = 100;
        } else {
            this.xpToLevel = 100 + level * 100;
        }

        return this.xpToLevel;
    }

    // Methods to upgrade character attributes
    public void upgradeLevel() {
        ++this.level;
    }

    public void upgradeStrength() {
        ++this.strength;
    }

    public void upgradeDefense() {
        ++this.defense;
    }

    /**
     * Upgrades the character's health points. It increases the max HP by 5 and sets current HP to max HP.
     */
    public void upgradeHP() {
        this.maxHp += 5;
        this.hp = this.maxHp;
    }

    public void upgradeSpeed() {
        ++this.speed;
    }

    /**
     * Resets the XP after leveling up. It sets the character's XP to the amount gained after leveling up.
     *
     * @param xp        The current XP of the character.
     * @param xpToLevel The XP needed to level up.
     */
    public void ResetXp(int xp, int xpToLevel) {
        this.xp = xp - xpToLevel;
    }

    // Setter methods to modify character's stats
    public void setXp(int num) {
        this.xp += num;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Static block to initialize the scanner
    static {
        scanner = new Scanner(System.in);
    }
}
