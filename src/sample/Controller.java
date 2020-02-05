package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Canvas canvas;

    private GraphicsContext context;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        context = canvas.getGraphicsContext2D();
        context.setFill(Color.BLUE);
        context.fillRect(100, 100, 250, 250);
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case G:
                context.setFill(Color.GREEN);
                break;
            case R:
                context.setFill(Color.RED);
                break;
            default:
                context.setFill(Color.BLUE);
                break;
        }
        context.fillRect(100, 100, 250, 250);
    }
}
