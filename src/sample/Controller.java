package sample;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class Controller {
    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        System.out.println(String.format("%s was pressed", keyEvent.getCode()));
    }
}
