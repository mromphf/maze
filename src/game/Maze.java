package game;

import game.abstraction.Collidable;
import game.abstraction.Tile;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Maze {

    private final List<List<Tile>> tiles;
    private final Tile goal;

    public Maze(List<List<Tile>> tiles) {
        this.tiles = tiles;

        goal = tiles.stream()
                .flatMap(List::stream)
                .filter(Tile::isGoal)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public Collection<Tile> tiles() {
        return tiles.stream().flatMap(List::stream).collect(Collectors.toSet());
    }

    public boolean gameIsOver(Collidable player) {
        return player.collidesWith(goal);
    }
}
