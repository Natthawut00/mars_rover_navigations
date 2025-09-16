package com.marsrover;

import com.marsrover.grid.Grid;
import com.marsrover.rover.Direction;
import com.marsrover.rover.Position;
import com.marsrover.rover.Rover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoverTest {

    private Rover rover;

    @BeforeEach
    void setUp() {
        Grid grid = new Grid(5, 5);
        rover = new Rover(new Position(0, 0), Direction.N, grid);
    }

    @Test
    void testInitialPosition() {
        assertEquals(0, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
        assertEquals(Direction.N, rover.getDirection());
    }

    @Test
    void testMoveForwardNorth() {
        rover.setDirection(Direction.N);
        String status = rover.moveForward();
        assertEquals("Success", status);
        assertEquals(0, rover.getPosition().getX());
        assertEquals(1, rover.getPosition().getY());
    }

    @Test
    void testMoveForwardEast() {
        rover.setDirection(Direction.E);
        String status = rover.moveForward();
        assertEquals("Success", status);
        assertEquals(1, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
    }

    @Test
    void testMoveOutOfBounds() {
        rover.setPosition(new Position(0, 4));
        rover.setDirection(Direction.N);
        String status = rover.moveForward();
        assertEquals("Out of bounds", status);
        assertEquals(0, rover.getPosition().getX());
        assertEquals(4, rover.getPosition().getY());
    }

    @Test
    void testDiagonalMoveNE() {
        rover.setDirection(Direction.NE);
        String status = rover.moveForward();
        assertEquals("Success", status);
        assertEquals(1, rover.getPosition().getX());
        assertEquals(1, rover.getPosition().getY());
    }

    @Test
    void testDiagonalMoveSE() {
        rover.setDirection(Direction.SE);
        rover.setPosition(new Position(2, 2));
        String status = rover.moveForward();
        assertEquals("Success", status);
        assertEquals(3, rover.getPosition().getX());
        assertEquals(1, rover.getPosition().getY());
    }

    @Test
    void testDiagonalMoveSW() {
        rover.setDirection(Direction.SW);
        rover.setPosition(new Position(2, 2));
        String status = rover.moveForward();
        assertEquals("Success", status);
        assertEquals(1, rover.getPosition().getX());
        assertEquals(1, rover.getPosition().getY());
    }

    @Test
    void testDiagonalMoveNW() {
        rover.setDirection(Direction.NW);
        rover.setPosition(new Position(2, 2));
        String status = rover.moveForward();
        assertEquals("Success", status);
        assertEquals(1, rover.getPosition().getX());
        assertEquals(3, rover.getPosition().getY());
    }

    @Test
    void testReset() {
        rover.setPosition(new Position(3, 3));
        rover.setDirection(Direction.W);
        rover.reset();

        assertEquals(0, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
        assertEquals(Direction.N, rover.getDirection());
    }
}
