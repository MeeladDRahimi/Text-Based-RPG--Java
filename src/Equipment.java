import java.io.Serializable;

public class Equipment implements Serializable {
    private String name;
    private int strengthBoost;
    private int defenseBoost;
    private int speedBoost;
    private boolean isEquipped;
    private int price;

    public Equipment(String name, int strengthBoost, int defenseBoost, int speedBoost, int price) {
        this.name = name;
        this.strengthBoost = strengthBoost;
        this.defenseBoost = defenseBoost;
        this.speedBoost = speedBoost;
        this.isEquipped = false;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getStrengthBoost() {
        return this.strengthBoost;
    }

    public int getDefenseBoost() {
        return this.defenseBoost;
    }

    public boolean isEquipped() {
        return this.isEquipped;
    }

    public int getSpeedBoost() {
        return this.speedBoost;
    }

    public void setEquipped(boolean equipped) {
        this.isEquipped = equipped;
    }

    public int getPrice() {
        return this.price;
    }
}
