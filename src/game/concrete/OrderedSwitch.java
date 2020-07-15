package game.concrete;

import game.abstraction.Collider;
import game.abstraction.Entity;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.Collection;

public class OrderedSwitch extends Collider implements Entity {

    private final Predicate predicate;
    private final int ordinal;
    private boolean flipped;

    public OrderedSwitch(int x, int y, int ordinal) {
        super(x, y);
        this.height = 60;
        this.width = 60;
        this.ordinal = ordinal;
        this.predicate = Predicate.IS_SWITCH;
    }

    @Override
    public void draw(GraphicsContext context) {
        Color c = flipped ? Color.LIGHTGREY : Color.BLACK;
        context.setFill(c);
        if (flipped) {
            context.fillRect(x, y, width, height);
        } else {
            context.setTextAlign(TextAlignment.CENTER);
            context.setFont(new Font(55));
            context.fillText(String.valueOf(ordinal), x + 30, y + 50);
        }
    }

    @Override
    public void onCollide(Entity e) {
        if (e.matches(Predicate.IS_PLAYER)) {
            flipped = true;
        }
    }

    @Override
    public void examine(Collection<Entity> c) {
        if (flipped && c.stream().filter(e -> e.matches(Predicate.SWITCH_FLIPPED)).count() < ordinal) {
            flipped = false;
        }
    }

    @Override
    public boolean matches(Predicate p) {
        if (p.equals(Predicate.SWITCH_FLIPPED)) {
            return flipped;
        }
        return predicate.equals(p);
    }

    @Override
    public boolean collidesWith(Entity e) {
        return false;
    }
}
