package game;

import game.abstraction.Entity;
import game.abstraction.MovableGameObject;

import java.util.*;
import java.util.stream.Collectors;

public class LoadsLevels {

    public static Collection<Entity> generateStaticTiles(Map<Integer, List<Character>> symbols) {
        List<Entity> tiles = new ArrayList<>();

        for (int horizontal = 0; horizontal < symbols.keySet().size(); horizontal++) {
            for (int vertical = 0; vertical < symbols.get(horizontal).size(); vertical++) {
                Character symbol = symbols.get(horizontal).get(vertical);
                tiles.add(Factory.buildStaticTile(symbol, vertical, horizontal));
            }
        }

        return tiles;
    }

    public static Collection<Entity> generateDynamicTiles(Map<Integer, List<Character>> symbols) {
        List<Optional<Entity>> tiles = new ArrayList<>();

        for (int horizontal = 0; horizontal < symbols.keySet().size(); horizontal++) {
            for (int vertical = 0; vertical < symbols.get(horizontal).size(); vertical++) {
                Character symbol = symbols.get(horizontal).get(vertical);
                tiles.add(Factory.buildDynamicTile(symbol, vertical, horizontal));
            }
        }

        return tiles.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    public static Collection<MovableGameObject> generateActors(Map<Integer, List<Character>> symbols) {
        List<Optional<MovableGameObject>> movables = new ArrayList<>();

        for (int horizontal = 0; horizontal < symbols.keySet().size(); horizontal++) {
            for (int vertical = 0; vertical < symbols.get(horizontal).size(); vertical++) {
                Character symbol = symbols.get(horizontal).get(vertical);
                movables.add(Factory.buildActor(symbol, vertical, horizontal));
            }
        }

        return movables.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
