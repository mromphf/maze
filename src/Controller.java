import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
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
        GraphicsContext context = canvas.getGraphicsContext2D();

        Player player = new Player(500, 500);
        drawables.forEach(d -> d.draw(context));
        player.draw(context);
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
    }
}
