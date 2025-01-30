/**
 * Represents the game map, allowing the player to navigate between different regions.
 */
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameMap {
    private Map<String, List<String>> map;
    private String currentRegion;
    private Scanner scanner;

    /**
     * Constructs a GameMap with the given regions and starting location.
     *
     * @param map The mapping of regions to their connected destinations.
     * @param startingRegion The initial region where the player starts.
     */
    public GameMap(Map<String, List<String>> map, String startingRegion) {
        this.scanner = new Scanner(System.in);
        this.map = map;
        this.currentRegion = startingRegion;
    }

    /**
     * Prints the current region and available travel destinations.
     */
    public void printCurrentRegion() {
        System.out.println("You are in: " + this.currentRegion);
        System.out.println("You can travel to:");
        List<String> destinations = this.map.get(this.currentRegion);

        for (int i = 0; i < destinations.size(); ++i) {
            System.out.println((i + 1) + ". " + destinations.get(i));
        }
    }

    /**
     * Moves the player to the selected region if the choice is valid.
     *
     * @param choice The index of the region to move to.
     * @return true if the move was successful, false otherwise.
     */
    public boolean moveToRegion(int choice) {
        List<String> destinations = this.map.get(this.currentRegion);
        if (choice >= 1 && choice <= destinations.size()) {
            this.currentRegion = destinations.get(choice - 1);
            return true;
        } else {
            System.out.println("Invalid choice! Please choose a valid destination.");
            return false;
        }
    }

    /**
     * Reads an integer input from the player within the valid range.
     *
     * @param prompt The message displayed to the player.
     * @param userChoice The maximum valid choice number.
     * @return The player's valid choice.
     */
    private int readInt(String prompt, int userChoice) {
        int input;
        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(this.scanner.next());
            } catch (Exception e) {
                input = -1;
                System.out.println("Please enter an integer!");
            }
        } while (input < 1 || input > userChoice);

        return input;
    }

    /**
     * Allows the player to travel to a new region by selecting a valid destination.
     */
    public void travel() {
        this.printCurrentRegion();
        System.out.print("Travel to (enter the number): ");
        int choice = this.readInt("Choose a destination: ", this.map.get(this.currentRegion).size());
        if (this.moveToRegion(choice)) {
            System.out.println("Travelling...");
        } else {
            System.out.println("Try again.");
        }
    }

    /**
     * Retrieves the name of the current region.
     *
     * @return The name of the current region.
     */
    public String getCurrRegionName() {
        return this.currentRegion;
    }
}
