import java.util.Random;

public class Enemy extends Character {
    private static final String[] adjectives = new String[]{"Hairy", "Sickly", "Stinky", "Fat", "Putrid", "Fierce", "Scary", "Bald", "Ugly"};
    private static final String[] enemyNames = new String[]{"Zombie", "Monster", "Sally", "Harry", "Bob", "Demon", "Demon-Chicken", "Pig", "Cyclops"};

    public Enemy() {
        super(randomName(), 100, 100, 0, 10, 10, 10, 0);
        this.randomize();
    }

    private static String randomName() {
        Random rand = new Random();
        String adjective = adjectives[rand.nextInt(adjectives.length)];
        String name = enemyNames[rand.nextInt(enemyNames.length)];
        return adjective + " " + name;
    }

    public void randomize() {
        Random rand = new Random();
        int playerMaxHp = GameLogic.player.getMaxHp();
        int playerStrength = GameLogic.player.getStrength();
        int playerDefense = GameLogic.player.getDefense();
        int playerSpeed = GameLogic.player.getSpeed();
        int currentAct = GameLogic.getCurrentAct();
        this.setMaxHp((int)((double)playerMaxHp * (0.8 + rand.nextDouble() * 0.4) * ((double)1.0F + (double)currentAct * 0.2)));
        this.setDefense((int)((double)playerDefense * (0.8 + rand.nextDouble() * 0.4) * ((double)1.0F + (double)currentAct * 0.1)));
        this.setStrength((int)((double)playerStrength * (0.8 + rand.nextDouble() * 0.4) * ((double)1.0F + (double)currentAct * 0.15)));
        this.setSpeed((int)((double)playerSpeed * (0.8 + rand.nextDouble() * 0.4) * ((double)1.0F + (double)currentAct * 0.1)));
        this.setHp(this.getMaxHp());
    }
}
