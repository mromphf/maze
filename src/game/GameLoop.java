package game;

import game.abstraction.GameObject;
import game.abstraction.MovableGameObject;
import game.abstraction.Predicate;
import game.concrete.Goal;
import game.concrete.Player;
import game.concrete.Switch;

import io.Controller;
import io.Screen;

import javafx.animation.AnimationTimer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GameLoop extends AnimationTimer {

    private final Screen screen;
    private final Collection<GameObject> tiles;
    private final Collection<MovableGameObject> actors;
    private final Player player;
    private final Collection<MovableGameObject> enemies;
    private final Collection<Switch> switches;
    private final Controller parent;
    private Goal goal;

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

        switches = tiles.stream()
                .filter(a -> a.matches(Predicate.IS_SWITCH))
                .map(a -> new Switch(a.getX(), a.getY()))
                .collect(Collectors.toSet());
    }

    @Override
    public void handle(long now) {
        Collection<GameObject> goals = new HashSet<GameObject>() {{ add(goal); }};

        screen.drawOnBackground(Stream.concat(switches.stream(), goals.stream()).collect(Collectors.toSet()));
        screen.drawOnForeground(actors);

        actors.forEach(a -> {
            a.move(tiles);
        });

        switches.forEach(s -> {
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
        stop();
        screen.reset();
        parent.loadNextLevel();
    }
}
