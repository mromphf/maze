package game.concrete;

import game.abstraction.Collider;
import game.abstraction.Entity;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Collection;

public class OrangeBarrier extends Collider implements Entity {

    private boolean active;

    public OrangeBarrier(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.active = true;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(active ? Color.ORANGE : Color.LIGHTSALMON);
        context.fillRect(x, y, width, height);
    }

    @Override
    public boolean collidesWith(Entity e) {
        if (active) {
            return super.collidesWith(e);
        }
        return false;
    }

    @Override
    public void examine(Collection<Entity> c) {
        if (c.stream().anyMatch(e -> e.matches(Predicate.BLUE_SWITCH_FLIPPED))) {
            active = true;
        } else if (c.stream().anyMatch(e -> e.matches(Predicate.ORANGE_SWITCH_FLIPPED))) {
            active = false;
        }
    }
}
