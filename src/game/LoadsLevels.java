package game;

import java.util.ArrayList;
import java.util.List;

public class LoadsLevels {

    public static List<GameObject> generateTiles(char[][] symbols) {

        List<GameObject> drawables = new ArrayList<>();

        for (int x = 0; x < symbols.length; x++) {
            for (int y = 0; y < symbols[x].length; y++) {
                if (symbols[x][y] == 'b') {
                    drawables.add(new Block(x * 50, y * 50));
                } else {
                    drawables.add(new Empty(x * 50, y * 50));
                }
            }
        }

        return drawables;
    }
}
