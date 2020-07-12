package game.concrete;

import game.abstraction.GameObject;
import game.abstraction.Collider;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Start extends Collider implements GameObject {

    public Start(int x, int y) {
        super(x, y);
        width = 50;
        height = 50;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.LIGHTGREY);
        context.fillRect(x, y, width, height);
    }

    @Override
    public boolean collidesWith(GameObject target) {
        return false;
    }

    @Override
    public boolean matches(Predicate p) {
        return false;
    }
}
