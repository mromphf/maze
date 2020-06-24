package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Block extends GameObject implements Drawable {

    private final Image blockImg = new Image(getClass().getResourceAsStream("brown-block.png"));

    public Block(int x, int y) {
        super(x, y);
        this.width = 50;
        this.height = 50;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(blockImg, x, y, width, width);
    }
}