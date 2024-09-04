package Main;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeGame {
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	private static final int TILE_SIZE = 2;
	
	private ArrayList<int[]> snake;
	private int[] food;
	
	private String direction = "RIGHT";
	private boolean gameover = false;
	private Random random = new Random();
	
	public SnakeGame() {
		snake = new ArrayList<>();
		snake.add(new int[]{WIDTH / 2, HEIGHT / 2});
		spawnFood();
	}
	
	public void update() {
		if(gameover) return;
		
		int[] head = snake.get(0);
		int[] newHead = new int[2];
		switch(direction) {
		
		case "UP":
			newHead[0] = head[0]; newHead[1] = head[1] - TILE_SIZE; break;
		case "DOWN":
			newHead[0] = head[0]; newHead[1] = head[1] + TILE_SIZE; break;
		case "LEFT": 
			newHead[0] = head[0] - TILE_SIZE; newHead[1] = head[1]; break;
		case "RIGHT":
			newHead[0] = head[0] + TILE_SIZE; newHead[1] = head[1]; break;
		}
		
		if(checkCollision(newHead)) {
			gameover = true;
			return;
		}
		
		snake.add(0, newHead);
		
		if(newHead[0] == food[0] && newHead[1] == food[1]) {
			spawnFood();
		} else {
			snake.remove(snake.size() - 1);
		}
	}
	
	public void render(GraphicsContext gc) {
		gc.clearRect(0, 0, WIDTH, HEIGHT);
		
		//Food
		gc.setFill(Color.RED);
		gc.fillRect(food[0], food[1], TILE_SIZE, TILE_SIZE);
		
		//Snake
		gc.setFill(Color.GREEN);
		for(int[] part : snake) {
			gc.fillRect(part[0], part[1], TILE_SIZE, TILE_SIZE);
		}
		
		if(gameover) {
			gc.setFill(Color.BLACK);
			gc.fillText("Gameover. You lost.", WIDTH / 2 - 40, HEIGHT / 2 );
		}
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getDirection() {
		return direction;
	}
	
	private boolean checkCollision(int[] position) {
		
		return false;
	}
	
	private void spawnFood() {
		food = new int[] {random.nextInt(WIDTH / TILE_SIZE) * TILE_SIZE, random.nextInt(HEIGHT / TILE_SIZE) * TILE_SIZE};
	}
	
	public Canvas createCanvas() {
		return new Canvas(WIDTH, HEIGHT);
	}

}
