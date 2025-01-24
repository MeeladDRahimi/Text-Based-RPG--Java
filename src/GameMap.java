import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameMap {
    private Map<String, List<String>> map;
    private String currentRegion;
    private Scanner scanner;

    public GameMap(Map<String, List<String>> map, String startingRegion) {
        this.scanner = new Scanner(System.in);
        this.map = map;
        this.currentRegion = startingRegion;
    }

    public void printCurrentRegion() {
        System.out.println("You are in: " + this.currentRegion);
        System.out.println("You can travel to:");
        List<String> destinations = (List)this.map.get(this.currentRegion);

        for(int i = 0; i < destinations.size(); ++i) {
            System.out.println(i + 1 + ". " + (String)destinations.get(i));
        }

    }

    public boolean moveToRegion(int choice) {
        List<String> destinations = (List)this.map.get(this.currentRegion);
        if (choice >= 1 && choice <= destinations.size()) {
            this.currentRegion = (String)destinations.get(choice - 1);
            return true;
        } else {
            System.out.println("Invalid choice! Please choose a valid destination.");
            return false;
        }
    }

    private int readInt(String prompt, int userChoice) {
        int input;
        do {
            System.out.println(prompt);

            try {
                input = Integer.parseInt(this.scanner.next());
            } catch (Exception var5) {
                input = -1;
                System.out.println("Please enter an integer!");
            }
        } while(input < 1 || input > userChoice);

        return input;
    }

    public void travel() {
        this.printCurrentRegion();
        System.out.print("Travel to (enter the number): ");
        int choice = this.readInt("Choose a destination: ", ((List)this.map.get(this.currentRegion)).size());
        if (this.moveToRegion(choice)) {
            System.out.println("Travelling...");
        } else {
            System.out.println("Try again.");
        }

    }

    public String getCurrRegionName() {
        return this.currentRegion;
    }
}
