import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Player extends GameObject implements Drawable {

    private final int velocity = 10;

    public Player(int x, int y) {
        super(x, y);
        this.width = 40;
        this.height = 40;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.GREEN);
        context.fillArc(x, y, width, height, 0, 360, ArcType.ROUND);
    }

    public void moveLeft() {
        this.x -= this.velocity;
    }

    public void moveRight() {
        this.x += this.velocity;
    }
}
