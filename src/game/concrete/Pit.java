package game.concrete;

import game.abstraction.Collider;
import game.abstraction.Entity;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pit extends Collider implements Entity {

    public Pit(int x, int y) {
        super(x, y);
        this.height = 60;
        this.width = 60;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.fillRect(x, y, width, height);
    }

    @Override
    public boolean collidesWith(Entity e) {
        if (e.matches(Predicate.IS_PLAYER)) {
            return super.collidesWith(e);
        }
        return false;
    }
}
