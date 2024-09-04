package Event;

import Main.SnakeGame;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {

	private SnakeGame game;
	
	public KeyHandler(SnakeGame game) {
		this.game = game;
	}
	
	@Override
	public void handle(KeyEvent key) {
		
		switch(key.getCode()) {
		
		case UP:
			if(!"DOWN".equals(game.getDirection())) game.setDirection("UP");
			break;
		case DOWN:
			if(!"UP".equals(game.getDirection())) game.setDirection("DOWN");
			break;
		case LEFT: 
			if(!"RIGHT".equals(game.getDirection())) game.setDirection("LEFT");
			break;
		case RIGHT:
			if(!"LEFT".equals(game.getDirection())) game.setDirection("RIGHT");
			break;
		default:
			break;
		}
		
	}

}
