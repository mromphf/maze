package game.abstraction;

import java.util.Collection;

public interface Movable {
    void move (Collection<? extends Collidable> c);
}
