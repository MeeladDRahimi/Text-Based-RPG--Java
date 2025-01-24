import java.util.Random;

public class BoundBoss extends BastionBoss {
    public BoundBoss() {
        this.setName("Final " + generateBossName());
        this.randomizeBoundBossStats();
    }

    private void randomizeBoundBossStats() {
        Random rand = new Random();
        int playerMaxHp = GameLogic.player.getMaxHp();
        int playerStrength = GameLogic.player.getStrength();
        int playerDefense = GameLogic.player.getDefense();
        int playerSpeed = GameLogic.player.getSpeed();
        int currentAct = GameLogic.getCurrentAct();
        this.setMaxHp((int)((double)playerMaxHp * ((double)1.5F + rand.nextDouble() * 0.7) * ((double)1.0F + (double)currentAct * 0.15)));
        this.setHp(this.getMaxHp());
        this.setStrength((int)((double)playerStrength * ((double)2.0F + rand.nextDouble() * (double)1.0F) * ((double)1.0F + (double)currentAct * 0.15)));
        this.setDefense((int)((double)playerDefense * ((double)2.0F + rand.nextDouble() * (double)1.0F) * ((double)1.0F + (double)currentAct * 0.15)));
        this.setSpeed((int)((double)playerSpeed * ((double)1.5F + rand.nextDouble() * 0.7) * ((double)1.0F + (double)currentAct * 0.15)));
    }
}
