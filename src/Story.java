/**
 * The Story class handles the narrative and dialogue for the game.
 * It provides methods to print out the different parts of the story,
 * including the prologue, each act, and special dialogues for the player.
 */
public class Story {

    /**
     * Prints the introductory part of the story, leading into the player's arrival in the hellscape.
     */
    public static void printIntroPartOne() {
        GameLogic.clearConsole();
        boldRedTextColor();
        GameLogic.printHeading("PROLOGUE");
        resetTextColor();
        System.out.println("You wake up in darkness. Heat and screaming shroud the environment around you." +
                "\nYou feel stinging... You don't know where you are or how you got here." +
                "\nYou only know that you need to find a way out");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
        System.out.println("You extend your hands and feel a wall. You must be inside of something... " + "" +
                "\nYou frantically push up on the ceiling and feel a burst of heat and light come in. " +
                "\nYou step out of a crate and see a hell-scape around you. " +
                "\nThe screams become louder and louder- and then silence...");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
        System.out.println("An old man appears." +
                "\nOld Man: First time?" +
                "\nOld Man: I am Davaiel, gatekeeper of the outer circle." +
                "\nDavaiel: On second thought, I don't recall another arrival... not good..." +
                "\nDavaiel: What's you name again???");
    }

    /**
     * Prints the second part of the introduction, where Davaiel explains the player's situation.
     */
    public static void printIntroPartTwo() {
        GameLogic.clearConsole();
        System.out.println("Davaiel: Oh... " + GameLogic.currentGameState.getPlayerName()
                + "...\nDavaiel: Yeah I don't know who you are." +
                "\nDavaiel: Alright. Well. You're not supposed to be here." +
                "\nDavaiel: It's okay don't worry... you only need to make it to the center of the realm...");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
        System.out.println("\nDavaiel: I can get you to the middle of the outer circle where the portal is to the third innermost bound" +
                "\nDavaiel: It will become harder and harder to survive the closer you are to the center of each bound" +
                "\nDavaiel: And you also need to encounter the lord of hell to ask to be pardoned...");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
        System.out.println("\nDavaiel: Basically, you're out of luck and will rot in here for eternity! Sorry bud!\n");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
        boldRedTextColor();
        GameLogic.printHeading(GameLogic.player.getName().toUpperCase() + "'S INFERNO");
        resetTextColor();
        System.out.println("By: Meelad Rahimi");
        GameLogic.anythingToContinue();
    }

    /**
     * Prints the story for Act 1, introducing the player to the outermost circle and Ashen Field.
     */
    public static void ActOneStory() {
        GameLogic.clearConsole();
        boldRedTextColor();
        GameLogic.printHeading("ACT I");
        resetTextColor();
        System.out.println("Davaiel: Welcome to Ashen Field! As you can see, or I guess can't see... the ash of the forgotten..." +
                "\nDavaiel: Yeah you're breathing in ash...that's no big deal right?");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
        System.out.println("\nDavaiel: Anyways there are other locations in the outermost bound that you have to explore." +
                "\nDavaiel: In each location there are bastions that hold many enemies, with a key fragment in the skull of the leader" +
                "\nDavaiel: You will need 6 fragments in order to unlock the gate to the 3rd bound." +
                "\nDavaiel: Also you'll have to defeat the lord of this bound..." +
                "\nDavaiel: By the way you may encounter enemies randomly. Buy some gear with brim or find some. Goodluck Traveller.");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
    }

    /**
     * Prints the story for Act 2, where the player moves deeper into Hell, encountering new dangers.
     */
    public static void ActTwoStory() {
        boldRedTextColor();
        GameLogic.printHeading("ACT II - The Infernal Descent");
        resetTextColor();
        System.out.println("Davaiel: Welcome to the third bound... it's hotter, more dangerous here..." +
                "\nDavaiel: The further you go, the closer you get to the center of Hell. But the air is thick..." +
                "\nDavaiel: You'll start noticing the land more hostile, and the demons here are much stronger..." +
                "\nDavaiel: To advance, you still need 6 more fragments from the key-bearing bastions, and another lord..." +
                "\nDavaiel: Good luck, you’ll need it. But be careful—some enemies are harder to avoid here... and you won't always see them coming.");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
        System.out.println("Davaiel: Oh I bet you're wondering what I'm doing here... " +
                "\nDavaiel: I forgot to mention that as a gatekeeper I can travel throughout all the bounds" +
                "\nDavaiel: I can't bring others though...");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
        System.out.println("\nDavaiel: I forgot to mention... the deeper you go, the more corrupted you'll become..." +
                "\nDavaiel: The heat will burn at your soul, and every second could be your last..." +
                "\nDavaiel: Don't let it consume you. Focus on getting the fragments and reaching the lord..." +
                "\nDavaiel: Defeat the lord, and you’ll be able to progress. But the closer you get to the center..." +
                "\nDavaiel: ...the more power you’ll need. I'm not sure you're ready for what’s ahead.");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
    }

