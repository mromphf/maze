package game.abstraction;

import java.util.Collection;

public interface MovableGameObject extends GameObject {
    void move (Collection<? extends GameObject> c);
}
