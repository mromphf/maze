package game.concrete;

import game.abstraction.Entity;
import game.abstraction.Collider;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Collection;

public class Goal extends Collider implements Entity {

    private final Predicate predicate = Predicate.IS_GOAL;
    private boolean gameOver;
    private boolean isOpen;

    public Goal(int x, int y, int width, int height, boolean isOpen) {
        super(x, y);
        this.isOpen = isOpen;
        this.gameOver = false;
        this.height = height;
        this.width = width;
    }

    @Override
    public void onCollide(Entity o) {
        if (o.matches(Predicate.IS_PLAYER)) {
            gameOver = true;
        }
    }

    @Override
    public void draw(GraphicsContext context) {
        Color c = isOpen? Color.BLUE : Color.RED;
        context.setFill(c);
        context.fillRect(x, y, width, height);
    }

    @Override
    public void examine(Collection<Entity> c) {
        int all_switches = (int) c.stream().filter(o -> o.matches(Predicate.IS_SWITCH)).count();
        int switches_flipped = (int) c.stream().filter(o -> o.matches(Predicate.SWITCH_FLIPPED)).count();
        if (all_switches == switches_flipped) {
            this.isOpen = true;
        }
    }

    @Override
    public boolean collidesWith(Entity c) {
        if (isOpen) {
            return false;
        }
        return super.collidesWith(c);
    }

    @Override
    public boolean matches(Predicate p) {
        if (p.equals(Predicate.GAME_OVER)) {
            return gameOver;
        }
        return predicate.equals(p);
    }
}
