package game;

import java.util.List;

public class Maze {

    private final List<GameObject> gameObjects;

    public Maze(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Player playerAtStartingLocation() {
        GameObject startingLocation = gameObjects.stream().filter(o -> o instanceof Start).findFirst().get();
        return new Player(startingLocation.x + 7, startingLocation.y + 7);
    }

    public boolean canMoveHere(GameObject object) {
        return this.gameObjects.stream().noneMatch(t -> t.collidesWith(object));
    }
}
