package game.concrete;

import game.abstraction.Entity;
import game.abstraction.Movable;
import game.abstraction.Mover;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.Collection;

public class BouncesUpAndDown extends Mover implements Movable {

    private final Predicate predicate = Predicate.IS_ENEMY;
    private boolean goingUp = true;

    public BouncesUpAndDown(int x, int y, int width, int height, int velocity) {
        super(x, y, width, height, velocity);
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
    public void move(Collection<? extends Entity> obstacles) {
        if (goingUp && canMoveHere(obstacles, new BouncesUpAndDown(x, y - velocity, width, height, velocity))) {
            moveUp();
        }
        else if (!goingUp && !canMoveHere(obstacles, new BouncesUpAndDown(x, y + velocity, width, height, velocity))) {
            goingUp = true;
            moveUp();
        }
        else if (goingUp && !canMoveHere(obstacles, new BouncesUpAndDown(x, y - velocity, width, height, velocity))) {
            goingUp = false;
            moveDown();
        }
        else if (!goingUp && canMoveHere(obstacles, new BouncesUpAndDown(x, y + velocity, width, height, velocity))) {
            moveDown();
        }
    }
}
