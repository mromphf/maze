import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Canvas canvas;

    GraphicsContext context;
    private final List<Drawable> drawables = Maze.make();
    private final Player player = new Player(500, 500);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rectangle2D screen = Screen.getPrimary().getBounds();
        final double screenHeight = screen.getHeight();
        final double screenWidth = screen.getWidth();
        canvas.setHeight(screenHeight);
        canvas.setWidth(screenWidth - (screenWidth * 0.2));
        context = canvas.getGraphicsContext2D();

        new MazeAnimationTimer(screenWidth, screenHeight).start();
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        if (keyCode.equals(KeyCode.LEFT)) {
            player.moveLeft();
        } else if (keyCode.equals(KeyCode.RIGHT)) {
            player.moveRight();
        }
        player.draw(context);
    }

     public class MazeAnimationTimer extends AnimationTimer {

        private final double screenWidth;
        private final double screenHeight;

        public MazeAnimationTimer(double screenWidth, double screenHeight) {
            this.screenWidth = screenWidth;
            this.screenHeight = screenHeight;
        }

        @Override
        public void handle(long now) {
            context.setFill(Color.WHITE);
            context.fillRect(0, 0,screenWidth, screenHeight);
            drawables.forEach(d -> d.draw(context));
            player.draw(context);
        }
    }
}
