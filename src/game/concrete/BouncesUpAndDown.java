package game.concrete;

import game.abstraction.GameObject;
import game.abstraction.MovableGameObject;
import game.abstraction.Mover;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.Collection;

public class BouncesUpAndDown extends Mover implements MovableGameObject {

    private final Predicate predicate = Predicate.IS_ENEMY;
    private boolean goingUp = true;

    public BouncesUpAndDown(int x, int y, int velocity) {
        super(x, y, velocity);
        height = 35;
        width = 35;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.RED);
        context.fillArc(x, y, width, height, 0, 360, ArcType.ROUND);
    }

    @Override
    public boolean matches(Predicate p) {
        return predicate.equals(p);
    }

    @Override
    public void move(Collection<? extends GameObject> obstacles) {
        if (goingUp && canMoveHere(obstacles, new BouncesUpAndDown(x, y - velocity, velocity))) {
            moveUp();
        }
        else if (!goingUp && !canMoveHere(obstacles, new BouncesUpAndDown(x, y + velocity, velocity))) {
            goingUp = true;
            moveUp();
        }
        else if (goingUp && !canMoveHere(obstacles, new BouncesUpAndDown(x, y - velocity, velocity))) {
            goingUp = false;
            moveDown();
        }
        else if (!goingUp && canMoveHere(obstacles, new BouncesUpAndDown(x, y + velocity, velocity))) {
            moveDown();
        }
    }
}
