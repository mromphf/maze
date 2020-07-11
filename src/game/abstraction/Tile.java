package game.abstraction;

public interface Tile extends Drawable, Collidable {

    boolean isStartLocation();

    boolean isGoal();
}

