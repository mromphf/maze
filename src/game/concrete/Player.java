package game.concrete;

import game.abstraction.Collidable;
import game.abstraction.GameObject;
import game.abstraction.Movable;
import io.Keyboard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.Collection;

public class Player extends GameObject implements Movable {

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

    public void move(Collection<? extends Collidable> obstacles) {
        if (Keyboard.isPressed(KeyCode.LEFT) && canMoveHere(obstacles, new Player(x - velocity, y))) {
            moveLeft();
        }
        else if (Keyboard.isPressed(KeyCode.RIGHT) && canMoveHere(obstacles, new Player(x + velocity, y))) {
            moveRight();
        }
        else if (Keyboard.isPressed(KeyCode.UP) && canMoveHere(obstacles, new Player(x, y - velocity))) {
            moveUp();
        }
        else if (Keyboard.isPressed(KeyCode.DOWN) && canMoveHere(obstacles, new Player(x, y + velocity))) {
            moveDown();
        }
    }

    private boolean canMoveHere(Collection<? extends Collidable> obstacles, Collidable tryingToMoveHere) {
        return obstacles.stream().noneMatch(o -> o.collidesWith(tryingToMoveHere));
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
