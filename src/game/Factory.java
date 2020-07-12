package game;

import game.abstraction.Actor;
import game.abstraction.Tile;
import game.concrete.*;

import java.util.Optional;

public class Factory {

    private static final int TILE_SIZE = 50;
    private static final int TILE_OFFSET = 7;

    public static Tile buildTile(Character symbol, int vertical, int horizontal) {
        switch (symbol) {
            case 'b':
                return new Block(vertical * TILE_SIZE, horizontal * TILE_SIZE);
            case 's':
                return new Start(vertical * TILE_SIZE, horizontal * TILE_SIZE);
            case 'g':
                return new Goal(vertical * TILE_SIZE, horizontal * TILE_SIZE);
            default:
                return new Empty(vertical * TILE_SIZE, horizontal * TILE_SIZE);
        }
    }

    public static Optional<Actor> buildActor(Character symbol, int x, int y) {
        if (symbol == 's') {
            return Optional.of(new Player(
                    (x * TILE_SIZE) + TILE_OFFSET,
                    (y * TILE_SIZE) + TILE_OFFSET,
                    5));
        }
        return Optional.empty();
    }
}
