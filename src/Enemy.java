import java.util.Random;

public class Enemy extends Character {
    private static final String[] fireAdjectives = new String[]{
            "Infernal", "Hellbound", "Emberborn", "Ashen", "Scorching", "Charred", "Molten", "Pyroclastic", "Brimstone", "Blazing"
    };

    private static final String[] iceAdjectives = new String[]{
            "Frostbane", "Glacial", "Icebound", "Blizzard-born", "Frozen", "Snowdrift", "Permafrost", "Shardborn", "Arctic", "Cryogenic"
    };

    private static final String[] undeadAdjectives = new String[]{
            "Boneclaw", "Wraithborn", "Hollow", "Gravebound", "Phantom", "Necrotic", "Ghoul-ridden", "Lichborne", "Shadowforged", "Deathbound"
    };

    private static final String[] arcaneAdjectives = new String[]{
            "Voidborn", "Abyssal", "Stormforged", "Celestial", "Aetherborn", "Titanborn", "Behemothic", "Runed", "Warped", "Draconic"
    };

    private static final String[] enemyNames = new String[]{
            "Warlord", "Revenant", "Stalker", "Titan", "Lord", "Demon", "Behemoth", "Specter", "Lich", "Abomination",
            "Wraith", "Phantom", "Ghoul", "Shade", "Gargoyle", "Serpent", "Dragon", "Fiend", "Overlord", "Necromancer",
            "Warlock", "Cultist", "Brute", "Terror", "Harbinger", "Sentinel", "Colossus", "Monstrosity", "Nightmare", "Devourer",
            "Abysswalker", "Voidborn", "Hellhound", "Ravager", "Plaguebearer", "Corruptor", "Dreadknight", "Darkspawn", "Soulreaper", "Tormentor",
            "Executioner", "Doombringer", "Stormcaller", "Pyromancer", "Frostbringer", "Ironclad", "Shadowmancer", "Bloodfiend", "Desecrator", "Bonecrusher"
    };

    private static final String[] types = new String[]{
            "🔥", "❄️", "☠️", "🔮"
    };

    private String type; // Store enemy type

    public Enemy() {
        super("", 100, 100, 0, 10, 10, 10, 0); // Temporary empty name
        this.type = assignRandomType(); // Assign type first
        this.setName(generateNameBasedOnType(this.type)); // Now generate name accordingly
        this.randomize();

    }


    private String assignRandomType() {
        Random rand = new Random();
        return types[rand.nextInt(types.length)]; // Randomly selects a type from the array
    }

    private static String generateNameBasedOnType(String type) {
        Random rand = new Random();
        String adjective;

        switch (type) {
            case "🔥" -> adjective = fireAdjectives[rand.nextInt(fireAdjectives.length)];
            case "❄️" -> adjective = iceAdjectives[rand.nextInt(iceAdjectives.length)];
            case "☠️" -> adjective = undeadAdjectives[rand.nextInt(undeadAdjectives.length)];
            case "🔮" -> adjective = arcaneAdjectives[rand.nextInt(arcaneAdjectives.length)];
            default -> adjective = "Unknown";
        }

        String name = enemyNames[rand.nextInt(enemyNames.length)];
        return type + adjective + " " + name;
    }

    public void randomize() {
        Random rand = new Random();
        int playerMaxHp = GameLogic.player.getMaxHp();
        int playerStrength = GameLogic.player.getStrength();
        int playerDefense = GameLogic.player.getDefense();
        int playerSpeed = GameLogic.player.getSpeed();
        int currentAct = GameLogic.getCurrentAct();

        // Balance the enemy stats more fairly
        double healthMultiplier = 0.9 + rand.nextDouble() * 0.3 + (currentAct * 0.15);
        double strengthMultiplier = 0.85 + rand.nextDouble() * 0.25 + (currentAct * 0.12);
        double defenseMultiplier = 0.85 + rand.nextDouble() * 0.25 + (currentAct * 0.1);
        double speedMultiplier = 0.85 + rand.nextDouble() * 0.25 + (currentAct * 0.1);

        this.setMaxHp((int) (playerMaxHp * healthMultiplier));
        this.setDefense((int) (playerDefense * defenseMultiplier));
        this.setStrength((int) (playerStrength * strengthMultiplier));
        this.setSpeed((int) (playerSpeed * speedMultiplier));

        this.setHp(this.getMaxHp());
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }
}
