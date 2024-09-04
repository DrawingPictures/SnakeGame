package Main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Random;

public class SnakeGame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int TILE_SIZE = 20;

    private String direction = "RIGHT"; // Anfangsrichtung
    private boolean isPaused = false;

    private LinkedList<int[]> snake; // Schlange besteht aus einer Liste von Segmenten [x, y]
    private int[] food;              // Essen besteht aus einem Punkt [x, y]
    private Canvas canvas;
    private GraphicsContext gc;
    private Timeline timeline;

    public SnakeGame() {
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        resetGame();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void startGame() {
        // Timeline für das Spiel, das in regelmäßigen Abständen die Bewegung der Schlange aktualisiert
        timeline = new Timeline(new KeyFrame(Duration.millis(200), e -> {
            if (!isPaused) {
                moveSnake();
                checkCollisions();
                render();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void changeDirection(String newDirection) {
        // Verhindert, dass die Schlange direkt umkehrt
        if ((direction.equals("UP") && !newDirection.equals("DOWN")) ||
            (direction.equals("DOWN") && !newDirection.equals("UP")) ||
            (direction.equals("LEFT") && !newDirection.equals("RIGHT")) ||
            (direction.equals("RIGHT") && !newDirection.equals("LEFT"))) {
            direction = newDirection;
        }
    }

    public void togglePause() {
        isPaused = !isPaused;
    }

    private void resetGame() {
        snake = new LinkedList<>();
        snake.add(new int[]{WIDTH / 2, HEIGHT / 2}); // Startposition der Schlange in der Mitte des Spielfelds
        spawnFood();
        direction = "RIGHT"; // Standardrichtung
    }

    private void moveSnake() {
        // Kopf der Schlange
        int[] head = snake.getFirst();
        int newX = head[0];
        int newY = head[1];

        // Richtung der Bewegung bestimmen
        switch (direction) {
            case "UP":
                newY -= TILE_SIZE;
                break;
            case "DOWN":
                newY += TILE_SIZE;
                break;
            case "LEFT":
                newX -= TILE_SIZE;
                break;
            case "RIGHT":
                newX += TILE_SIZE;
                break;
        }

        // Neues Segment vorne hinzufügen (Schlangen-Kopf verschieben)
        snake.addFirst(new int[]{newX, newY});

        // Wenn die Schlange das Essen erreicht, wächst sie, ansonsten wird das letzte Segment entfernt
        if (newX == food[0] && newY == food[1]) {
            spawnFood(); // neues Essen erscheint, wenn die Schlange es erreicht
        } else {
            snake.removeLast(); // Entferne das letzte Segment der Schlange
        }
    }

    private void checkCollisions() {
        int[] head = snake.getFirst();

        // Kollisionsprüfung mit den Wänden
        if (head[0] < 0 || head[0] >= WIDTH || head[1] < 0 || head[1] >= HEIGHT) {
            resetGame();
        }

        // Kollisionsprüfung mit sich selbst
        for (int i = 1; i < snake.size(); i++) {
            if (head[0] == snake.get(i)[0] && head[1] == snake.get(i)[1]) {
                resetGame();
            }
        }
    }

    private void spawnFood() {
        Random rand = new Random();
        int foodX = rand.nextInt(WIDTH / TILE_SIZE) * TILE_SIZE;
        int foodY = rand.nextInt(HEIGHT / TILE_SIZE) * TILE_SIZE;
        food = new int[]{foodX, foodY};
    }

    private void render() {
        // Hintergrund zeichnen
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        // Schlange zeichnen
        gc.setFill(Color.LIME);
        for (int[] segment : snake) {
            gc.fillRect(segment[0], segment[1], TILE_SIZE, TILE_SIZE);
        }

        // Essen zeichnen
        gc.setFill(Color.RED);
        gc.fillRect(food[0], food[1], TILE_SIZE, TILE_SIZE);
    }
}