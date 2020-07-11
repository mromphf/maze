package game.concrete;

import game.abstraction.Collidable;
import game.abstraction.GameObject;
import game.abstraction.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Goal extends GameObject implements Tile {

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
}