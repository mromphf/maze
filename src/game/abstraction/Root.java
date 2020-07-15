package game.abstraction;

import java.util.Collection;

public abstract class Root {

    public void examine(Collection<Entity> c) {}

    public boolean matches(Predicate p) {
        return false;
    }
}
