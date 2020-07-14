package game.concrete;

import game.abstraction.Collider;
import game.abstraction.GameObject;
import game.abstraction.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Block extends Collider implements GameObject {

    private final Image blockImg = new Image(getClass().getResourceAsStream("../../brown-block.png"));

    public Block(int x, int y) {
        super(x, y);
        this.width = 60;
        this.height = 60;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(blockImg, x, y, width, width);
    }

    @Override
    public boolean matches(Predicate p) {
        return false;
    }
}
