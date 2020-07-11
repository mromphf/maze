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

    public static List<List<Tile>> generateTiles(Map<Integer, List<Character>> symbols) {

        List<List<Tile>> tiles = new ArrayList<>();

        for (int horizontal = 0; horizontal < symbols.keySet().size(); horizontal++) {
            tiles.add(new ArrayList<>());
            for (int vertical = 0; vertical < symbols.get(horizontal).size(); vertical++) {
                if (symbols.get(horizontal).get(vertical) == 'b') {
                    tiles.get(horizontal).add(new Block(vertical * 50, horizontal * 50));
                } else if (symbols.get(horizontal).get(vertical) == 's') {
                    tiles.get(horizontal).add(new Start(vertical * 50, horizontal * 50));
                } else if (symbols.get(horizontal).get(vertical) == 'g') {
                    tiles.get(horizontal).add(new Goal(vertical * 50, horizontal * 50));
                } else {
                    tiles.get(horizontal).add(new Empty(vertical * 50, horizontal * 50));
                }
            }
        }

        return tiles;
    }
}
