package game;

import game.abstraction.Tile;
import game.concrete.Block;
import game.concrete.Empty;
import game.concrete.Goal;
import game.concrete.Start;

import java.util.ArrayList;
import java.util.List;

public class LoadsLevels {

    public static List<Tile> generateTiles(char[][] symbols) {

        List<Tile> tiles = new ArrayList<>();

        for (int horizontal = 0; horizontal < symbols.length; horizontal++) {
            for (int vertical = 0; vertical < symbols[horizontal].length; vertical++) {
                if (symbols[horizontal][vertical] == 'b') {
                    tiles.add(new Block(vertical * 50, horizontal * 50));
                } else if (symbols[horizontal][vertical] == 's') {
                    tiles.add(new Start(vertical * 50, horizontal * 50));
                } else if (symbols[horizontal][vertical] == 'g') {
                    tiles.add(new Goal(vertical * 50, horizontal * 50));
                } else {
                    tiles.add(new Empty(vertical * 50, horizontal * 50));
                }
            }
        }

        return tiles;
    }
}
