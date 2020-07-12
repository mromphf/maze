package game.concrete;

import game.abstraction.Collider;
import game.abstraction.Predicate;
import game.abstraction.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Block extends Collider implements Tile {

    private final Image blockImg = new Image(getClass().getResourceAsStream("../../brown-block.png"));

    public Block(int x, int y) {
        super(x, y);
        this.width = 50;
        this.height = 50;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(blockImg, x, y, width, width);
    }

    @Override
    public boolean isStartLocation() {
        return false;
    }

    @Override
    public boolean isGoal() {
        return false;
    }

    @Override
    public boolean matches(Predicate p) {
        return false;
    }
}
