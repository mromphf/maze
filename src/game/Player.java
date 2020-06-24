package game;

import io.Keyboard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Player extends GameObject {

    private final int velocity = 5;

    public Player(int x, int y) {
        super(x, y);
        this.width = 35;
        this.height = 35;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.GREEN);
        context.fillArc(x, y, width, height, 0, 360, ArcType.ROUND);
    }

    public void move(Maze maze) {
        if (Keyboard.isPressed(KeyCode.LEFT) && maze.canMoveHere(new Player(x - velocity, y))) {
            moveLeft();
        }
        else if (Keyboard.isPressed(KeyCode.RIGHT) && maze.canMoveHere(new Player(x + velocity, y))) {
            moveRight();
        }
        else if (Keyboard.isPressed(KeyCode.UP) && maze.canMoveHere(new Player(x, y - velocity))) {
            moveUp();
        }
        else if (Keyboard.isPressed(KeyCode.DOWN) && maze.canMoveHere(new Player(x, y + velocity))) {
            moveDown();
        }
    }

    private void moveLeft() {
        this.x -= this.velocity;
    }

    private void moveRight() {
        this.x += this.velocity;
    }

    private void moveUp() {
        this.y -= this.velocity;
    }

    private void moveDown() {
        this.y += this.velocity;
    }
}
