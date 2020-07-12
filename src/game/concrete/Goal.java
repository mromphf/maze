package game.concrete;

import game.abstraction.Collidable;
import game.abstraction.Collider;
import game.abstraction.Predicate;
import game.abstraction.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Goal extends Collider implements Tile {

    private final boolean isOpen;
    private final Predicate predicate = Predicate.IS_GOAL;

    public Goal(int x, int y, boolean isOpen) {
        super(x, y);
        this.isOpen = isOpen;
        height = 50;
        width = 50;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public Goal open() {
        return new Goal(x, y, true);
    }

    @Override
    public void draw(GraphicsContext context) {
        Color c = isOpen? Color.BLUE : Color.RED;
        context.setFill(c);
        context.fillRect(x, y, width, height);
    }

    @Override
    public boolean collidesWith(Collidable c) {
        return false;
    }

    @Override
    public boolean matches(Predicate p) {
        return predicate.equals(p);
    }
}
