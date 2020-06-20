import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Canvas canvas;
    private final List<Drawable> drawables = populateDrawables();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rectangle2D screen = Screen.getPrimary().getBounds();
        final double screenHeight = screen.getHeight();
        final double screenWidth = screen.getWidth();
        canvas.setHeight(screenHeight);
        canvas.setWidth(screenWidth - (screenWidth * 0.2));

        GraphicsContext context = canvas.getGraphicsContext2D();
        drawables.forEach(d -> d.draw(context));
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
    }

    private List<Drawable> populateDrawables() {
        List<Drawable> result = new ArrayList<>();
        for (int i = 0; i < 1500; i += 50) {
            result.add(new Block(i, 0));
        }
        return result;
    }
}
