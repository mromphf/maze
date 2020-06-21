import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class Keyboard {
    private static final Map<KeyCode, Boolean> keysPressed = new HashMap<>();

    public static boolean isPressed(KeyCode keyCode) {
        return keysPressed.get(keyCode);
    }

    public static void press(KeyCode keyCode) {
        keysPressed.put(keyCode, true);
    }

    public static void release(KeyCode keyCode) {
        keysPressed.put(keyCode, false);
    }
}
