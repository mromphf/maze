package game.concrete;

import game.abstraction.Actor;
import game.abstraction.Collidable;
import game.abstraction.Mover;
import io.Keyboard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.Collection;

public class Player extends Mover implements Actor {

    public Player(int x, int y, int velocity) {
        super(x, y, velocity);
        height = 35;
        width = 35;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.GREEN);
        context.fillArc(x, y, width, height, 0, 360, ArcType.ROUND);
    }

    public void move(Collection<? extends Collidable> obstacles) {
        if (Keyboard.isPressed(KeyCode.LEFT) && canMoveHere(obstacles, new Player(x - velocity, y, velocity))) {
            moveLeft();
        }
        else if (Keyboard.isPressed(KeyCode.RIGHT) && canMoveHere(obstacles, new Player(x + velocity, y, velocity))) {
            moveRight();
        }
        else if (Keyboard.isPressed(KeyCode.UP) && canMoveHere(obstacles, new Player(x, y - velocity, velocity))) {
            moveUp();
        }
        else if (Keyboard.isPressed(KeyCode.DOWN) && canMoveHere(obstacles, new Player(x, y + velocity, velocity))) {
            moveDown();
        }
    }
}
