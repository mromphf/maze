package game;

import game.abstraction.Entity;
import game.abstraction.Movable;
import game.concrete.*;

import java.util.Optional;

public class Factory {

    private static final int TILE_SIZE = 55;
    private static final int TILE_OFFSET = 10;

    public static Entity buildStaticTile(Character symbol, int x, int y) {
        switch (symbol) {
            case 'b':
                return new Block(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            case 's':
                return new Start(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            case 'p':
                return new Pit(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            default:
                return new Empty(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    public static Optional<Entity> buildDynamicTile(Character symbol, int x, int y) {
        switch (symbol) {
            case 'g':
                return Optional.of(new Goal(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, false));
            case 'x':
                return Optional.of(new Switch(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE));
            case 'o':
                return Optional.of(new OrangeSwitch(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE));
            case 'q':
                return Optional.of(new OrangeBarrier(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE));
            case 'n':
                return Optional.of(new BlueSwitch(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE));
            case 'w':
                return Optional.of(new BlueBarrier(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE));
            case '1':
            case '2':
            case '3':
            case '4':
                int ordinal = symbol - '0';
                return Optional.of(new OrderedSwitch(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, ordinal));
            default:
                return Optional.empty();
        }
    }

    public static Optional<Movable> buildActor(Character symbol, int x, int y) {
        switch (symbol) {
            case 's':
                return Optional.of(new Player(
                        (x * TILE_SIZE) + TILE_OFFSET,
                        (y * TILE_SIZE) + TILE_OFFSET,
                        35, 35, 5));
            case 'u':
                return Optional.of(new BouncesUpAndDown(
                        (x * TILE_SIZE) + TILE_OFFSET,
                        (y * TILE_SIZE) + TILE_OFFSET,
                        35, 35, 5));
            case 'l':
                return Optional.of(new BouncesLeftAndRight(
                        (x * TILE_SIZE) + TILE_OFFSET,
                        (y * TILE_SIZE) + TILE_OFFSET,
                        35, 35, 5));
            default:
                return Optional.empty();
        }
    }
}
