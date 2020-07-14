package game.concrete;

import game.abstraction.GameObject;
import game.abstraction.Collider;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Switch extends Collider implements GameObject {

    private final Predicate predicate = Predicate.IS_SWITCH;
    private boolean flipped;

    public Switch(int x, int y) {
        super(x, y);
        this.flipped = false;
        height = 50;
        width = 50;
    }

    @Override
    public void onCollide(GameObject target) {
        if (target.matches(Predicate.IS_PLAYER)) {
            this.flipped = true;
        }
    }

    @Override
    public boolean collidesWith(GameObject c) {
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
