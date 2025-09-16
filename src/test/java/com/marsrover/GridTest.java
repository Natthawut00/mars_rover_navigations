package com.marsrover;

import com.marsrover.grid.Grid;
import com.marsrover.rover.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    void testInsideGrid() {
        Grid grid = new Grid(5, 5);
        assertTrue(grid.isInside(new Position(4, 4)));
        assertFalse(grid.isInside(new Position(5, 5)));
    }

    @Test
    void testAddObstacle() {
        Grid grid = new Grid(5, 5);
        grid.addObstacle(2, 2);
        assertTrue(grid.hasObstacle(new Position(2, 2)));
        assertFalse(grid.hasObstacle(new Position(1, 1)));
    }
}
