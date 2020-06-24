package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Empty extends GameObject implements Drawable, Tile {

    public Empty(int x, int y) {
        super(x, y);
        this.width = 50;
        this.height = 50;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.GRAY);
        context.strokeRect(x, y, width, height);
    }

    @Override
    public boolean isPassable() {
        return true;
    }
}
