package game;

import game.abstraction.Collidable;
import game.abstraction.GameObject;
import game.concrete.Goal;
import game.concrete.Player;
import game.concrete.Start;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Maze {

    private final List<GameObject> gameObjects;
    private final GameObject startLocation;
    private final GameObject goal;

    public Maze(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
        startLocation = gameObjects.stream().filter(o -> o instanceof Start).findFirst().get();
        goal = gameObjects.stream().filter(o -> o instanceof Goal).findFirst().get();
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Collection<? extends Collidable> allObstacles() {
        return gameObjects.stream().filter(Objects::nonNull).collect(Collectors.toSet());
    }

    public GameObject getGoal() {
        return goal;
    }

    public Player playerAtStartingLocation() {
        return new Player(startLocation.getX() + 7, startLocation.getY() + 7);
    }
}
