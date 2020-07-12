package game.concrete;

import game.abstraction.GameObject;
import game.abstraction.MovableGameObject;
import game.abstraction.Mover;
import game.abstraction.Predicate;
import io.Keyboard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.Collection;

public class Player extends Mover implements MovableGameObject {

    private final Predicate predicate = Predicate.IS_PLAYER;

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

    @Override
    public boolean matches(Predicate p) {
        return predicate.equals(p);
    }

    public void move(Collection<? extends GameObject> obstacles) {
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
