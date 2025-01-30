import java.util.Random;

/**
 * Represents an enemy character in the game, with attributes such as health, strength, defense, and speed.
 * Enemies are randomized in terms of type and stats based on the player's current progress in the game.
 */
public class Enemy extends Character {

    // Predefined lists of adjectives for different enemy types
    private static final String[] fireAdjectives = new String[]{
            "Infernal", "Hellbound", "Emberborn", "Ashen", "Scorching", "Charred", "Molten", "Pyroclastic", "Brimstone", "Blazing"
    };

    private static final String[] iceAdjectives = new String[]{
            "Frostbane", "Glacial", "Icebound", "Blizzard-born", "Frozen", "Snowdrift", "Permafrost", "Shardborn", "Arctic", "Cryogenic"
    };

    private static final String[] undeadAdjectives = new String[]{
            "Boneclaw", "Wraithborn", "Hollow", "Gravebound", "Phantom", "Necrotic", "Ghoul-ridden", "Lichborne", "Shadowforged", "Deathbound"
    };

    private static final String[] arcaneAdjectives = new String[]{
            "Voidborn", "Abyssal", "Stormforged", "Celestial", "Aetherborn", "Titanborn", "Behemothic", "Runed", "Warped", "Draconic"
    };

    private static final String[] enemyNames = new String[]{
            "Warlord", "Revenant", "Stalker", "Titan", "Lord", "Demon", "Behemoth", "Specter", "Lich", "Abomination",
            "Wraith", "Phantom", "Ghoul", "Shade", "Gargoyle", "Serpent", "Dragon", "Fiend", "Overlord", "Necromancer",
            "Warlock", "Cultist", "Brute", "Terror", "Harbinger", "Sentinel", "Colossus", "Monstrosity", "Nightmare", "Devourer",
            "Abysswalker", "Voidborn", "Hellhound", "Ravager", "Plaguebearer", "Corruptor", "Dreadknight", "Darkspawn", "Soulreaper", "Tormentor",
            "Executioner", "Doombringer", "Stormcaller", "Pyromancer", "Frostbringer", "Ironclad", "Shadowmancer", "Bloodfiend", "Desecrator", "Bonecrusher"
    };

    // Types of enemies represented by symbols
    private static final String[] types = new String[]{
            "🔥", "❄️", "☠️", "🔮"
    };

    private String type; // Store the enemy's type (e.g., Fire, Ice, Undead, Arcane)

    /**
     * Default constructor for the Enemy class.
     * It initializes the enemy with randomized type, name, and stats based on the player's current attributes.
     */
    public Enemy() {
        super("", 100, 100, 0, 10, 10, 10, 0); // Temporary empty name
        this.type = assignRandomType(); // Assign a random type to the enemy
        this.setName(generateNameBasedOnType(this.type)); // Generate a name based on the enemy's type
        this.randomize(); // Randomize enemy stats based on the player's attributes and current act
    }

    /**
     * Randomly assigns an enemy type from the available types.
     *
     * @return The type symbol of the enemy (e.g., "🔥", "❄️", etc.).
     */
    private String assignRandomType() {
        Random rand = new Random();
        return types[rand.nextInt(types.length)]; // Randomly selects a type from the types array
    }

    /**
     * Generates a unique name for the enemy based on its type.
     *
     * @param type The type of the enemy (e.g., "🔥", "❄️", etc.).
     * @return The full name of the enemy, including its type and a randomly selected adjective.
     */
    private static String generateNameBasedOnType(String type) {
        Random rand = new Random();
        String adjective;

        // Select an adjective based on the type
        switch (type) {
            case "🔥" -> adjective = fireAdjectives[rand.nextInt(fireAdjectives.length)];
            case "❄️" -> adjective = iceAdjectives[rand.nextInt(iceAdjectives.length)];
            case "☠️" -> adjective = undeadAdjectives[rand.nextInt(undeadAdjectives.length)];
            case "🔮" -> adjective = arcaneAdjectives[rand.nextInt(arcaneAdjectives.length)];
            default -> adjective = "Unknown";
        }

        // Select a random enemy name and combine with the type and adjective
        String name = enemyNames[rand.nextInt(enemyNames.length)];
        return type + adjective + " " + name;
    }

    /**
     * Randomizes the enemy's stats based on the player's stats and current act.
     * This ensures the enemy's power scales with the player's progress in the game.
     */
    public void randomize() {
        Random rand = new Random();
        int playerMaxHp = GameLogic.player.getMaxHp();
        int playerStrength = GameLogic.player.getStrength();
        int playerDefense = GameLogic.player.getDefense();
        int playerSpeed = GameLogic.player.getSpeed();
        int currentAct = GameLogic.getCurrentAct();

        // Adjust the multipliers based on the current act and a random factor
        double healthMultiplier = 0.9 + rand.nextDouble() * 0.3 + (currentAct * 0.15);
        double strengthMultiplier = 0.85 + rand.nextDouble() * 0.25 + (currentAct * 0.12);
        double defenseMultiplier = 0.85 + rand.nextDouble() * 0.25 + (currentAct * 0.1);
        double speedMultiplier = 0.85 + rand.nextDouble() * 0.25 + (currentAct * 0.1);

        // Randomize the enemy's stats based on the player's stats and multipliers
        this.setMaxHp((int) (playerMaxHp * healthMultiplier));
        this.setDefense((int) (playerDefense * defenseMultiplier));
        this.setStrength((int) (playerStrength * strengthMultiplier));
        this.setSpeed((int) (playerSpeed * speedMultiplier));

        this.setHp(this.getMaxHp()); // Set the enemy's current HP to max HP
    }

    // Getter and setter for the enemy type
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
