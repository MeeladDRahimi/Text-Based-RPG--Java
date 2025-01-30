import java.util.Random;

/**
 * The FinalEnemy class represents the last boss, Davaiel, in the game.
 * This boss has multiple stages and changes stats upon reaching the second stage.
 */
public class FinalEnemy extends BastionBoss {
    private int stage;
    private boolean enraged;

    /**
     * Constructs the FinalEnemy with the default name and initializes its stats.
     * If the boss is in stage 1 and has zero HP upon creation, it immediately switches to stage 2.
     */
    public FinalEnemy() {
        this.setName("Davaiel");
        this.stage = 1;
        this.enraged = false;
        this.randomizeBossStats();

        // If the boss is already at zero HP, transition to stage 2 immediately
        if (this.stage == 1 && this.getHp() <= 0) {
            this.switchToStageTwo();
        }
    }

    /**
     * Randomizes the stats of the boss based on the player's stats and the current game act.
     *
     * <p>Precondition: GameLogic.player must be initialized and have valid stats.
     * <p>Postcondition: The boss's HP, strength, defense, and speed are set based on the game state.
     */
    private void randomizeBossStats() {
        Random rand = new Random();
        int playerMaxHp = GameLogic.player.getMaxHp();
        int playerStrength = GameLogic.player.getStrength();
        int playerDefense = GameLogic.player.getDefense();
        int playerSpeed = GameLogic.player.getSpeed();
        int currentAct = GameLogic.getCurrentAct();

        if (this.stage == 1) {
            // Base stats for stage 1
            this.setMaxHp(10);
            this.setHp(this.getMaxHp());
            this.setStrength(10);
            this.setDefense(10);
            this.setSpeed(10);
        } else {
            // Enhanced stats for stage 2, based on player's attributes and game act
            this.setMaxHp((int) (playerMaxHp * (1.5 + rand.nextDouble() * 0.8) * (1.0 + currentAct * 0.4)));
            this.setHp(this.getMaxHp());
            this.setStrength((int) (playerStrength * (1.8 + rand.nextDouble() * 0.6) * (1.0 + currentAct * 0.3)));
            this.setDefense((int) (playerDefense * (1.6 + rand.nextDouble() * 0.5) * (1.0 + currentAct * 0.25)));
            this.setSpeed(6);
        }
    }

    /**
     * Transitions the boss to stage 2, increasing its stats and marking it as enraged.
     *
     * <p>Precondition: The boss must be in stage 1 and have 0 or less HP.
     * <p>Postcondition: The boss enters stage 2, its stats are increased, and it becomes enraged.
     */
    public void switchToStageTwo() {
        System.out.println(this.getName() + " grows furious! Entering Stage 2...");
        GameLogic.anythingToContinue();
        this.stage = 2;
        this.enraged = true;
        this.randomizeBossStats();
    }

    /**
     * Gets the current stage of the boss.
     *
     * @return The current stage (1 or 2) of the boss.
     */
    public int getStage() {
        return this.stage;
    }
}
