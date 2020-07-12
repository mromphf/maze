package game.concrete;

import game.abstraction.Actor;
import game.abstraction.Collidable;
import game.abstraction.Mover;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.Collection;

public class Bouncer extends Mover implements Actor {

    private boolean goingUp = true;

    public Bouncer(int x, int y, int velocity) {
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
    public void move(Collection<? extends Collidable> obstacles) {
        if (goingUp && canMoveHere(obstacles, new Bouncer(x, y - velocity, velocity))) {
            moveUp();
        }
        else if (!goingUp && !canMoveHere(obstacles, new Bouncer(x, y + velocity, velocity))) {
            goingUp = true;
            moveUp();
        }
        else if (goingUp && !canMoveHere(obstacles, new Bouncer(x, y - velocity, velocity))) {
            goingUp = false;
            moveDown();
        }
        else if (!goingUp && canMoveHere(obstacles, new Bouncer(x, y + velocity, velocity))) {
            moveDown();
        }
    }
}
