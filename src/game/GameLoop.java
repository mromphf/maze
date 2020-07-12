package game;

import game.abstraction.GameObject;
import game.abstraction.MovableGameObject;
import game.abstraction.Predicate;
import game.concrete.Goal;
import game.concrete.Player;
import game.concrete.Switch;
import io.File;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static javafx.application.Platform.exit;

public class GameLoop extends AnimationTimer {

    private final GraphicsContext foreground;
    private final GraphicsContext background;
    private final double screenWidth;
    private final double screenHeight;
    private final Map<Integer, List<Character>> levelFile = File.loadLevel("data/level4.csv");
    private final Collection<GameObject> tiles = LoadsLevels.generateTiles(levelFile);
    private final Collection<MovableGameObject> actors = LoadsLevels.generateActors(levelFile);
    private final Player player;
    private final Collection<MovableGameObject> enemies;
    private final Collection<Switch> switches;
    private Goal goal;

    public GameLoop(Canvas fgCanvas, Canvas bgCanvas) {
        Rectangle2D screen = Screen.getPrimary().getBounds();
        this.screenHeight = screen.getHeight();
        this.screenWidth = screen.getWidth();

        fgCanvas.setHeight(screenHeight);
        fgCanvas.setWidth(screenWidth - (screenWidth * 0.2));
        bgCanvas.setHeight(screenHeight);
        bgCanvas.setWidth(screenWidth - (screenWidth * 0.2));

        fgCanvas.toFront();
        bgCanvas.toBack();

        foreground = fgCanvas.getGraphicsContext2D();
        background = bgCanvas.getGraphicsContext2D();

        foreground.translate(225, 35);
        background.translate(225, 35);

        tiles.forEach(d -> d.draw(background));

        player = (Player) actors.stream()
                .filter(m -> m.matches(Predicate.IS_PLAYER))
                .findFirst()
                .orElseThrow(IllegalStateException::new);

        enemies = actors.stream()
                .filter(a -> a.matches(Predicate.IS_ENEMY))
                .collect(Collectors.toSet());

        goal = (Goal) tiles.stream()
                .filter(t -> t.matches(Predicate.IS_GOAL))
                .findFirst()
                .orElseThrow(IllegalStateException::new);

        switches = tiles.stream()
                .filter(a -> a.matches(Predicate.IS_SWITCH))
                .map(a -> new Switch(a.getX(), a.getY()))
                .collect(Collectors.toSet());
    }

    @Override
    public void handle(long now) {
        foreground.clearRect(0, 0, screenWidth, screenHeight);

        actors.forEach(a -> {
            a.draw(foreground);
            a.move(tiles);
        });

        goal.draw(background);

        switches.forEach(s -> {
            s.draw(background);

            if (player.collidesWith(s)) {
                s.onCollide(player);
            }
        });

        if (switches.stream().filter(Switch::isFlipped).count() == switches.size()) {
            goal = goal.open();
        }

        enemies.forEach(e -> {
            if (player.collidesWith(e)) {
                gameOver();
            }
        });

        if (player.collidesWith(goal) && goal.isOpen()) {
            gameOver();
        }
    }

    public void gameOver() {
        exit();
    }
}
