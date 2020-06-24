package game;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    public static List<Drawable> make() {
        List<Drawable> drawables = new ArrayList<>();
        for (int i = 0; i < 1500; i += 50) {
            drawables.add(new Block(i, 0));
        }
        for (int i = 0; i < 1050; i += 50) {
            drawables.add(new Block(0, i));
        }
        for (int i = 0; i < 1050; i += 50) {
            drawables.add(new Block(1500, i));
        }
        for (int i = 0; i < 1500; i += 50) {
            drawables.add(new Block(i, 1050));
        }
        return drawables;
    }
}
