package game;

import java.util.List;

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

    public GameObject getGoal() {
        return goal;
    }

    public Player playerAtStartingLocation() {
        return new Player(startLocation.x + 7, startLocation.y + 7);
    }

    public boolean canMoveHere(GameObject object) {
        return this.gameObjects.stream().noneMatch(t -> t.collidesWith(object));
    }
}
