import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Block implements Drawable {

    private final Image blockImg = new Image(getClass().getResourceAsStream("brown-block.png"));
    private int x;
    private int y;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(blockImg, x, y, 50, 50);
    }
}
