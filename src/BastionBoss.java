import java.util.Random;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Bastion Boss in the game, a special type of boss with randomized stats
 * that varies based on the current act and region. Each boss has a unique type, name,
 * and stats depending on where they are located within the game world.
 */
public class BastionBoss extends Enemy {

    /**
     * Default constructor for BastionBoss.
     * This initializes the boss by setting its attributes based on the current game map.
     */
    public BastionBoss() {
        bossSequences(GameLogic.gameMap); // Initialize the boss with the appropriate sequence based on the current map
    }

    /**
     * Randomizes the Bastion Boss stats based on the player's stats and the current act.
     * This includes adjusting the boss's HP, strength, defense, and speed.
     */
    private void randomizeBossStats() {
        Random rand = new Random();
        int playerMaxHp = GameLogic.player.getMaxHp();
        int playerStrength = GameLogic.player.getStrength();
        int playerDefense = GameLogic.player.getDefense();
        int playerSpeed = GameLogic.player.getSpeed();
        int currentAct = GameLogic.getCurrentAct();

        // Randomly scale Bastion Boss stats based on the player's stats and current act
        this.setMaxHp((int)((double)playerMaxHp * (0.9 + rand.nextDouble() * (double)0.5F) * ((double)1.0F + (double)currentAct * (double)0.25F)));
        this.setHp(this.getMaxHp()); // Set current HP to max HP
        this.setStrength((int)((double)playerStrength * (1.3 + rand.nextDouble() * (double)0.5F) * ((double)1.0F + (double)currentAct * 0.2)));
        this.setDefense((int)((double)playerDefense * (1.3 + rand.nextDouble() * (double)0.5F) * ((double)1.0F + (double)currentAct * 0.2)));
        this.setSpeed((int)((double)playerSpeed * (0.8 + rand.nextDouble() * (double)0.5F) * ((double)1.0F + (double)currentAct * 0.1)));
    }

    // Predefined types for Bastion Bosses, each representing a different element
    private static final String[] types = new String[]{
            "🔥", "❄️", "☠️", "🔮"
    };

    /**
     * Determines the boss sequence for the current game based on the act and region.
     * This method maps the current act and region to a specific boss and assigns its attributes.
     *
     * @param map The current game map containing regions and the player's position.
     */
    private void bossSequences(GameMap map) {
        System.out.println(map.getCurrRegionName()); // Print the current region name for debugging
        int act = GameLogic.getCurrentAct();
        String region = map.getCurrRegionName();

        // Define boss mapping for each act
        Map<String, String[]> bossMap = getBossMap(act);

        // Default boss (if region is not found in map)
        String[] defaultBoss = {"Fire", "Arcanight"};

        // Get boss data from map or default if region is not found
        String[] bossData = bossMap.getOrDefault(region, defaultBoss);
        setBossAttributes(bossData[0], bossData[1]); // Set boss type, name, and randomize stats
    }

    /**
     * Returns a mapping of regions to their respective boss types and names
     * based on the current act. The map is used to determine the correct boss
     * for each region in the game.
     *
     * @param act The current act in the game (e.g., Act 1, Act 2, etc.).
     * @return A map containing region names as keys and an array with boss type and name as values.
     */
    private Map<String, String[]> getBossMap(int act) {
        Map<String, String[]> bossMap = new HashMap<>();

        switch (act) {
            case 1:
                // Act 1 region to boss mappings
                bossMap.put("Ashen Field", new String[]{"🔥", "Pyre Lord Surtur"});
                bossMap.put("The Emberwood", new String[]{"❄️", "Frost Warden Ignis"});
                bossMap.put("Wailing Hollow", new String[]{"☠️", "Skeleton Queen Merridia"});
                bossMap.put("Cinderfall Cliffs", new String[]{"☠️", "Plague Bringer Dermese"});
                bossMap.put("The Shivering Rift", new String[]{"🔮", "Banshee Queen"});
                bossMap.put("Default", new String[]{"🔥", "Tarfiend Belphegor"});
                break;

            case 2:
                // Act 2 region to boss mappings
                bossMap.put("The Crag of Souls", new String[]{"🔥", "Diablo"});
                bossMap.put("Hellfire Caverns", new String[]{"❄️", "Borealis, the Eternal Blizzard"});
                bossMap.put("Blighted Ash Wastes", new String[]{"☠️", "Morgrim, the Soul Devourer"});
                bossMap.put("The Searing Plains", new String[]{"☠️", "Malrik, the Plague Revenant"});
                bossMap.put("Infernal Spire", new String[]{"🔮", "Umbra, the Riftborn Magnus"});
                bossMap.put("Default", new String[]{"🔥", "Ignisfang, the Hellborn Wyrm"});
                break;

            case 3:
                // Act 3 region to boss mappings
                bossMap.put("The Cinderscape", new String[]{"🔥", "Infernis"});
                bossMap.put("Fiendish Depths", new String[]{"❄️", "Cryovex"});
                bossMap.put("Demonforge Citadel", new String[]{"☠️", "Thanadrax"});
                bossMap.put("The Infernal Crucible", new String[]{"☠️", "Gilded Thanadrax"});
                bossMap.put("Lavaheart Basin", new String[]{"🔮", "Vaelith, the Arcane Lich"});
                bossMap.put("Default", new String[]{"🔥", "Moltres, Copyright Blazer"});
                break;

            default:
                // Default boss mapping if the act is not recognized
                bossMap.put("The Blazing Chasm", new String[]{"🔥", "Mephisto"});
                bossMap.put("The Blood Furnace", new String[]{"❄️", "Frostreaver, the Rimeborn Warlord"});
                bossMap.put("The Screaming Pit", new String[]{"☠️", "Malkrow, Bird of Death"});
                bossMap.put("The Eternal Flame", new String[]{"☠️", "Obituscary, Bringer of Bad News"});
                bossMap.put("The Gate of Despair", new String[]{"🔮", "Whodeknee"});
                bossMap.put("Default", new String[]{"🔥", "Arcanight"});
                break;
        }

        return bossMap; // Return the map with region-boss data
    }

    /**
     * Sets the boss type, name, and randomizes stats based on the type and name.
     *
     * @param type The type of the boss (e.g., "🔥", "❄️").
     * @param name The name of the boss.
     */
    private void setBossAttributes(String type, String name) {
        this.setType(type); // Set the boss's element type (e.g., Fire, Ice)
        this.setName(type + " " + name); // Set the boss's full name (e.g., Fire Pyre Lord Surtur)
        randomizeBossStats(); // Randomize the boss's stats based on the player's stats
    }
}
