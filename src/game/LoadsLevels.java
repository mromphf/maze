package game;

import game.abstraction.Movable;
import game.abstraction.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class LoadsLevels {

    public static Collection<Tile> generateTiles(Map<Integer, List<Character>> symbols) {
        List<Tile> tiles = new ArrayList<>();

        for (int horizontal = 0; horizontal < symbols.keySet().size(); horizontal++) {
            for (int vertical = 0; vertical < symbols.get(horizontal).size(); vertical++) {
                Character symbol = symbols.get(horizontal).get(vertical);
                tiles.add(Factory.buildTile(symbol, vertical, horizontal));
            }
        }

        return tiles;
    }

    public static Collection<Movable> generateMovables(Map<Integer, List<Character>> symbols) {
        List<Optional<Movable>> movables = new ArrayList<>();

        for (int horizontal = 0; horizontal < symbols.keySet().size(); horizontal++) {
            for (int vertical = 0; vertical < symbols.get(horizontal).size(); vertical++) {
                Character symbol = symbols.get(horizontal).get(vertical);
                movables.add(Factory.buildMovable(symbol, vertical, horizontal));
            }
        }

        return movables.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
