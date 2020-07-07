package game;

import java.util.ArrayList;
import java.util.List;

public class LoadsLevels {

    public static List<GameObject> generateTiles(char[][] symbols) {

        List<GameObject> gameObjects = new ArrayList<>();

        for (int x = 0; x < symbols.length; x++) {
            for (int y = 0; y < symbols[x].length; y++) {
                if (symbols[x][y] == 'b') {
                    gameObjects.add(new Block(x * 50, y * 50));
                } else if (symbols[x][y] == 's') {
                    gameObjects.add(new Start(x * 50, y * 50));
                } else if (symbols[x][y] == 'g') {
                    gameObjects.add(new Goal(x * 50, y * 50));
                } else {
                    gameObjects.add(new Empty(x * 50, y * 50));
                }
            }
        }

        return gameObjects;
    }
}
