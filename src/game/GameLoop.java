package game;

import game.abstraction.Entity;
import game.abstraction.Movable;
import game.abstraction.Predicate;

import io.Controller;
import io.Screen;

import javafx.animation.AnimationTimer;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GameLoop extends AnimationTimer {

    private final Screen screen;
    private final Collection<Entity> staticTiles;
    private final Collection<Entity> dynamicTiles;
    private final Collection<Movable> actors;
    private final Controller parent;
    private boolean paused;

    public GameLoop(Controller parent, Screen screen, List<List<Character>> levelFile) {
        this.screen = screen;
        this.parent = parent;
        this.staticTiles = LoadsLevels.generateStaticTiles(levelFile);
        this.dynamicTiles = LoadsLevels.generateDynamicTiles(levelFile);
        this.actors = LoadsLevels.generateActors(levelFile);
        this.paused = false;

        screen.drawOnBackground(staticTiles);
    }

    @Override
    public void handle(long now) {
        Collection<Entity> actorsAndDynamicTiles = Stream.concat(dynamicTiles.stream(), actors.stream())
                .collect(Collectors.toSet());

        Set<Entity> allTiles = Stream
                .concat(staticTiles.stream(), dynamicTiles.stream())
                .collect(Collectors.toSet());

        screen.drawOnMidground(dynamicTiles);
        screen.drawOnForeground(actors);

        actors.forEach(a -> {
            a.move(allTiles);

            actors.forEach(otherActor -> {
                if (a.collidesWith(otherActor)) {
                    a.onCollide(otherActor);
                }
            });
        });

        dynamicTiles.forEach(t -> {
            t.examine(dynamicTiles);

            actors.forEach(a -> {
                if (a.collidesWith(t)) {
                    t.onCollide(a);
                }
            });
        });

        if (actorsAndDynamicTiles.stream().anyMatch(t -> t.matches(Predicate.GAME_OVER))) {
            gameOver();
        }
    }

    public void pause() {
        if (paused) {
            paused = false;
            start();
        } else {
            paused = true;
            stop();
        }
    }

    private void gameOver() {
        stop();
        screen.reset();
        parent.loadNextLevel();
    }
}
