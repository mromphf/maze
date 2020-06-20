import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Player implements Drawable {

    private int x;
    private int y;
    private int height = 40;
    private int width = 40;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.GREEN);
        context.fillArc(x, y, 40, 40, 0, 360, ArcType.ROUND);
    }
}
