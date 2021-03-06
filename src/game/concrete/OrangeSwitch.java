package game.concrete;

import game.abstraction.Collider;
import game.abstraction.Entity;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.Collection;

public class OrangeSwitch extends Collider implements Entity {

    private boolean flipped;

    public OrangeSwitch(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.flipped = false;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(flipped ? Color.DARKORANGE : Color.ORANGE);
        context.fillRect(x, y, width, height);
        context.setFill(Color.DARKORANGE);
        context.setTextAlign(TextAlignment.CENTER);
        context.setFont(new Font(55));
        context.fillText("S", x + 30, y + 50);
    }

    @Override
    public void onCollide(Entity e) {
        if (e.matches(Predicate.IS_PLAYER)) {
            flipped = true;
        }
    }

    @Override
    public boolean collidesWith(Entity e) {
        return false;
    }

    @Override
    public void examine(Collection<Entity> c) {
        if (c.stream().anyMatch(e -> e.matches(Predicate.BLUE_SWITCH_FLIPPED))) {
            flipped = false;
        }
    }

    @Override
    public boolean matches(Predicate p) {
        if (p.equals(Predicate.ORANGE_SWITCH_FLIPPED)) {
            return flipped;
        }
        return false;
    }
}
