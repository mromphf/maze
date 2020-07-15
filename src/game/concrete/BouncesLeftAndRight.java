package game.concrete;

import game.abstraction.Entity;
import game.abstraction.MovableGameObject;
import game.abstraction.Mover;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.Collection;

public class BouncesLeftAndRight extends Mover implements MovableGameObject {

    private final Predicate predicate = Predicate.IS_ENEMY;
    private boolean goingLeft = true;

    public BouncesLeftAndRight(int x, int y, int velocity) {
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
    public void move(Collection<? extends Entity> obstacles) {
        if (goingLeft && canMoveHere(obstacles, new BouncesLeftAndRight(x - velocity, y, velocity))) {
            moveLeft();
        }
        else if (!goingLeft && !canMoveHere(obstacles, new BouncesLeftAndRight(x + velocity, y, velocity))) {
            goingLeft = true;
            moveLeft();
        }
        else if (goingLeft && !canMoveHere(obstacles, new BouncesLeftAndRight(x - velocity, y, velocity))) {
            goingLeft = false;
            moveRight();
        }
        else if (!goingLeft && canMoveHere(obstacles, new BouncesLeftAndRight(x + velocity, y, velocity))) {
            moveRight();
        }
    }
}
