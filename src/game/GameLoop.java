package game;

import game.abstraction.GameObject;
import game.abstraction.MovableGameObject;
import game.abstraction.Predicate;
import game.concrete.Goal;
import game.concrete.Player;

import io.Controller;
import io.Screen;

import javafx.animation.AnimationTimer;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class GameLoop extends AnimationTimer {

    private final Screen screen;
    private final Collection<GameObject> tiles;
    private final Collection<MovableGameObject> actors;
    private final Player player;
    private final Collection<MovableGameObject> enemies;
    private final Controller parent;
    private final Goal goal;

    public GameLoop(Controller parent, Screen screen, Map<Integer, List<Character>> levelFile) {
        this.screen = screen;
        this.parent = parent;
        this.tiles = LoadsLevels.generateTiles(levelFile);
        this.actors = LoadsLevels.generateActors(levelFile);

        screen.drawOnBackground(tiles);

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
        screen.drawOnBackground(tiles);
        screen.drawOnForeground(actors);

        actors.forEach(a -> a.move(tiles));

        tiles.stream()
                .filter(t -> t.matches(Predicate.IS_SWITCH) || t.matches(Predicate.IS_GOAL))
                .forEach(t -> {

                    t.examine(tiles);

                    if (player.collidesWith(t)) {
                        t.onCollide(player);
                    }
                });

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
        stop();
        screen.reset();
        parent.loadNextLevel();
    }
}
