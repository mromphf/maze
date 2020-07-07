package game;

import java.util.ArrayList;
import java.util.List;

public class LoadsLevels {

    public static List<GameObject> generateTiles(char[][] symbols) {

        List<GameObject> gameObjects = new ArrayList<>();

        for (int horizontal = 0; horizontal < symbols.length; horizontal++) {
            for (int vertical = 0; vertical < symbols[horizontal].length; vertical++) {
                if (symbols[horizontal][vertical] == 'b') {
                    gameObjects.add(new Block(vertical * 50, horizontal * 50));
                } else if (symbols[horizontal][vertical] == 's') {
                    gameObjects.add(new Start(vertical * 50, horizontal * 50));
                } else if (symbols[horizontal][vertical] == 'g') {
                    gameObjects.add(new Goal(vertical * 50, horizontal * 50));
                } else {
                    gameObjects.add(new Empty(vertical * 50, horizontal * 50));
                }
            }
        }

        return gameObjects;
    }
}
