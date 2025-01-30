import java.util.Random;

public class BoundBoss extends BastionBoss {
    public BoundBoss() {

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

    private static final String[] types = new String[]{
            "🔥", "❄️", "☠️", "🔮"
    };

    private void bossSequence(){
        switch(GameLogic.getCurrentAct()){
            case 1:
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "Vaelith, Warden of the Eternal Chain";
                this.setName(bossName);
                randomizeBoundBossStats();
            case 2:
                bossType = types[2]; // Fire type
                this.setType(bossType);
                bossName = bossType + "Draegor, The Undying Sentinel";
                this.setName(bossName);
                randomizeBoundBossStats();
            case 3:
                bossType = types[3]; // Fire type
                this.setType(bossType);
                bossName = bossType + "Zyphiron, Harbinger of Binding";
                this.setName(bossName);
                randomizeBoundBossStats();
            case 4:
                bossType = types[1]; // Fire type
                this.setType(bossType);
                bossName = bossType + "Morvax, The Ice Giant";
                this.setName(bossName);
                randomizeBoundBossStats();
        }
    }
}
