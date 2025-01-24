import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shop implements Serializable {
    private List<Equipment> shopItemPool;
    private List<Equipment> itemsForSale;
    private List<Equipment> buybackInventory;
    private Random rand;

    public Shop(List<Equipment> shopItemPool) {
        this.shopItemPool = new ArrayList(shopItemPool);
        this.itemsForSale = new ArrayList();
        this.buybackInventory = new ArrayList();
        this.rand = new Random();
        this.populateShop();
    }

    public void populateShop() {
        this.itemsForSale.clear();
        List<Equipment> availableItems = new ArrayList(this.shopItemPool);

        for(int i = 0; i < 5; ++i) {
            if (!availableItems.isEmpty()) {
                Equipment randomItem = (Equipment)availableItems.remove(this.rand.nextInt(availableItems.size()));
                this.itemsForSale.add(randomItem);
            }
        }

    }

    public void showShopItems() {
        System.out.println("Welcome to the shop! Here are the items for sale:");

        for(int i = 0; i < this.itemsForSale.size(); ++i) {
            Equipment item = (Equipment)this.itemsForSale.get(i);
            System.out.println(i + 1 + ". " + item.getName() + " - Price: " + item.getPrice() + " Brim (Strength: " + item.getStrengthBoost() + ", Defense: " + item.getDefenseBoost() + ", Speed: " + item.getSpeedBoost() + ")");
        }

    }

    public void showBuybackItems() {
        if (this.buybackInventory.isEmpty()) {
            System.out.println("No items are available to buyback.");
        } else {
            System.out.println("Items available for buyback:");

            for(int i = 0; i < this.buybackInventory.size(); ++i) {
                Equipment item = (Equipment)this.buybackInventory.get(i);
                System.out.println(i + 1 + ". " + item.getName() + " - Price: " + (int)((double)item.getPrice() * (double)1.25F));
            }

        }
    }

    public boolean buyBackItem(Player player, int itemIndex) {
        if (itemIndex >= 1 && itemIndex <= this.buybackInventory.size()) {
            Equipment item = (Equipment)this.buybackInventory.get(itemIndex - 1);
            int buybackPrice = (int)((double)item.getPrice() * (double)1.25F);
            if (player.spendCurrency(buybackPrice)) {
                player.addToInventory(item);
                this.buybackInventory.remove(item);
                PrintStream var10000 = System.out;
                String var10001 = item.getName();
                var10000.println("You bought back " + var10001 + " for " + buybackPrice + " Brim.");
                return true;
            } else {
                System.out.println("You don't have enough Brim!");
                return false;
            }
        } else {
            System.out.println("Invalid item choice.");
            return false;
        }
    }

    public boolean sellItem(Player player, int itemIndex) {
        if (itemIndex >= 1 && itemIndex <= player.getInventory().size()) {
            Equipment item = (Equipment)player.getInventoryAsEquipment().get(itemIndex - 1);
            int sellPrice = (int)((double)item.getPrice() * (double)0.5F);
            player.addCurrency(sellPrice);
            player.removeItem(item);
            this.buybackInventory.add(item);
            PrintStream var10000 = System.out;
            String var10001 = item.getName();
            var10000.println("You sold " + var10001 + " for " + sellPrice + " Brim.");
            return true;
        } else {
            System.out.println("Invalid item choice.");
            return false;
        }
    }

    public boolean buyItem(Player player, int itemIndex) {
        if (itemIndex >= 1 && itemIndex <= this.itemsForSale.size()) {
            Equipment item = (Equipment)this.itemsForSale.get(itemIndex - 1);
            if (player.spendCurrency(item.getPrice())) {
                player.addToInventory(item);
                this.itemsForSale.remove(item);
                System.out.println("You bought " + item.getName());
                return true;
            } else {
                System.out.println("You don't have enough Brim!");
                return false;
            }
        } else {
            System.out.println("Invalid item choice.");
            return false;
        }
    }

    public void restockShop() {
        if (this.itemsForSale.isEmpty()) {
            System.out.println("The shop is out of stock, restocking...");
            this.populateShop();
        }

    }

    public int getBuybackInventorySize() {
        return this.buybackInventory.size();
    }
}
