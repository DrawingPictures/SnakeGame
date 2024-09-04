package Main;

import Controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	

	@Override
	public void start(Stage stage) throws Exception {
		
		// Spiel und Controller initialisieren
        SnakeGame game = new SnakeGame();
        GameController gameController = new GameController(game);

        // Root-Layout und Szene einrichten
        StackPane root = new StackPane();
        root.getChildren().add(game.getCanvas());

        Scene scene = new Scene(root, 600, 400);

        // Event-Handler der Szene zuweisen
        gameController.setupScene(scene);

        stage.setTitle("Snake Game");
        stage.setScene(scene);
        stage.show();

        // Spiel starten
        gameController.startGame();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
