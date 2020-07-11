package game;

import game.abstraction.Collidable;
import game.abstraction.Tile;
import game.concrete.Player;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Maze {

    private final List<List<Tile>> tiles;
    private final Tile startLocation;
    private final Tile goal;

    public Maze(List<List<Tile>> tiles) {
        this.tiles = tiles;

        startLocation = tiles.stream()
                .flatMap(List::stream)
                .filter(Tile::isStartLocation)
                .findFirst()
                .orElseThrow(IllegalStateException::new);

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

    public Player playerAtStartingLocation() {
        return new Player(startLocation.getX() + 7, startLocation.getY() + 7);
    }
}
