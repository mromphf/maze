package game.concrete;

import game.abstraction.Entity;
import game.abstraction.Collider;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Start extends Collider implements Entity {

    public Start(int x, int y) {
        super(x, y);
        width = 60;
        height = 60;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.LIGHTGREY);
        context.fillRect(x, y, width, height);
    }

    @Override
    public boolean collidesWith(Entity target) {
        return false;
    }

    @Override
    public boolean matches(Predicate p) {
        return false;
    }
}
