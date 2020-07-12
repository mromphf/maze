package game.concrete;

import game.abstraction.Collidable;
import game.abstraction.Collider;
import game.abstraction.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Goal extends Collider implements Tile {

    public Goal(int x, int y) {
        super(x, y);
        height = 50;
        width = 50;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.BLUE);
        context.fillRect(x, y, width, height);
    }

    @Override
    public boolean collidesWith(Collidable c) {
        return false;
    }

    @Override
    public boolean isStartLocation() {
        return false;
    }

    @Override
    public boolean isGoal() {
        return true;
    }
}
