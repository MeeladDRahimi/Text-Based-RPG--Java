
import java.util.Random;

public class FinalEnemy extends BastionBoss {
    private int stage;
    private boolean enraged;

    public FinalEnemy() {
        this.setName("Davaiel");
        this.stage = 1;
        this.enraged = false;
        this.randomizeBossStats();
        if (this.stage == 1 && this.getHp() <= 0) {
            this.switchToStageTwo();
        }

    }

    private void randomizeBossStats() {
        Random rand = new Random();
        int playerMaxHp = GameLogic.player.getMaxHp();
        int playerStrength = GameLogic.player.getStrength();
        int playerDefense = GameLogic.player.getDefense();
        int playerSpeed = GameLogic.player.getSpeed();
        int currentAct = GameLogic.getCurrentAct();
        if (this.stage == 1) {
            this.setMaxHp(10);
            this.setHp(this.getMaxHp());
            this.setStrength(10);
            this.setDefense(10);
            this.setSpeed(10);
        } else {
            this.setMaxHp((int)((double)playerMaxHp * ((double)1.5F + rand.nextDouble() * 0.8) * ((double)1.0F + (double)currentAct * 0.4)));
            this.setHp(this.getMaxHp());
            this.setStrength((int)((double)playerStrength * (1.8 + rand.nextDouble() * 0.6) * ((double)1.0F + (double)currentAct * 0.3)));
            this.setDefense((int)((double)playerDefense * (1.6 + rand.nextDouble() * (double)0.5F) * ((double)1.0F + (double)currentAct * (double)0.25F)));
            this.setSpeed(6);
        }

    }

    public void switchToStageTwo() {
        System.out.println(this.getName() + " grows furious! Entering Stage 2...");
        GameLogic.anythingToContinue();
        this.stage = 2;
        this.enraged = true;
        this.randomizeBossStats();
    }

    public int getStage() {
        return this.stage;
    }
}
