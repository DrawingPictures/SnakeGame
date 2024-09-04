package Event;

import Main.SnakeGame;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class KeyHandler {
	 private final SnakeGame game;

	    public KeyHandler(SnakeGame game) {
	        this.game = game;
	    }

	    // Handler für Tastatureingaben
	    public EventHandler<KeyEvent> getKeyHandler() {
	        return event -> {
	            switch (event.getCode()) {
	                case UP:
	                    game.changeDirection("UP");
	                    break;
	                case DOWN:
	                    game.changeDirection("DOWN");
	                    break;
	                case LEFT:
	                    game.changeDirection("LEFT");
	                    break;
	                case RIGHT:
	                    game.changeDirection("RIGHT");
	                    break;
	                default:
	                    break;
	            }
	        };
	    }

	    // Handler für Mausklicks
	    public EventHandler<MouseEvent> getMouseHandler() {
	        return event -> {
	            game.togglePause();
	        };
	    }
}