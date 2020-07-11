package game;

import game.abstraction.Tile;
import game.concrete.Block;
import game.concrete.Empty;
import game.concrete.Goal;
import game.concrete.Start;

public class Factory {
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
}
