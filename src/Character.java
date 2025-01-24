import java.util.Scanner;

public abstract class Character {
    static Scanner scanner;
    private String name;
    private int maxHp;
    private int hp;
    private int xp;
    private int strength;
    private int defense;
    private int speed;
    private int level;
    private int xpToLevel;

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

    public void setLevel(int playerLevel) {
        this.level = playerLevel;
    }

    public int getXpToLevel(int level) {
        if (level == 0) {
            this.xpToLevel = 100;
        } else {
            this.xpToLevel = 100 + level * 100;
        }

        return this.xpToLevel;
    }

    public void upgradeLevel() {
        ++this.level;
    }

    public void upgradeStrength() {
        ++this.strength;
    }

    public void upgradeDefense() {
        ++this.defense;
    }

    public void upgradeHP() {
        this.maxHp += 5;
        this.hp = this.maxHp;
    }

    public void upgradeSpeed() {
        ++this.speed;
    }

    public void ResetXp(int xp, int xpToLevel) {
        this.xp = xp - xpToLevel;
    }

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

    static {
        scanner = new Scanner(System.in);
    }
}
