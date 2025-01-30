import java.util.Random;

/**
 * Class representing the sequence of events during a battle between the player and an enemy.
 */
public class BattleSequence {
    private Player player;
    private Enemy enemy;
    private int originalPlayerDefense;
    private int originalPlayerHP;
    private boolean enemyStaggered = false;
    private boolean playerGetsExtraTurn = false;
    private String playerElement = "None";
    private int imbuementTurnsLeft = 0;

    /**
     * Constructor to initialize the battle sequence between a player and an enemy.
     * @param player The player in the battle.
     * @param enemy The enemy the player is fighting.
     */
    public BattleSequence(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.originalPlayerDefense = player.getDefense();
        this.originalPlayerHP = player.getMaxHp();
        startBattle();
        resetPlayerHP();
    }

    /**
     * Starts the battle loop where both the player and the enemy take turns until one of them is defeated.
     */
    private void startBattle() {
        System.out.println(this.enemy.getName() + " appears!");

        // Battle loop: runs as long as both the player and the enemy have health remaining
        while (this.player.getHp() > 0 && this.enemy.getHp() > 0) {
            playerTurn();
            if (playerGetsExtraTurn) {
                playerGetsExtraTurn = false;
                playerTurn(); // Player gets another turn if parry is successful
            }
            if (this.enemy.getHp() > 0) {
                enemyTurn();
            }
        }
        determineOutcome();
        resetPlayerDefense();
    }

    /**
     * Handles the player's turn, where they can choose from several actions.
     */
    private void playerTurn() {
        // Decreases imbuement turns and resets player element if it expires
        if (imbuementTurnsLeft > 0) {
            imbuementTurnsLeft--;
            if (imbuementTurnsLeft == 0) {
                playerElement = "None";
                System.out.println("Your elemental imbuement has worn off...");
            }
        }

        System.out.println("\n" + player.getName() + "'s turn:");
        // Present the player with different action choices
        System.out.println("1. Light Attack (High Accuracy, Low Damage)");
        System.out.println("2. Medium Attack (Balanced)");
        System.out.println("3. Heavy Attack (High Damage, Low Accuracy)");
        System.out.println("4. Parry (Negates attack and grants an extra turn if successful)");
        System.out.println("5. Imbue Weapon with Element (Lasts 2 Turns)");
        printHealthBars();

        // Player makes a choice
        int choice = GameLogic.readInt("Choose an action: ", 5);
        switch (choice) {
            case 1 -> attack(player, enemy, "light");
            case 2 -> attack(player, enemy, "medium");
            case 3 -> attack(player, enemy, "heavy");
            case 4 -> parry();
            case 5 -> this.imbueWeapon();
        }
    }

    /**
     * Imbues the player's weapon with a selected elemental energy.
     */
    private void imbueWeapon() {
        System.out.println("Choose an element to imbue your weapon with:");
        System.out.println("1. 🔥");
        System.out.println("2. ❄️");
        System.out.println("3. ☠️");
        System.out.println("4. 🔮");

        // Player selects the elemental type
        int choice = GameLogic.readInt("Select an element: ", 4);
        switch (choice) {
            case 1 -> playerElement = "🔥";
            case 2 -> playerElement = "❄️";
            case 3 -> playerElement = "☠️";
            case 4 -> playerElement = "🔮";
        }

        imbuementTurnsLeft = 3; // The imbuement lasts for 3 turns
        System.out.println("Your weapon is now imbued with " + playerElement + " energy for 2 turns!");
    }

    /**
     * Handles the enemy's turn, where the enemy can choose from different attack types.
     */
    private void enemyTurn() {
        System.out.println("\n" + enemy.getName() + "'s turn:");
        if (enemyStaggered) {
            System.out.println(enemy.getName() + " is staggered! All attacks have heightened accuracy!");
            enemyStaggered = false; // Reset staggered status after the enemy's turn
        }

        Random rand = new Random();
        int action = rand.nextInt(3);
        // Randomly chooses an attack type for the enemy
        if (action == 0) attack(enemy, player, "light");
        else if (action == 1) attack(enemy, player, "medium");
        else attack(enemy, player, "heavy");
    }

