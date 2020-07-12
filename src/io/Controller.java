package io;

import game.GameLoop;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

public class Controller implements Initializable {

    @FXML
    private Canvas foreground;

    @FXML
    private Canvas background;

    private final List<Map<Integer, List<Character>>> levelData = loadLevelData();

    private int levelIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadNextLevel();
    }

    public void loadNextLevel() {
        if (levelIndex + 1 > levelData.size()) {
            exit();
        }
        GameLoop gameLoop = new GameLoop(this,foreground, background, levelData.get(levelIndex));
        gameLoop.start();
        levelIndex++;
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        Keyboard.press(keyEvent.getCode());
    }

    @FXML
    public void handleKeyReleased(KeyEvent keyEvent) {
        Keyboard.release(keyEvent.getCode());
    }

    private List<Map <Integer, List<Character>>> loadLevelData() {
        return new ArrayList<Map <Integer, List<Character>>>() {{
            add(Storage.loadLevel("data/level1.csv"));
            add(Storage.loadLevel("data/level2.csv"));
            add(Storage.loadLevel("data/level3.csv"));
            add(Storage.loadLevel("data/level4.csv"));
            add(Storage.loadLevel("data/level5.csv"));
        }};
    }
}
