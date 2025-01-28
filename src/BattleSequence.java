import com.sun.management.GarbageCollectionNotificationInfo;

import java.io.PrintStream;
import java.util.Random;

public class BattleSequence {
    private Player player;
    private Enemy enemy;
    private int originalPlayerDefense;
    private int originalPlayerHP;
    private int playerHearts, enemyHearts;

    public BattleSequence(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.originalPlayerDefense = player.getDefense();
        this.originalPlayerHP = player.getMaxHp();
        calculatePlayerBHearts(player);
        calculateEnemyBHearts(enemy);
        this.startBattle();
        resetPlayerHP();

    }

    private void startBattle() {
        System.out.println(this.enemy.getName() + " appears!");

        while (this.player.getHp() > 0 && this.enemy.getHp() > 0) {
            this.playerTurn();
            if (this.enemy.getHp() > 0) {
                this.enemyTurn();
            }
        }
        this.determineOutcome();
        this.resetPlayerDefense();
    }

    private void playerTurn() {
        Story.boldText();
        System.out.println("\n" + this.player.getName() + "'s turn:");
        Story.resetTextColor();
        System.out.println("1. Attack (" + (this.player.getEffectiveDamage() - this.enemy.getDefense() % 3) + " damage)");
        System.out.println("2. Defend (2 defense)");
        printHealthBars(this.player, this.enemy);
        Story.resetTextColor();
        int choice = GameLogic.readInt("Choose an action: ", 2);
        if (choice == 1) {
            this.attack(this.player, this.enemy);
        } else if (choice == 2) {
            this.defend(this.player);
        }

    }

    private void enemyTurn() {
        Story.boldText();
        System.out.println("\n" + this.enemy.getName() + "'s turn:");
        Story.resetTextColor();
        Random rand = new Random();
        int action = rand.nextInt(2);
        if (action == 0) {
            this.attack(this.enemy, this.player);
        } else {
            this.defend(this.enemy);
        }

    }

    private void attack(Character attacker, Character defender) {
        if (this.attemptDodge(defender)) {
            System.out.println(defender.getName() + " dodged the attack!");
        } else {
            int damage = (attacker instanceof Player ? ((Player) attacker).getEffectiveDamage() : attacker.getStrength()) - (defender instanceof Player ? ((Player) defender).getEffectiveDefense() : defender.getDefense()) % 3;
            damage = Math.max(damage, 0);
            defender.setHp(defender.getHp() - damage);
            PrintStream var10000 = System.out;
            String var10001 = attacker.getName();
            var10000.println(var10001 + " attacks " + defender.getName() + " for " + damage + " damage.");
        }

    }

    private void defend(Character character) {
        character.setDefense(character.getDefense() + 2);
        System.out.println(character.getName() + " defends, increasing defense by 2");
    }

    private void determineOutcome() {
        if (this.player.getHp() <= 0) {
            System.out.println(this.player.getName() + " has been defeated! You faint.");
        } else if (this.enemy.getHp() <= 0) {
            System.out.println(this.enemy.getName() + " has been defeated!");
        }

    }

    private void resetPlayerDefense() {
        this.player.setDefense(this.originalPlayerDefense);
        System.out.println(this.player.getName() + "'s defense has worn off.");
    }

    private void resetPlayerHP() {
        this.player.setHp(this.originalPlayerHP);
        System.out.println(this.player.getName() + "'s health eventually recovers to full health.");
        GameLogic.anythingToContinue();
    }

    private boolean attemptDodge(Character defender) {
        Random rand = new Random();
        int dodgeChance = defender.getSpeed() * 2;
        return rand.nextInt(100) < dodgeChance;
    }

    private String buildBar(Player player){
        Story.boldText();
        StringBuilder bar = new StringBuilder("|");
        Story.resetTextColor();
        Story.boldRedTextColor();
        int hearts = (int)Math.ceil((double) player.getHp()/player.getMaxHp() * 10);
        for(int i = 0; i < hearts; i++){
            bar.append("\u2665");
        }
        for(int i = hearts; i < 10 ;i++){
            bar.append("-");
        }
        bar.append("|");
        return bar.toString();
    }

    private String buildBar(Enemy enemy){
        StringBuilder bar = new StringBuilder("|");
        int hearts = (int)Math.ceil((double) enemy.getHp()/enemy.getMaxHp() * 10);
        for(int i = 0; i < hearts; i++){
            bar.append("\u2661");
        }
        for(int i = hearts; i < 10;i++){
            bar.append("-");
        }
        bar.append("|");
        return bar.toString();
    }

    private  String getPlayerBar(Player player){
        return buildBar(player);
    }

    private  String getEnemyBar(Enemy enemy){
        return buildBar(enemy);
    }

    private void printHealthBars(Player player, Enemy enemy){
        System.out.println(getPlayerBar(player) + "\t" + getEnemyBar(enemy));
        System.out.println(player.getName() + ": " + player.getHp() + "/" + player.getMaxHp() +
                "\t" + enemy.getName() + ": " + enemy.getHp() + "/" + enemy.getMaxHp());
    }

    private void calculatePlayerBHearts(Player player){
        double heartValue = (double)player.getMaxHp() / 10;
        playerHearts = (int)Math.ceil(player.getHp()/heartValue);
    }

    private void calculateEnemyBHearts(Enemy enemy){
        double heartValue = (double)enemy.getMaxHp() / 10;
        enemyHearts = (int)Math.ceil(enemy.getHp()/heartValue);
    }

}
