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

    public boolean canMoveHere(GameObject object) {
        return this.gameObjects.stream().noneMatch(t -> t.collidesWith(object));
    }
}
