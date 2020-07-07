package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Goal extends GameObject {

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
    public boolean collidesWith(GameObject o) {
        return false;
    }
}
