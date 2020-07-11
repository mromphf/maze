package game;

import game.abstraction.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoadsLevels {

    public static List<List<Tile>> generateTiles(Map<Integer, List<Character>> symbols) {
        List<List<Tile>> tiles = new ArrayList<>();

        for (int horizontal = 0; horizontal < symbols.keySet().size(); horizontal++) {
            tiles.add(new ArrayList<>());

            for (int vertical = 0; vertical < symbols.get(horizontal).size(); vertical++) {

                Character symbol = symbols.get(horizontal).get(vertical);
                tiles.get(horizontal).add(Factory.buildTile(symbol, vertical, horizontal));

            }
        }

        return tiles;
    }
}
