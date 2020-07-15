package game.abstraction;

import java.util.Collection;

public interface MovableGameObject extends Entity {
    void move (Collection<? extends Entity> c);
}
