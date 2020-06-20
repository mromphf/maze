import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Canvas canvas;

    private final Image blockImg = new Image(getClass().getResourceAsStream("brown-block.png"));
    private GraphicsContext context;
    private double screenHeight;
    private double screenWidth;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rectangle2D screen = Screen.getPrimary().getBounds();
        screenHeight = screen.getHeight();
        screenWidth = screen.getWidth();
        canvas.setHeight(screenHeight);
        canvas.setWidth(screenWidth);

        context = canvas.getGraphicsContext2D();
        context.drawImage(blockImg, 0, 0,50, 50);
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
    }
}
