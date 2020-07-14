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
import java.util.stream.Stream;


public class GameLoop extends AnimationTimer {

    private final Screen screen;
    private final Collection<GameObject> staticTiles;
    private final Collection<GameObject> dynamicTiles;
    private final Collection<MovableGameObject> actors;
    private final Player player;
    private final Controller parent;
    private final Goal goal;

    public GameLoop(Controller parent, Screen screen, Map<Integer, List<Character>> levelFile) {
        this.screen = screen;
        this.parent = parent;
        this.staticTiles = LoadsLevels.generateStaticTiles(levelFile);
        this.dynamicTiles = LoadsLevels.generateDynamicTiles(levelFile);
        this.actors = LoadsLevels.generateActors(levelFile);

        screen.drawOnBackground(staticTiles);

        player = (Player) actors.stream()
                .filter(m -> m.matches(Predicate.IS_PLAYER))
                .findFirst()
                .orElseThrow(IllegalStateException::new);

        goal = (Goal) dynamicTiles.stream()
                .filter(t -> t.matches(Predicate.IS_GOAL))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public void handle(long now) {
        screen.drawOnBackground(dynamicTiles);
        screen.drawOnForeground(actors);

        actors.forEach(a -> a.move(
                Stream.concat(staticTiles.stream(), dynamicTiles.stream()).collect(Collectors.toSet()))
        );

        dynamicTiles.forEach(t -> {
            t.examine(dynamicTiles);

            actors.forEach(a -> {
                if (a.collidesWith(t)) {
                    t.onCollide(a);
                }
            });
        });

        actors.stream()
                .filter(a -> a.matches(Predicate.IS_ENEMY))
                .forEach(e -> {
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
