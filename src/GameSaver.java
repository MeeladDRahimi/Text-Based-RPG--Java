/**
 * The GameSaver class provides functionality to save and load game states.
 * It uses Java's serialization mechanism to write and read GameState objects.
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameSaver {

    /**
     * Constructs a GameSaver instance.
     * This class contains only static methods and does not require instantiation.
     */
    public GameSaver() {
    }

    /**
     * Saves the current game state to a file.
     *
     * @param gameState The game state object to save.
     * @param fileName The name of the file where the game state will be saved.
     *
     * Preconditions: gameState must not be null, and fileName must be a valid path.
     * Postconditions: The game state is saved to the specified file.
     */
    public static void saveGame(GameState gameState, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(gameState);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving the game: " + e.getMessage());
        }
    }

    /**
     * Loads a game state from a file.
     *
     * @param fileName The name of the file from which to load the game state.
     * @return The loaded GameState object, or null if an error occurs.
     *
     * Preconditions: fileName must refer to a valid, existing file.
     * Postconditions: If successful, returns a GameState object. Otherwise, returns null.
     */
    public static GameState loadGame(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (GameState) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error loading the game: " + e.getMessage());
            return null;
        }
    }
}
