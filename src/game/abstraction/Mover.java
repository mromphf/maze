package game.abstraction;

import java.util.Collection;

public abstract class Mover extends Collider {

    protected final int velocity;

    public Mover(int x, int y, int width, int height, int velocity) {
        super(x, y, width, height);
        this.velocity = velocity;
    }

    protected boolean canMoveHere(Collection<? extends Entity> obstacles, Entity tryingToMoveHere) {
        return obstacles.stream().noneMatch(o -> o.collidesWith(tryingToMoveHere));
    }

    protected void moveLeft() {
        this.x -= this.velocity;
    }

    protected void moveRight() {
        this.x += this.velocity;
    }

    protected void moveUp() {
        this.y -= this.velocity;
    }

    protected void moveDown() {
        this.y += this.velocity;
    }
}
