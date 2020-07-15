package game.concrete;

import game.abstraction.Entity;
import game.abstraction.Collider;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Empty extends Collider implements Entity {

    public Empty(int x, int y) {
        super(x, y);
        this.width = 60;
        this.height = 60;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.GRAY);
        context.strokeRect(x, y, width, height);
    }

    @Override
    public boolean collidesWith(Entity c) {
        return false;
    }

    @Override
    public boolean matches(Predicate p) {
        return false;
    }
}
