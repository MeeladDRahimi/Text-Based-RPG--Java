import java.io.PrintStream;
import java.util.Random;

public class BattleSequence {
    private Player player;
    private Enemy enemy;
    private int originalPlayerDefense;
    private int originalPlayerHP;
    private boolean enemyStaggered = false;
    private boolean playerGetsExtraTurn = false;
    private String playerElement = "None";
    private int imbuementTurnsLeft = 0;


    public BattleSequence(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.originalPlayerDefense = player.getDefense();
        this.originalPlayerHP = player.getMaxHp();
        startBattle();
        resetPlayerHP();
    }

    private void startBattle() {
        System.out.println(this.enemy.getName() + " appears!");

        while (this.player.getHp() > 0 && this.enemy.getHp() > 0) {
            playerTurn();
            if (playerGetsExtraTurn) {
                playerGetsExtraTurn = false;
                playerTurn();
            }
            if (this.enemy.getHp() > 0) {
                enemyTurn();
            }
        }
        determineOutcome();
        resetPlayerDefense();
    }

    private void playerTurn() {
        System.out.println("\n" + player.getName() + "'s turn:");
        System.out.println("1. Light Attack (High Accuracy, Low Damage)");
        System.out.println("2. Medium Attack (Balanced)");
        System.out.println("3. Heavy Attack (High Damage, Low Accuracy)");
        System.out.println("4. Parry (Negates attack and grants an extra turn if successful)");
        System.out.println("5. Imbue Weapon with Element (Lasts 2 Turns)");
        printHealthBars();

        if(imbuementTurnsLeft > 0){
            imbuementTurnsLeft--;
            if(imbuementTurnsLeft == 0){
                playerElement = "None";
                System.out.println("Your elemental imbuement has worn off...");
            }
        }

        int choice = GameLogic.readInt("Choose an action: ", 5);
        switch (choice) {
            case 1 -> attack(player, enemy, "light");
            case 2 -> attack(player, enemy, "medium");
            case 3 -> attack(player, enemy, "heavy");
            case 4 -> parry();
            case 5 -> this.imbueWeapon();
        }
    }

    private void imbueWeapon(){
        System.out.println("Choose an element to imbue your weapon with:");
        System.out.println("1. 🔥");
        System.out.println("2. ❄️");
        System.out.println("3. ☠️");
        System.out.println("4. 🔮");

        int choice = GameLogic.readInt("Select an element: ", 4);
        switch(choice){
            case 1 -> playerElement = "Fire";
            case 2 -> playerElement = "Ice";
            case 3 -> playerElement = "Undead";
            case 4 -> playerElement = "Arcane";
        }

        imbuementTurnsLeft = 3;
        System.out.println("Your weapon is now imbued with " + playerElement + " energy for 2 turns!");
    }

    private void enemyTurn() {
        System.out.println("\n" + enemy.getName() + "'s turn:");
        if (enemyStaggered) {
            System.out.println(enemy.getName() + " is staggered! All attacks have heightened accuracy!");
            enemyStaggered = false;
        }

        Random rand = new Random();
        int action = rand.nextInt(3);
        if (action == 0) attack(enemy, player, "light");
        else if (action == 1) attack(enemy, player, "medium");
        else attack(enemy, player, "heavy");
    }

    private void attack(Character attacker, Character defender, String attackType) {
        int baseDamage = attacker.getStrength();
        int accuracy = 100;

        switch (attackType) {
            case "light" -> { baseDamage = Math.max(baseDamage / 2, 1); accuracy = enemyStaggered ? 100 : 95; }
            case "medium" -> { baseDamage = (int)(baseDamage * 0.75); accuracy = enemyStaggered ? 100 : 85; }
            case "heavy" -> { baseDamage *= 2; accuracy = enemyStaggered ? 90 : 60; }
        }

        if (new Random().nextInt(100) < accuracy) {
            int damage = Math.max(baseDamage - defender.getDefense(), 1);
            defender.setHp(defender.getHp() - damage);
            System.out.println(attacker.getName() + " used a " + attackType + " attack dealing " + damage + " damage!");
        } else {
            System.out.println(attacker.getName() + " missed their attack!");
        }
    }

    private void parry() {
        System.out.println(player.getName() + " prepares to parry...");
        if (new Random().nextInt(100) < 50) {
            System.out.println("Successful parry! " + enemy.getName() + " is staggered and vulnerable next turn!");
            enemyStaggered = true;
            playerGetsExtraTurn = true;
        } else {
            System.out.println("Parry failed! " + player.getName() + " takes full damage next turn.");
        }
    }

    private void determineOutcome() {
        if (player.getHp() <= 0) {
            System.out.println(player.getName() + " has been defeated! You faint.");
        } else if (enemy.getHp() <= 0) {
            System.out.println(enemy.getName() + " has been defeated!");
        }
    }

    private void resetPlayerDefense() {
        player.setDefense(originalPlayerDefense);
        System.out.println(player.getName() + "'s defense has returned to normal.");
    }

    private void resetPlayerHP() {
        player.setHp(originalPlayerHP);
        System.out.println(player.getName() + "'s health is restored to full.");
        GameLogic.anythingToContinue();
    }

    private String buildBar(Character character, String symbol) {
        StringBuilder bar = new StringBuilder("|");
        int hearts = (int) Math.ceil((double) character.getHp() / character.getMaxHp() * 10);
        for (int i = 0; i < hearts; i++) {
            bar.append(symbol);
        }
        for (int i = hearts; i < 10; i++) {
            bar.append("-");
        }
        bar.append("|");
        return bar.toString();
    }

    private void printHealthBars() {
        System.out.println(buildBar(player, "\u2665") + "\t" + buildBar(enemy, "\u2661"));
        System.out.println(player.getName() + ": " + player.getHp() + "/" + player.getMaxHp() +
                "\t" + enemy.getName() + ": " + enemy.getHp() + "/" + enemy.getMaxHp());
    }
}
