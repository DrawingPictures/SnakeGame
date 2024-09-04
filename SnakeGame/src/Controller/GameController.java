package Controller;


import Main.SnakeGame;
import Event.KeyHandler;
import javafx.scene.Scene;

public class GameController {
    private final SnakeGame game;
    private final KeyHandler eventHandler;

    public GameController(SnakeGame game) {
        this.game = game;
        this.eventHandler = new KeyHandler(game);
    }

    public void setupScene(Scene scene) {
        // Setze Tastatureingaben- und Mausklick-Handler
        scene.setOnKeyPressed(eventHandler.getKeyHandler());
        scene.setOnMouseClicked(eventHandler.getMouseHandler());
    }

    public void startGame() {
        game.startGame();
    }
}