package Controller;

import Event.KeyHandler;
import Main.SnakeGame;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

public class GameController {
	
	private SnakeGame game;
	private Canvas canvas;
	
	public GameController() {
		game = new SnakeGame();
		canvas = game.createCanvas();
	}
	
	public Scene createScene() {
		StackPane sPane = new StackPane();
		sPane.getChildren().add(canvas);
		
		Scene scene = new Scene(sPane);
		scene.setOnKeyPressed(new KeyHandler(game));
		
		return scene;
	}
	
	public void startGame() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		new AnimationTimer() {

			@Override
			public void handle(long now) {
				
				game.update();
				game.render(gc);
				
			}
			
		}.start();
	}

}
