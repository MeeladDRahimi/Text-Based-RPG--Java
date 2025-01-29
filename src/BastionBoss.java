import java.util.Random;

public class BastionBoss extends Enemy {
    private static final String[] bossAdjectives = new String[]{"Mighty", "Furious", "Hellish"};
    private static final String[] bossName = new String[]{"Azazel", "Leviathan", "Abaddon", "Legion", "Baal", "Mephisto", "Diablo", "Azmodan", "Urzael", "Rakanoth", "Astaroth", "Lilith"};

    public BastionBoss() {
        this.setName(generateBossName());
        this.randomizeBossStats();
    }

    static String generateBossName() {
        Random rand = new Random();
        String adjective = bossAdjectives[rand.nextInt(bossAdjectives.length)];
        String name = bossName[rand.nextInt(bossName.length)];
        return adjective + " " + name;
    }

    private void randomizeBossStats() {
        Random rand = new Random();
        int playerMaxHp = GameLogic.player.getMaxHp();
        int playerStrength = GameLogic.player.getStrength();
        int playerDefense = GameLogic.player.getDefense();
        int playerSpeed = GameLogic.player.getSpeed();
        int currentAct = GameLogic.getCurrentAct();
        this.setMaxHp((int)((double)playerMaxHp * (0.9 + rand.nextDouble() * (double)0.5F) * ((double)1.0F + (double)currentAct * (double)0.25F)));
        this.setHp(this.getMaxHp());
        this.setStrength((int)((double)playerStrength * (1.3 + rand.nextDouble() * (double)0.5F) * ((double)1.0F + (double)currentAct * 0.2)));
        this.setDefense((int)((double)playerDefense * (1.3 + rand.nextDouble() * (double)0.5F) * ((double)1.0F + (double)currentAct * 0.2)));
        this.setSpeed((int)((double)playerSpeed * (0.8 + rand.nextDouble() * (double)0.5F) * ((double)1.0F + (double)currentAct * 0.1)));
    }
}
