package game.abstraction;

import javafx.scene.canvas.GraphicsContext;

import java.util.Collection;

public interface GameObject {

    void draw(GraphicsContext context);

    void onCollide(GameObject target);

    void examine(Collection<GameObject> objects);

    boolean matches(Predicate p);

    boolean collidesWith(GameObject target);

    int getX();

    int getY();

    int getHeight();

    int getWidth();
}