    /**
     * Executes an attack, calculating damage, accuracy, and applying elemental effects.
     * @param attacker The character performing the attack.
     * @param defender The character receiving the attack.
     * @param attackType The type of attack (light, medium, heavy).
     */
    private void attack(Character attacker, Character defender, String attackType) {
        int baseDamage = attacker.getStrength();
        int accuracy = 100;

        // Adjust damage and accuracy based on attack type and whether the attacker is the player or the enemy
        if (attacker instanceof Player) {
            baseDamage = player.getEffectiveDamage();
            switch (attackType) {
                case "light" -> {
                    baseDamage = Math.max(baseDamage / 2, 1);
                    accuracy = enemyStaggered ? 100 : 95;
                }
                case "medium" -> {
                    baseDamage = (int) (baseDamage * 0.75);
                    accuracy = enemyStaggered ? 100 : 85;
                }
                case "heavy" -> {
                    baseDamage *= 2;
                    accuracy = enemyStaggered ? 90 : 60;
                }
            }
        } else {
            switch (attackType) {
                case "light" -> {
                    baseDamage = Math.max(baseDamage / 2, 1);
                    accuracy = enemyStaggered ? 100 : 95;
                }
                case "medium" -> {
                    baseDamage = (int) (baseDamage * 0.75);
                    accuracy = enemyStaggered ? 100 : 85;
                }
                case "heavy" -> {
                    baseDamage *= 2;
                    accuracy = enemyStaggered ? 90 : 60;
                }
            }
        }

        // Apply elemental effectiveness if the player is attacking
        if (attacker instanceof Player && !playerElement.equals("None")) {
            baseDamage = applyElementalEffectiveness(baseDamage, enemy.getType());
        }

        // Perform attack if the accuracy check passes
        if (new Random().nextInt(100) < accuracy) {
            int damage = Math.max(baseDamage - defender.getDefense(), 1);
            if (defender instanceof Player) {
                damage = Math.max(baseDamage - ((Player) defender).getEffectiveDefense(), 1);
                defender.setHp(defender.getHp() - damage);
                System.out.println(attacker.getName() + " used a " + attackType + " attack dealing " + damage + " damage!");
            } else if (defender instanceof Enemy) {
                defender.setHp(defender.getHp() - damage);
                System.out.println(attacker.getName() + " used a " + attackType + " attack dealing " + damage + " damage!");
            }
        } else {
            System.out.println(attacker.getName() + " missed their attack!");
        }
    }

    /**
     * Applies elemental effectiveness to the damage based on the player's weapon element and the enemy's type.
     * @param baseDamage The base damage value.
     * @param enemyType The type of enemy being attacked.
     * @return The modified damage based on elemental effectiveness.
     */
    private int applyElementalEffectiveness(int baseDamage, String enemyType) {
        if (isSuperEffective(playerElement, enemyType)) {
            System.out.println("It's super effective!");
            return (int) (baseDamage * 1.5);
        } else if (isNotVeryEffective(playerElement, enemyType)) {
            System.out.println("It's not very effective...");
            return (int) (baseDamage * 0.75);
        }
        return baseDamage;
    }

    /**
     * Determines if the player's attack is super effective against the enemy.
     * @param attackType The type of the player's attack.
     * @param enemyType The type of the enemy.
     * @return True if the attack is super effective, false otherwise.
     */
    private boolean isSuperEffective(String attackType, String enemyType) {
        return (attackType.contains("🔥") && enemyType.contains("❄️")) ||
                (attackType.contains("❄️") && enemyType.contains("☠️")) ||
                (attackType.contains("☠️") && enemyType.contains("🔮")) ||
                (attackType.contains("🔮") && enemyType.contains("🔥"));
    }

    /**
     * Determines if the player's attack is not very effective against the enemy.
     * @param attackType The type of the player's attack.
     * @param enemyType The type of the enemy.
     * @return True if the attack is not very effective, false otherwise.
     */
    private boolean isNotVeryEffective(String attackType, String enemyType) {
        return (attackType.equals("🔥") && enemyType.contains("🔮")) ||
                (attackType.equals("❄️") && enemyType.contains("🔥")) ||
                (attackType.equals("☠️") && enemyType.contains("❄️")) ||
                (attackType.equals("🔮") && enemyType.contains("☠️"));
    }

    /**
     * Performs a parry action, where the player can negate the enemy's attack and gain an extra turn.
     */
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

    /**
     * Determines the outcome of the battle, printing whether the player or enemy was defeated.
     */
    private void determineOutcome() {
        if (player.getHp() <= 0) {
            System.out.println(player.getName() + " has been defeated! You faint.");
        } else if (enemy.getHp() <= 0) {
            System.out.println(enemy.getName() + " has been defeated!");
        }
    }

    /**
     * Resets the player's defense to its original value.
     */
    private void resetPlayerDefense() {
        player.setDefense(originalPlayerDefense);
        System.out.println(player.getName() + "'s defense has returned to normal.");
    }

    /**
     * Resets the player's HP to its original value.
     */
    private void resetPlayerHP() {
        player.setHp(originalPlayerHP);
        System.out.println(player.getName() + "'s health is restored to full.");
        GameLogic.anythingToContinue();
    }

    /**
     * Builds a visual representation of the health bar for a character.
     * @param character The character whose health bar is being built.
     * @param symbol The symbol used for the health bar.
     * @return The string representation of the character's health bar.
     */
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

    /**
     * Prints the health bars of the player and the enemy.
     */
    private void printHealthBars() {
        System.out.println(buildBar(player, "\u2665") + "\t" + buildBar(enemy, "\u2661"));
        if(!playerElement.equals("None")){
            System.out.print(playerElement);
        }
        System.out.println(player.getName() + ": " + player.getHp() + "/" + player.getMaxHp() +
                "\t" + enemy.getName() + ": " + enemy.getHp() + "/" + enemy.getMaxHp());
    }
}