    /**
     * Prints the story for Act 3, where the player must face the Infernal Spire and its dangers.
     */
    public static void ActThreeStory() {
        boldRedTextColor();
        GameLogic.printHeading("ACT III - Soul Hunger");
        resetTextColor();
        System.out.println("Davaiel: You're entering the Infernal Spire now... There’s no turning back..." +
                "\nDavaiel: This place is a hellish labyrinth, designed to break the will of those who dare enter..." +
                "\nDavaiel: The demons here are smarter, and stronger. They know what you're after... and they'll stop at nothing to keep you from succeeding." +
                "\nDavaiel: But you’ve come this far... now you need to secure those final key fragments." +
                "\nDavaiel: And don't forget about the lord of this bound. They’re not going to let you walk out easily.");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
        System.out.println("\nDavaiel: As you go deeper into the Spire, you'll feel the heat... it will weigh on your body and mind..." +
                "\nDavaiel: The demons here... they feed on despair. Every loss will hurt more than the last." +
                "\nDavaiel: But it’s all for the final gate to the core... That’s where you’ll find the portal to the final bound." +
                "\nDavaiel: But be warned—this place is cursed, and its lord is not just another beast to slay..." +
                "\nDavaiel: When you face the lord, it will feel... different. Like something’s trying to break your very soul.");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
    }

    /**
     * Prints the story for Act 4, where the player reaches the Core of Hell to face the final challenges.
     */
    public static void ActFourStory() {
        boldRedTextColor();
        GameLogic.printHeading("ACT IV - The Core of Hell");
        resetTextColor();
        System.out.println("Davaiel: You’ve made it to the heart of Hell... The core." +
                "\nDavaiel: The air here is suffocating, and every step feels like a weight on your chest..." +
                "\nDavaiel: The very ground you walk on is cursed. It's not just the demons, it's the land itself that wants you to fail." +
                "\nDavaiel: You’ll find the last key fragment in the deepest part of this wretched place... and the lord..." +
                "\nDavaiel: The lord here is more than just a creature... it's the embodiment of Hell's wrath. It will challenge every ounce of your strength..." +
                "\nDavaiel: But if you defeat them, you can finally reach the heart of the realm... and escape... or so you think..." +
                "\nDavaiel: I also heard he was very misleading and strikingly handsome...what??? I'm just being honest...");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
        System.out.println("\nDavaiel: What you face here will test more than just your physical strength." +
                "\nDavaiel: You'll face a darkness that no mortal mind should ever experience." +
                "\nDavaiel: I can only hope you’re prepared for what’s coming." +
                "\nDavaiel: When you reach the lord, it won’t just be a fight for your life... it’ll be a fight for your very soul.");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
        System.out.println("\nDavaiel: The final key is in your grasp. The portal to the core is open." +
                "\nDavaiel: The end is in sight, but the journey has only begun... This is Hell, after all...");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
    }

    /**
     * Prints the final boss dialogue and provides choices for the player to make.
     */
    public static void finalBossDialogue() {
        GameLogic.clearConsole();
        int choice;
        System.out.println("Davaiel: I can't believe you actually made it! Well done! Once you open this door you will see the beast..." +
                "\nDavaiel: Are you ready?" +
                "\n(1.) Yes" +
                "\n(2.) No" +
                "\n(3.) Maybe");
        choice = GameLogic.readInt("->",3);
        if(choice == 1){
            System.out.println("Davaiel: Perfect. You got this!");
        }
        else{
            System.out.println("Davaiel: Too bad!");
        }
        System.out.println("*Davaiel slowly wobbles to the door and opens it...");
        GameLogic.anythingToContinue();
        System.out.println("*The room is empty*");
        System.out.println("Davaiel: *Clears throat and walks to the middle of the room...*");
        GameLogic.anythingToContinue();
        System.out.println("Davaiel: *Awkwardly does jazz hands*");
        System.out.println("Davaiel:...dude it's me. I'm the lord of Hell...here look...");
        Story.boldRedTextColor();
        System.out.print("Davaiel");
        Story.resetTextColor();
        System.out.println(": My name is red, " + GameLogic.player + "....");
        GameLogic.anythingToContinue();
        System.out.println("Davaiel: Alright anyways I doubt you would fight an old man. You're stuck here forev-");
    }

    /**
     * Prints the end game dialogue, revealing the fate of the player and a humorous twist.
     */
    public static void endGameDialogue() {
        GameLogic.clearConsole();
        System.out.println("After the fateful warrior slashed down the lord of hell, a beam of light came down onto him..." +
                "\nNo one knows of what became of him");
        GameLogic.anythingToContinue();
        System.out.println("And he's still here...wait let me check the records..." +
                "\nTurns out the fateful warriors name was in the records all along..." +
                "\n'Was a Philadelphia Eagles fan and enjoyed pineapple on pizza.' wow.");
        GameLogic.anythingToContinue();
        GameLogic.clearConsole();
        boldRedTextColor();
        GameLogic.printHeading(GameLogic.player.getName().toUpperCase() + "'S INFERNO");
        resetTextColor();
        System.out.println("By: Meelad Rahimi");
        GameLogic.anythingToContinue();
    }

    /**
     * Resets the text color to the default terminal color.
     */
    public static void resetTextColor() {
        System.out.print("\u001b[0m");
    }

    /**
     * Makes the text bold.
     */
    public static void boldText() {
        System.out.print("\u001b[1m");
    }

    /**
     * Makes the text bold and red.
     */
    public static void boldRedTextColor() {
        System.out.println("\u001b[1m\u001b[31m");
    }
}
