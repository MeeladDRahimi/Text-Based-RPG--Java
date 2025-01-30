import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a shop where the player can buy and sell items. The shop has a pool of items
 * and can offer a subset of these items for sale. It also supports a buyback feature where
 * sold items can be repurchased at a higher price.
 */
public class Shop implements Serializable {
    private List<Equipment> shopItemPool;  // List of available equipment items in the shop's pool.
    private List<Equipment> itemsForSale;  // List of equipment items currently available for sale.
    private List<Equipment> buybackInventory;  // List of items that have been sold and are available for buyback.
    private Random rand;  // Random object for selecting items from the pool.

    /**
     * Constructs a Shop with a given pool of available equipment items.
     *
     * @param shopItemPool the list of equipment available to be sold in the shop.
     */
    public Shop(List<Equipment> shopItemPool) {
        this.shopItemPool = new ArrayList(shopItemPool);
        this.itemsForSale = new ArrayList();
        this.buybackInventory = new ArrayList();
        this.rand = new Random();
        this.populateShop();  // Initially populate the shop with items.
    }

    /**
     * Populates the shop with a random selection of items from the item pool.
     */
    public void populateShop() {
        this.itemsForSale.clear();  // Clear any existing items.
        List<Equipment> availableItems = new ArrayList(this.shopItemPool);  // Make a copy of the item pool.

        // Select 5 random items from the available items to sell in the shop.
        for (int i = 0; i < 5; ++i) {
            if (!availableItems.isEmpty()) {
                Equipment randomItem = (Equipment) availableItems.remove(this.rand.nextInt(availableItems.size()));
                this.itemsForSale.add(randomItem);
            }
        }
    }

    /**
     * Displays all the items available for sale in the shop.
     */
    public void showShopItems() {
        System.out.println("Welcome to the shop! Here are the items for sale:");

        // Iterate through the items for sale and display their details.
        for (int i = 0; i < this.itemsForSale.size(); ++i) {
            Equipment item = (Equipment) this.itemsForSale.get(i);
            System.out.println(i + 1 + ". " + item.getName() + " - Price: " + item.getPrice() + " Brim (Strength: " + item.getStrengthBoost() + ", Defense: " + item.getDefenseBoost() + ", Speed: " + item.getSpeedBoost() + ")");
        }
    }

    /**
     * Displays all items that can be bought back by the player.
     */
    public void showBuybackItems() {
        if (this.buybackInventory.isEmpty()) {
            System.out.println("No items are available to buyback.");
        } else {
            System.out.println("Items available for buyback:");

            // Iterate through the buyback inventory and display the buyback price.
            for (int i = 0; i < this.buybackInventory.size(); ++i) {
                Equipment item = (Equipment) this.buybackInventory.get(i);
                System.out.println(i + 1 + ". " + item.getName() + " - Price: " + (int) ((double) item.getPrice() * (double) 1.25F));
            }
        }
    }

    /**
     * Allows the player to buy back an item they previously sold.
     *
     * @param player      the player trying to buy back an item.
     * @param itemIndex   the index of the item in the buyback inventory.
     * @return true if the transaction was successful, false otherwise.
     */
    public boolean buyBackItem(Player player, int itemIndex) {
        if (itemIndex >= 1 && itemIndex <= this.buybackInventory.size()) {
            Equipment item = (Equipment) this.buybackInventory.get(itemIndex - 1);
            int buybackPrice = (int) ((double) item.getPrice() * (double) 1.25F);
            if (player.spendCurrency(buybackPrice)) {
                player.addToInventory(item);
                this.buybackInventory.remove(item);
                System.out.println("You bought back " + item.getName() + " for " + buybackPrice + " Brim.");
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

    /**
     * Allows the player to sell an item to the shop.
     *
     * @param player      the player selling the item.
     * @param itemIndex   the index of the item in the player's inventory.
     * @return true if the transaction was successful, false otherwise.
     */
    public boolean sellItem(Player player, int itemIndex) {
        if (itemIndex >= 1 && itemIndex <= player.getInventory().size()) {
            Equipment item = (Equipment) player.getInventoryAsEquipment().get(itemIndex - 1);
            int sellPrice = (int) ((double) item.getPrice() * (double) 0.5F);
            player.addCurrency(sellPrice);
            player.removeItem(item);
            this.buybackInventory.add(item);
            System.out.println("You sold " + item.getName() + " for " + sellPrice + " Brim.");
            return true;
        } else {
            System.out.println("Invalid item choice.");
            return false;
        }
    }

    /**
     * Allows the player to buy an item from the shop.
     *
     * @param player      the player buying the item.
     * @param itemIndex   the index of the item in the shop.
     * @return true if the transaction was successful, false otherwise.
     */
    public boolean buyItem(Player player, int itemIndex) {
        if (itemIndex >= 1 && itemIndex <= this.itemsForSale.size()) {
            Equipment item = (Equipment) this.itemsForSale.get(itemIndex - 1);
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

    /**
     * Restocks the shop if it is out of stock.
     */
    public void restockShop() {
        if (this.itemsForSale.isEmpty()) {
            System.out.println("The shop is out of stock, restocking...");
            this.populateShop();
        }
    }

    /**
     * Gets the size of the buyback inventory.
     *
     * @return the number of items available for buyback.
     */
    public int getBuybackInventorySize() {
        return this.buybackInventory.size();
    }
}
