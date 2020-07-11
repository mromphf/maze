package game;

import game.abstraction.Tile;
import game.concrete.Block;
import game.concrete.Empty;
import game.concrete.Goal;
import game.concrete.Start;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoadsLevels {

    public static List<Tile> generateTiles(Map<Integer, List<Character>> symbols) {

        List<Tile> tiles = new ArrayList<>();

        for (int horizontal = 0; horizontal < symbols.keySet().size(); horizontal++) {
            for (int vertical = 0; vertical < symbols.get(horizontal).size(); vertical++) {
                if (symbols.get(horizontal).get(vertical) == 'b') {
                    tiles.add(new Block(vertical * 50, horizontal * 50));
                } else if (symbols.get(horizontal).get(vertical) == 's') {
                    tiles.add(new Start(vertical * 50, horizontal * 50));
                } else if (symbols.get(horizontal).get(vertical) == 'g') {
                    tiles.add(new Goal(vertical * 50, horizontal * 50));
                } else {
                    tiles.add(new Empty(vertical * 50, horizontal * 50));
                }
            }
        }

        return tiles;
    }
}
