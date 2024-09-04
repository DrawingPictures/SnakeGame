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
import javafx.stage.Stage;

public class Main extends Application {
	

	@Override
	public void start(Stage stage) throws Exception {
		
		GameController controller = new GameController();
		Scene scene = controller.createScene();
		
		stage.setTitle("My Snake Game");
		stage.setScene(scene);
		stage.show();
		
		controller.startGame();
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
