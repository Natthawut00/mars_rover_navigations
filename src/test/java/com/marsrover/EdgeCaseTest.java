package com.marsrover;

import com.marsrover.grid.Grid;
import com.marsrover.rover.Direction;
import com.marsrover.rover.Position;
import com.marsrover.rover.Rover;
import com.marsrover.navigation.RoverController;
import com.marsrover.navigation.NavigationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EdgeCaseTest {

    @Test
    void testRoverBlockedByObstacle() {
        Grid grid = new Grid(5, 5);
        grid.addObstacle(0, 1);
        Rover rover = new Rover(new Position(0, 0), Direction.N, grid);

        String status = rover.moveForward();
        assertEquals("Obstacle encountered", status);
        assertEquals(0, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
    }

    @Test
    void testRoverOutOfBoundsAtStart() {
        Grid grid = new Grid(3, 3);
        Rover rover = new Rover(new Position(2, 2), Direction.N, grid);
        String status = rover.moveForward();
        assertEquals("Out of bounds", status);
    }

    @Test
    void testNegativePosition() {
        Grid grid = new Grid(3, 3);
        Rover rover = new Rover(new Position(0, 0), Direction.W, grid);
        String status = rover.moveForward();
        assertEquals("Out of bounds", status);
    }

    @Test
    void testMinimalGrid1x1() {
        Grid grid = new Grid(1, 1);
        Rover rover = new Rover(new Position(0, 0), Direction.N, grid);
        String status = rover.moveForward();
        assertEquals("Out of bounds", status);
    }

    @Test
    void testLargeGrid() {
        int size = 1000;
        Grid grid = new Grid(size, size);
        Rover rover = new Rover(new Position(500, 500), Direction.N, grid);
        String status = rover.moveForward();
        assertEquals("Success", status);
    }

    @Test
    void testLongCommandString() {
        Grid grid = new Grid(5, 5);
        Rover rover = new Rover(new Position(0, 0), Direction.N, grid);
        RoverController ctrl = new RoverController(rover);

        String longCommands = "M".repeat(11);
        NavigationResult result = ctrl.executeCommands(longCommands, false);

        assertTrue(result.status().startsWith("Invalid command"),
                "Expected status to start with 'Invalid command' but got: " + result.status());
    }

    @Test
    void testInvalidCommand() {
        Grid grid = new Grid(5, 5);
        Rover rover = new Rover(new Position(0, 0), Direction.N, grid);
        RoverController ctrl = new RoverController(rover);

        NavigationResult result = ctrl.executeCommands("MXM", false);
        assertTrue(result.status().startsWith("Invalid command"));
    }
}
