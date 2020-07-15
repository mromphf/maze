package game.abstraction;

import javafx.scene.canvas.GraphicsContext;

import java.util.Collection;

public interface Entity {

    void draw(GraphicsContext context);

    void onCollide(Entity target);

    void examine(Collection<Entity> objects);

    boolean matches(Predicate p);

    boolean collidesWith(Entity target);

    int getX();

    int getY();

    int getHeight();

    int getWidth();
}
