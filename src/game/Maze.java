package game;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    public static List<Drawable> make() {

        List<Drawable> drawables = new ArrayList<>();

        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                if (x == 0 || y == 0 || x == 19 || y == 19) {
                    drawables.add(new Block(x * 50, y * 50));
                }
                else {
                    drawables.add(new Empty(x * 50, y * 50));
                }
            }
        }

        return drawables;
    }
}
