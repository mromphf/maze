package game.abstraction;

import java.util.Collection;

public interface Movable extends Entity {
    void move (Collection<? extends Entity> c);
}
