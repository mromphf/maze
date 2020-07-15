package game.concrete;

import game.abstraction.Collider;
import game.abstraction.Entity;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Block extends Collider implements Entity {

    private final Image blockImg = new Image("file:data/graphics/brown-block.png");

    public Block(int x, int y, int width, int height) {
        super(x, y);
        this.height = height;
        this.width = width;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(blockImg, x, y, width, height);
    }

    @Override
    public boolean matches(Predicate p) {
        return false;
    }
}
