package io;

import game.GameLoop;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Canvas foreground;

    @FXML
    private Canvas background;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new GameLoop(foreground, background).start();
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        Keyboard.press(keyEvent.getCode());
    }

    @FXML
    public void handleKeyReleased(KeyEvent keyEvent) {
        Keyboard.release(keyEvent.getCode());
    }

}
