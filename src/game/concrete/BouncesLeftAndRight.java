package game.concrete;

import game.abstraction.Entity;
import game.abstraction.Movable;
import game.abstraction.Mover;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.Collection;

public class BouncesLeftAndRight extends Mover implements Movable {

    private final Predicate predicate = Predicate.IS_ENEMY;
    private boolean goingLeft = true;

    public BouncesLeftAndRight(int x, int y, int width, int height, int velocity) {
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
        if (goingLeft && canMoveHere(obstacles, new BouncesLeftAndRight(x - velocity, y, width, height, velocity))) {
            moveLeft();
        }
        else if (!goingLeft && !canMoveHere(obstacles, new BouncesLeftAndRight(x + velocity, y, width, height, velocity))) {
            goingLeft = true;
            moveLeft();
        }
        else if (goingLeft && !canMoveHere(obstacles, new BouncesLeftAndRight(x - velocity, y, width, height, velocity))) {
            goingLeft = false;
            moveRight();
        }
        else if (!goingLeft && canMoveHere(obstacles, new BouncesLeftAndRight(x + velocity, y, width, height, velocity))) {
            moveRight();
        }
    }
}
