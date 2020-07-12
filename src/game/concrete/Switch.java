package game.concrete;

import game.abstraction.GameObject;
import game.abstraction.Collider;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Switch extends Collider implements GameObject {

    private final Predicate predicate = Predicate.IS_SWITCH;

    public Switch(int x, int y) {
        super(x, y);
        height = 50;
        width = 50;
    }

    @Override
    public boolean collidesWith(GameObject c) {
        return false;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.LIGHTBLUE);
        context.fillRect(x, y, width, height);
    }

    @Override
    public boolean matches(Predicate p) {
        return predicate.equals(p);
    }
}
