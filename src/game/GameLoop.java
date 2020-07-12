package game;

import game.abstraction.Actor;
import game.abstraction.Predicate;
import game.abstraction.Tile;
import game.concrete.Goal;
import game.concrete.Player;
import io.File;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    private final Map<Integer, List<Character>> levelFile = File.loadLevel("data/level2.csv");
    private final Collection<Tile> tiles = LoadsLevels.generateTiles(levelFile);
    private final Collection<Actor> actors = LoadsLevels.generateActors(levelFile);
    private final Player player;
    private final Goal goal;
    private final Collection<Actor> enemies;

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
    }

    @Override
    public void handle(long now) {
        foreground.clearRect(0, 0, screenWidth, screenHeight);

        actors.forEach(a -> {
            a.draw(foreground);
            a.move(tiles);
        });

        if (player.collidesWith(enemies)) {
            gameOver();
        }

        if (player.collidesWith(goal)) {
            gameOver();
        }
    }

    private void gameOver() {
        exit();
    }
}
