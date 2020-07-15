package game;

import game.abstraction.Entity;
import game.abstraction.MovableGameObject;
import game.concrete.*;

import java.util.Optional;

public class Factory {

    private static final int TILE_SIZE = 60;
    private static final int TILE_OFFSET = 13;

    public static Entity buildStaticTile(Character symbol, int vertical, int horizontal) {
        switch (symbol) {
            case 'b':
                return new Block(vertical * TILE_SIZE, horizontal * TILE_SIZE);
            case 's':
                return new Start(vertical * TILE_SIZE, horizontal * TILE_SIZE);
            default:
                return new Empty(vertical * TILE_SIZE, horizontal * TILE_SIZE);
        }
    }

    public static Optional<Entity> buildDynamicTile(Character symbol, int vertical, int horizontal) {
        switch (symbol) {
            case 'g':
                return Optional.of(new Goal(vertical * TILE_SIZE, horizontal * TILE_SIZE, false));
            case 'x':
                return Optional.of(new Switch(vertical * TILE_SIZE, horizontal * TILE_SIZE));
            default:
                return Optional.empty();
        }
    }

    public static Optional<MovableGameObject> buildActor(Character symbol, int x, int y) {
        switch (symbol) {
            case 's':
                return Optional.of(new Player(
                        (x * TILE_SIZE) + TILE_OFFSET,
                        (y * TILE_SIZE) + TILE_OFFSET,
                        5));
            case 'u':
                return Optional.of(new BouncesUpAndDown(
                        (x * TILE_SIZE) + TILE_OFFSET,
                        (y * TILE_SIZE) + TILE_OFFSET,
                        5));
            case 'l':
                return Optional.of(new BouncesLeftAndRight(
                        (x * TILE_SIZE) + TILE_OFFSET,
                        (y * TILE_SIZE) + TILE_OFFSET,
                        5));
            default:
                return Optional.empty();
        }
    }
}
