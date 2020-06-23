import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.util.List;

public class GameLoop extends AnimationTimer {

    private final GraphicsContext context;
    private final double screenWidth;
    private final double screenHeight;
    private final List<Drawable> drawables = Maze.make();
    private final Player player = new Player(500, 500);

    public GameLoop(Canvas canvas) {
        Rectangle2D screen = Screen.getPrimary().getBounds();
        final double screenHeight = screen.getHeight();
        final double screenWidth = screen.getWidth();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        canvas.setHeight(screenHeight);
        canvas.setWidth(screenWidth - (screenWidth * 0.2));
        context = canvas.getGraphicsContext2D();
    }

    @Override
    public void handle(long now) {
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, screenWidth, screenHeight);

        drawables.forEach(d -> d.draw(context));

        player.draw(context);
        player.move();
    }
}
