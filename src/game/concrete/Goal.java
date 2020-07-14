package game.concrete;

import game.abstraction.GameObject;
import game.abstraction.Collider;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Collection;

public class Goal extends Collider implements GameObject {

    private final Predicate predicate = Predicate.IS_GOAL;
    private boolean isOpen;

    public Goal(int x, int y, boolean isOpen) {
        super(x, y);
        this.isOpen = isOpen;
        height = 50;
        width = 50;
    }

    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void draw(GraphicsContext context) {
        Color c = isOpen? Color.BLUE : Color.RED;
        context.setFill(c);
        context.fillRect(x, y, width, height);
    }

    @Override
    public void examine(Collection<GameObject> c) {
        int all_switches = (int) c.stream().filter(o -> o.matches(Predicate.IS_SWITCH)).count();
        int switches_flipped = (int) c.stream().filter(o -> o.matches(Predicate.SWITCH_FLIPPED)).count();
        if (all_switches == switches_flipped) {
            this.isOpen = true;
        }
    }

    @Override
    public boolean collidesWith(GameObject c) {
        if (isOpen) {
            return false;
        }
        return super.collidesWith(c);
    }

    @Override
    public boolean matches(Predicate p) {
        return predicate.equals(p);
    }
}
