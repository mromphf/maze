package game.abstraction;

import javafx.scene.canvas.GraphicsContext;

import java.util.Collection;

public interface GameObject {

    void draw(GraphicsContext context);

    boolean matches(Predicate p);

    boolean collidesWith(GameObject target);

    boolean collidesWith(Collection<? extends GameObject> targets);

    int getX();

    int getY();

    int getHeight();

    int getWidth();
}
