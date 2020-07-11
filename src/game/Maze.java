package game;

import game.abstraction.Collidable;
import game.abstraction.Drawable;
import game.abstraction.Tile;
import game.concrete.Goal;
import game.concrete.Player;
import game.concrete.Start;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class Maze {

    private final Collection<Tile> tiles;
    private final Tile startLocation;
    private final Tile goal;

    public Maze(Collection<Tile> tiles) {
        this.tiles = tiles;
        startLocation = tiles.stream().filter(o -> o instanceof Start).findFirst().get();
        goal = tiles.stream().filter(o -> o instanceof Goal).findFirst().get();
    }

    public Collection<? extends Drawable> backgroundGraphics() {
        return tiles;
    }

    public Collection<? extends Collidable> allObstacles() {
        return tiles.stream().filter(Objects::nonNull).collect(Collectors.toSet());
    }

    public boolean gameIsOver(Collidable player) {
        return player.collidesWith(goal);
    }

    public Player playerAtStartingLocation() {
        return new Player(startLocation.getX() + 7, startLocation.getY() + 7);
    }
}
