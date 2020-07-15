package game.concrete;

import game.abstraction.Entity;
import game.abstraction.Collider;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Switch extends Collider implements Entity {

    private final Predicate predicate = Predicate.IS_SWITCH;
    private boolean flipped;

    public Switch(int x, int y, int width, int height) {
        super(x, y);
        this.flipped = false;
        this.height = height;
        this.width = width;
    }

    @Override
    public void onCollide(Entity target) {
        if (target.matches(Predicate.IS_PLAYER)) {
            this.flipped = true;
        }
    }

    @Override
    public boolean collidesWith(Entity c) {
        return false;
    }

    @Override
    public void draw(GraphicsContext context) {
        Color c = flipped ? Color.LIGHTGREY : Color.LIGHTBLUE;
        context.setFill(c);
        context.fillRect(x, y, width, height);
    }

    @Override
    public boolean matches(Predicate p) {
        if (p.equals(Predicate.SWITCH_FLIPPED)) {
            return flipped;
        }
        return predicate.equals(p);
    }
}
