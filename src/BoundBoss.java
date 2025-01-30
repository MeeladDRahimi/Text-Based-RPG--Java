import java.util.Random;

/**
 * Represents a Bound Boss in the game, a special type of boss that is tied to specific locations (acts).
 * The stats of the Bound Boss are randomized based on the player's progress in the game.
 */
public class BoundBoss extends BastionBoss {

    /**
     * Default constructor for BoundBoss.
     * Initializes the Bound Boss with randomized stats based on the player's current stats.
     */
    public BoundBoss() {
        this.randomizeBoundBossStats(); // Randomize the Bound Boss stats
    }

    /**
     * Randomizes the Bound Boss stats based on the player's stats and current act.
     * The boss's strength, defense, speed, and HP are scaled according to the player's attributes and the current act.
     */
    private void randomizeBoundBossStats() {
        Random rand = new Random();
        int playerMaxHp = GameLogic.player.getMaxHp();
        int playerStrength = GameLogic.player.getStrength();
        int playerDefense = GameLogic.player.getDefense();
        int playerSpeed = GameLogic.player.getSpeed();
        int currentAct = GameLogic.getCurrentAct();

        // Randomly scale Bound Boss stats based on the player's stats and current act
        this.setMaxHp((int)((double)playerMaxHp * ((double)1.5F + rand.nextDouble() * 0.7) * ((double)1.0F + (double)currentAct * 0.15)));
        this.setHp(this.getMaxHp()); // Set current HP to max HP
        this.setStrength((int)((double)playerStrength * ((double)2.0F + rand.nextDouble() * (double)1.0F) * ((double)1.0F + (double)currentAct * 0.15)));
        this.setDefense((int)((double)playerDefense * ((double)2.0F + rand.nextDouble() * (double)1.0F) * ((double)1.0F + (double)currentAct * 0.15)));
        this.setSpeed((int)((double)playerSpeed * ((double)1.5F + rand.nextDouble() * 0.7) * ((double)1.0F + (double)currentAct * 0.15)));
    }

    // Predefined types for Bound Bosses, each representing a different element
    private static final String[] types = new String[]{
            "🔥", "❄️", "☠️", "🔮"
    };

    /**
     * Defines the boss sequence based on the current act of the game.
     * This method adjusts the boss type, name, and stats depending on the act.
     */
    private void bossSequence() {
        String bossType;
        String bossName;

        // Set the boss type and name based on the current act
        switch(GameLogic.getCurrentAct()) {
            case 1:
                bossType = types[0]; // Fire type
                this.setType(bossType);
                bossName = bossType + "Vaelith, Warden of the Eternal Chain";
                this.setName(bossName);
                randomizeBoundBossStats(); // Randomize the Bound Boss stats
                break; // Added break to avoid falling through cases

            case 2:
                bossType = types[2]; // Undead type
                this.setType(bossType);
                bossName = bossType + "Draegor, The Undying Sentinel";
                this.setName(bossName);
                randomizeBoundBossStats(); // Randomize the Bound Boss stats
                break;

            case 3:
                bossType = types[3]; // Arcane type
                this.setType(bossType);
                bossName = bossType + "Zyphiron, Harbinger of Binding";
                this.setName(bossName);
                randomizeBoundBossStats(); // Randomize the Bound Boss stats
                break;

            case 4:
                bossType = types[1]; // Ice type
                this.setType(bossType);
                bossName = bossType + "Morvax, The Ice Giant";
                this.setName(bossName);
                randomizeBoundBossStats(); // Randomize the Bound Boss stats
                break;

            default:
                // Handle cases when the current act doesn't match any predefined case
                bossType = "⚡"; // Default type if act is unknown
                this.setType(bossType);
                bossName = "Default Bound Boss";
                this.setName(bossName);
                break;
        }
    }
}
