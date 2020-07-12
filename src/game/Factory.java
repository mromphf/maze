package game;

import game.abstraction.Actor;
import game.abstraction.Tile;
import game.concrete.*;

import java.util.Optional;

public class Factory {

    private static final int TILE_OFFSET = 7;

    public static Tile buildTile(Character symbol, int vertical, int horizontal) {
        switch (symbol) {
            case 'b':
                return new Block(vertical * 50, horizontal * 50);
            case 's':
                return new Start(vertical * 50, horizontal * 50);
            case 'g':
                return new Goal(vertical * 50, horizontal * 50);
            default:
                return new Empty(vertical * 50, horizontal * 50);
        }
    }

    public static Optional<Actor> buildActor(Character symbol, int x, int y) {
        if (symbol == 's') {
            return Optional.of(new Player((x * 50) + TILE_OFFSET, (y * 50) + TILE_OFFSET));
        }
        return Optional.empty();
    }
}
