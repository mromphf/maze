package game.abstraction;

public interface Collidable {

    boolean collidesWith(Collidable target);

    int getX();

    int getY();

    int getHeight();

    int getWidth();
}
