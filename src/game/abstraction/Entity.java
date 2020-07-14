package game.abstraction;

import java.util.Collection;

public abstract class Entity {

    public void examine(Collection<GameObject> c) {}

    public boolean matches(Predicate p) {
        return false;
    }
}
