import java.util.ArrayList;
import java.util.List;

public class Maze {
    public static List<Drawable> make() {
        List<Drawable> drawables = new ArrayList<>();
        for (int i = 0; i < 1500; i += 50) {
            drawables.add(new Block(i, 0));
        }
        drawables.add(new Block(0, 50));
        return drawables;
    }
}
