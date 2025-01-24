import java.io.PrintStream;
import java.util.Random;

public class BattleSequence {
    private Player player;
    private Enemy enemy;
    private int originalPlayerDefense;
    private int originalPlayerHP;

    public BattleSequence(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.originalPlayerDefense = player.getDefense();
        this.originalPlayerHP = player.getMaxHp();
        this.startBattle();
    }

    private void startBattle() {
        System.out.println("A " + this.enemy.getName() + " appears!");

        while(this.player.getHp() > 0 && this.enemy.getHp() > 0) {
            this.playerTurn();
            if (this.enemy.getHp() > 0) {
                this.enemyTurn();
            }
        }

        this.determineOutcome();
        this.resetPlayerDefense();
        this.resetPlayerHP();
    }

    private void playerTurn() {
        System.out.println("\n" + this.player.getName() + "'s turn:");
        PrintStream var10000 = System.out;
        String var10001 = this.player.getName();
        var10000.println(var10001 + ": " + this.player.getHp() + "/" + this.player.getMaxHp() + " vs " + this.enemy.getName() + ": " + this.enemy.getHp() + "/" + this.enemy.getMaxHp());
        var10000 = System.out;
        int var3 = this.player.getEffectiveDamage();
        int var10002 = this.enemy.getDefense();
        var10000.println("1. Attack (" + (var3 - var10002 % 3) + " damage)");
        System.out.println("2. Defend (2 defense)");
        int choice = GameLogic.readInt("Choose an action: ", 2);
        if (choice == 1) {
            this.attack(this.player, this.enemy);
        } else if (choice == 2) {
            this.defend(this.player);
        }

    }

    private void enemyTurn() {
        System.out.println("\n" + this.enemy.getName() + "'s turn:");
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
            int damage = (attacker instanceof Player ? ((Player)attacker).getEffectiveDamage() : attacker.getStrength()) - (defender instanceof Player ? ((Player)defender).getEffectiveDefense() : defender.getDefense()) % 3;
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
    }

    private boolean attemptDodge(Character defender) {
        Random rand = new Random();
        int dodgeChance = defender.getSpeed() * 2;
        return rand.nextInt(100) < dodgeChance;
    }
}
