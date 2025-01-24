import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameSaver {
    public GameSaver() {
    }

    public static void saveGame(GameState gameState, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(gameState);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving the game: " + e.getMessage());
        }

    }

    public static GameState loadGame(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            GameState gameState = (GameState)in.readObject();
            return gameState;
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error loading the game: " + ((Exception)e).getMessage());
            return null;
        }
    }
}
