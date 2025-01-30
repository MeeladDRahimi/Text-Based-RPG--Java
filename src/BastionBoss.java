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
            if (map.getCurrRegionName().equals("Ashen Field")) {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "Pyre Lord Surtur";
                this.setName(bossName);
                randomizeBossStats();
                // Add fire-specific abilities or attributes for this boss
            }

            else if (map.getCurrRegionName().equals("The Emberwood")) {
                String bossType = types[1]; // ice type
                this.setType(bossType);
                String bossName = bossType + "Frost Warden Ignis";
                this.setName(bossName);
                randomizeBossStats();
            }

            else if (map.getCurrRegionName().equals("Wailing Hollow")) {
                String bossType = types[2]; // undead type
                this.setType(bossType);
                String bossName = bossType + "Skeleton Queen Merridia";
                this.setName(bossName);
                randomizeBossStats();
            }

            else if (map.getCurrRegionName().equals("Cinderfall Cliffs")) {
                String bossType = types[2]; // undead type
                this.setType(bossType);
                String bossName = bossType + "Plague Bringer Dermese";
                this.setName(bossName);
                randomizeBossStats();
            }

            else if (map.getCurrRegionName().equals("The Shivering Rift")) {
                String bossType = types[3]; // Arcane type
                this.setType(bossType);
                String bossName = bossType + "Banshee Queen";
                this.setName(bossName);
                randomizeBossStats();
            } else {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "Tarfiend Belphegor";
                this.setName(bossName);
                randomizeBossStats();
            }
        } else if (GameLogic.getCurrentAct() == 2) {
            if (map.getCurrRegionName().equals("The Crag of Souls")) {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "Diablo";
                this.setName(bossName);
                randomizeBossStats();
                // Add fire-specific abilities or attributes for this boss
            }

            else if (map.getCurrRegionName().equals("Hellfire Caverns")) {
                String bossType = types[1]; // ice type
                this.setType(bossType);
                String bossName = bossType + "Borealis, the Eternal Blizzard";
                this.setName(bossName);
                randomizeBossStats();
            }

            else if (map.getCurrRegionName().equals("Blighted Ash Wastes")) {
                String bossType = types[2]; // undead type
                this.setType(bossType);
                String bossName = bossType + "Morgrim, the Soul Devourer";
                this.setName(bossName);
                randomizeBossStats();
            }

            else if (map.getCurrRegionName().equals("The Searing Plains")) {
                String bossType = types[2]; // undead type
                this.setType(bossType);
                String bossName = bossType + "Malrik, the Plague Revenant";
                this.setName(bossName);
                randomizeBossStats();
            }

            else if (map.getCurrRegionName().equals("Infernal Spire")) {
                String bossType = types[3]; // arcane type
                this.setType(bossType);
                String bossName = bossType + "Umbra, the Riftborn Magnus";
                this.setName(bossName);
                randomizeBossStats();
            } else {
                String bossType = types[0]; // fire type
                this.setType(bossType);
                String bossName = bossType + "Ignisfang, the Hellborn Wyrm";
                this.setName(bossName);
                randomizeBossStats();
            }
        } else if (GameLogic.getCurrentAct() == 3) {
            if (map.getCurrRegionName().equals("The Cinderscape")) {
                String bossType = types[0]; // fire type
                this.setType(bossType);
                String bossName = bossType + "Infernis";
                this.setName(bossName);
                randomizeBossStats();
                // Add fire-specific abilities or attributes for this boss
            }

            else if (map.getCurrRegionName().equals("Fiendish Depths")) {
                String bossType = types[1]; // ice type
                this.setType(bossType);
                String bossName = bossType + "Cryovex";
                this.setName(bossName);
                randomizeBossStats();
            }

            else if (map.getCurrRegionName().equals("Demonforge Citadel")) {
                String bossType = types[2]; // undead type
                this.setType(bossType);
                String bossName = bossType + "Thanadrax";
                this.setName(bossName);
                randomizeBossStats();
            }

            else if (map.getCurrRegionName().equals("The Infernal Crucible")) {
                String bossType = types[2]; // undead type
                this.setType(bossType);
                String bossName = bossType + "Gilded Thanadrax";
                this.setName(bossName);
                randomizeBossStats();
            }

            else if (map.getCurrRegionName().equals("Lavaheart Basin")) {
                String bossType = types[3]; // arcane type
                this.setType(bossType);
                String bossName = bossType + "Vaelith, the Arcane Lich";
                this.setName(bossName);
                randomizeBossStats();
            } else {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "Moltres, Copyright Blazer";
                this.setName(bossName);
                randomizeBossStats();
            }
        }
        else{
            if (map.getCurrRegionName().equals("The Blazing Chasm")) {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "Mephisto";
                this.setName(bossName);
                randomizeBossStats();
                // Add fire-specific abilities or attributes for this boss
            }

            else if (map.getCurrRegionName().equals("The Blood Furnace")) {
                String bossType = types[1]; // ice type
                this.setType(bossType);
                String bossName = bossType + "Frostreaver, the Rimeborn Warlord";
                this.setName(bossName);
                randomizeBossStats();
            }

            else if (map.getCurrRegionName().equals("The Screaming Pit")) {
                String bossType = types[2]; // undead type
                this.setType(bossType);
                String bossName = bossType + "Malkrow, Bird of Death";
                this.setName(bossName);
                randomizeBossStats();
            }

            else if (map.getCurrRegionName().equals("The Eternal Flame")) {
                String bossType = types[2]; // undead type
                this.setType(bossType);
                String bossName = bossType + "Obituscary, Bringer of Bad News";
                this.setName(bossName);
                randomizeBossStats();
            }

            else if (map.getCurrRegionName().equals("The Gate of Despair")) {
                String bossType = types[3]; // arcane type
                this.setType(bossType);
                String bossName = bossType + "Whodeknee";
                this.setName(bossName);
                randomizeBossStats();
            } else {
                String bossType = types[0]; // Fire type
                this.setType(bossType);
                String bossName = bossType + "Arcanight";
                this.setName(bossName);
                randomizeBossStats();
            }
        }
    }


}
