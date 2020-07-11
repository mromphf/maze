package game;

import game.abstraction.Collidable;
import game.abstraction.GameObject;
import game.concrete.Player;
import io.File;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Screen;

import java.util.Collection;
import java.util.List;

import static javafx.application.Platform.exit;

public class GameLoop extends AnimationTimer {

    private final GraphicsContext foreground;
    private final GraphicsContext background;
    private final double screenWidth;
    private final double screenHeight;
    private final Maze maze = new Maze(LoadsLevels.generateTiles(File.loadLevel("src/level1.csv")));
    private final Collection<? extends Collidable> obstacles = maze.allObstacles();
    private final Player player = maze.playerAtStartingLocation();

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

        List<GameObject> tiles = maze.getGameObjects();
        tiles.forEach(d -> d.draw(background));
    }

    @Override
    public void handle(long now) {
        foreground.clearRect(0, 0, screenWidth, screenHeight);
        player.draw(foreground);
        player.move(obstacles);

        if (player.collidesWith(maze.getGoal())) {
            gameOver();
        }
    }

    private void gameOver() {
        exit();
    }
}
