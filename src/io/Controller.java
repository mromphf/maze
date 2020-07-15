package io;

import game.GameLoop;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

public class Controller implements Initializable {

    @FXML
    private Canvas foreground;

    @FXML
    private Canvas midground;

    @FXML
    private Canvas background;

    private final List<List<List<Character>>> levelData = Storage.loadLevels("data/levels");

    private GameLoop gameLoop;

    private int levelIndex = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadNextLevel();
    }

    public void loadNextLevel() {
        if (levelIndex + 1 > levelData.size()) {
            exit();
        }
        Screen screen = new Screen(foreground, midground, background);
        gameLoop = new GameLoop(this, screen, levelData.get(levelIndex));
        gameLoop.start();
        levelIndex++;
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.P)) {
            gameLoop.pause();
        }
        Keyboard.press(keyEvent.getCode());
    }

    @FXML
    public void handleKeyReleased(KeyEvent keyEvent) {
        Keyboard.release(keyEvent.getCode());
    }
}
