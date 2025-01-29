import java.util.Random;

public class BastionBoss extends Enemy {

    public BastionBoss() {
        bossSequences(GameLogic.gameMap);
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

    private static final String[] types = new String[]{
            "🔥", "❄️", "☠️", "🔮"
    };

    private void bossSequences(GameMap map) {
        System.out.println(map.getCurrRegionName());


        if (GameLogic.getCurrentAct() == 1) {
            // Fire Boss for "Ashen Field"
            if (map.getCurrRegionName().equals("Ashen Field")) {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "1Name";
                this.setName(bossName);
                randomizeBossStats();
                // Add fire-specific abilities or attributes for this boss
            }

            // Ice Boss for "Frostfell Peaks"
            else if (map.getCurrRegionName().equals("The Emberwood")) {
                String bossType = types[1]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "2Name";
                this.setName(bossName);
                randomizeBossStats();
            }

            // Undead Boss for "Graveyard of the Lost"
            else if (map.getCurrRegionName().equals("Wailing Hollow")) {
                String bossType = types[2]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "3Name";
                this.setName(bossName);
                randomizeBossStats();
            }

            // Arcane Boss for "Aether Realm"
            else if (map.getCurrRegionName().equals("Cinderfall Cliffs")) {
                String bossType = types[2]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "4Name";
                this.setName(bossName);
                randomizeBossStats();
            }

            // Example for another region with random boss types
            else if (map.getCurrRegionName().equals("The Shivering Rift")) {
                String bossType = types[3]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "5Name";
                this.setName(bossName);
                randomizeBossStats();
            } else {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "6Name";
                this.setName(bossName);
                randomizeBossStats();
            }
        } else if (GameLogic.getCurrentAct() == 2) {
            if (map.getCurrRegionName().equals("Ashen Field")) {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "1Name";
                this.setName(bossName);
                randomizeBossStats();
                // Add fire-specific abilities or attributes for this boss
            }

            // Ice Boss for "Frostfell Peaks"
            else if (map.getCurrRegionName().equals("The Emberwood")) {
                String bossType = types[1]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "2Name";
                this.setName(bossName);
                randomizeBossStats();
            }

            // Undead Boss for "Graveyard of the Lost"
            else if (map.getCurrRegionName().equals("Wailing Hollow")) {
                String bossType = types[2]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "3Name";
                this.setName(bossName);
                randomizeBossStats();
            }

            // Arcane Boss for "Aether Realm"
            else if (map.getCurrRegionName().equals("Cinderfall Cliffs")) {
                String bossType = types[2]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "4Name";
                this.setName(bossName);
                randomizeBossStats();
            }

            // Example for another region with random boss types
            else if (map.getCurrRegionName().equals("The Shivering Rift")) {
                String bossType = types[3]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "5Name";
                this.setName(bossName);
                randomizeBossStats();
            } else {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "6Name";
                this.setName(bossName);
                randomizeBossStats();
            }
        } else if (GameLogic.getCurrentAct() == 3) {
            if (map.getCurrRegionName().equals("Ashen Field")) {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "1Name";
                this.setName(bossName);
                randomizeBossStats();
                // Add fire-specific abilities or attributes for this boss
            }

            // Ice Boss for "Frostfell Peaks"
            else if (map.getCurrRegionName().equals("The Emberwood")) {
                String bossType = types[1]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "2Name";
                this.setName(bossName);
                randomizeBossStats();
            }

            // Undead Boss for "Graveyard of the Lost"
            else if (map.getCurrRegionName().equals("Wailing Hollow")) {
                String bossType = types[2]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "3Name";
                this.setName(bossName);
                randomizeBossStats();
            }

            // Arcane Boss for "Aether Realm"
            else if (map.getCurrRegionName().equals("Cinderfall Cliffs")) {
                String bossType = types[2]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "4Name";
                this.setName(bossName);
                randomizeBossStats();
            }

            // Example for another region with random boss types
            else if (map.getCurrRegionName().equals("The Shivering Rift")) {
                String bossType = types[3]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "5Name";
                this.setName(bossName);
                randomizeBossStats();
            } else {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "6Name";
                this.setName(bossName);
                randomizeBossStats();
            }
        }
        else{
            if (map.getCurrRegionName().equals("Ashen Field")) {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "1Name";
                this.setName(bossName);
                randomizeBossStats();
                // Add fire-specific abilities or attributes for this boss
            }

            // Ice Boss for "Frostfell Peaks"
            else if (map.getCurrRegionName().equals("The Emberwood")) {
                String bossType = types[1]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "2Name";
                this.setName(bossName);
                randomizeBossStats();
            }

            // Undead Boss for "Graveyard of the Lost"
            else if (map.getCurrRegionName().equals("Wailing Hollow")) {
                String bossType = types[2]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "3Name";
                this.setName(bossName);
                randomizeBossStats();
            }

            // Arcane Boss for "Aether Realm"
            else if (map.getCurrRegionName().equals("Cinderfall Cliffs")) {
                String bossType = types[2]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "4Name";
                this.setName(bossName);
                randomizeBossStats();
            }

            // Example for another region with random boss types
            else if (map.getCurrRegionName().equals("The Shivering Rift")) {
                String bossType = types[3]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "5Name";
                this.setName(bossName);
                randomizeBossStats();
            } else {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "6Name";
                this.setName(bossName);
                randomizeBossStats();
            }
        }
    }


}
