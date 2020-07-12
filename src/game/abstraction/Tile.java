package game.abstraction;

public interface Tile extends Drawable, Collidable, Examinable {

    boolean isStartLocation();

    boolean isGoal();
}

