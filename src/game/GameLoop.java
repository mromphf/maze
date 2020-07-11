package game;

import game.abstraction.Movable;
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
    private final Map<Integer, List<Character>> levelFile = File.loadLevel("src/level1.csv");
    private final List<Tile> tiles = LoadsLevels.generateTiles(levelFile).stream().flatMap(List::stream).collect(Collectors.toList());
    private final Player player;
    private final Goal goal;

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

        Collection<? extends Movable> movables = LoadsLevels.generateMovables(levelFile);

        player = (Player) movables.stream()
                .filter(m -> m instanceof Player)
                .findFirst()
                .orElseThrow(IllegalStateException::new);

        goal = (Goal) tiles.stream()
                .filter(Tile::isGoal)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public void handle(long now) {
        foreground.clearRect(0, 0, screenWidth, screenHeight);
        player.draw(foreground);
        player.move(tiles);

        if (player.collidesWith(goal)) {
            gameOver();
        }
    }

    private void gameOver() {
        exit();
    }
}
