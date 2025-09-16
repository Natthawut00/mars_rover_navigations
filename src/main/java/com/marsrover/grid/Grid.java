package com.marsrover.grid;

import com.marsrover.rover.Position;
import java.util.HashSet;
import java.util.Set;

public class Grid {
    private final int width, height;
    private final Set<Obstacle> obstacles = new HashSet<>();

    public Grid(int width, int height) { this.width = width; this.height = height; }

    public void addObstacle(int x, int y) { obstacles.add(new Obstacle(x, y)); }

    public boolean isInside(Position pos) {
        return pos.getX() >= 0 && pos.getX() < width && pos.getY() >= 0 && pos.getY() < height;
    }

    public boolean hasObstacle(Position pos) {
        return obstacles.contains(new Obstacle(pos.getX(), pos.getY()));
    }
}
