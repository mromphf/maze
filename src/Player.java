import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
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

    public void move() {
        if (Keyboard.isPressed(KeyCode.LEFT)) {
            moveLeft();
        }
        else if (Keyboard.isPressed(KeyCode.RIGHT)) {
            moveRight();
        }
        else if (Keyboard.isPressed(KeyCode.UP)) {
            moveUp();
        }
        else if (Keyboard.isPressed(KeyCode.DOWN)) {
            moveDown();
        }
    }

    private void moveLeft() {
        this.x -= this.velocity;
    }

    private void moveRight() {
        this.x += this.velocity;
    }

    private void moveUp() {
        this.y -= this.velocity;
    }

    private void moveDown() {
        this.y += this.velocity;
    }
}
