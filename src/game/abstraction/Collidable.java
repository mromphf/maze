package game.abstraction;

import java.util.Collection;

public interface Collidable {

    boolean collidesWith(Collidable target);

    boolean collidesWith(Collection<? extends Collidable> targets);

    int getX();

    int getY();

    int getHeight();

    int getWidth();
}
