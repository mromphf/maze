package game.abstraction;

import java.util.Collection;

public abstract class Mover extends GameObject {

    protected final int velocity;

    public Mover(int x, int y, int velocity) {
        super(x, y);
        this.velocity = velocity;
    }

    protected boolean canMoveHere(Collection<? extends Collidable> obstacles, Collidable tryingToMoveHere) {
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
