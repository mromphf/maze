package game.abstraction;

import javafx.scene.canvas.GraphicsContext;

public interface GameObject {

    void draw(GraphicsContext context);

    boolean matches(Predicate p);

    void onCollide(GameObject target);

    boolean collidesWith(GameObject target);

    int getX();

    int getY();

    int getHeight();

    int getWidth();
}
