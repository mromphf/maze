package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Start extends GameObject {

    public Start(int x, int y) {
        super(x, y);
        width = 50;
        height = 50;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.LIGHTBLUE);
        context.fillRect(x, y, width, height);
    }

    @Override
    public boolean collidesWith(GameObject target) {
        return false;
    }
}
