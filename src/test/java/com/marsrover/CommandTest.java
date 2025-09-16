package com.marsrover;

import com.marsrover.command.*;
import com.marsrover.rover.*;
import com.marsrover.grid.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    private Rover rover;

    @BeforeEach
    void setUp() {
        Grid grid = new Grid(5, 5);
        rover = new Rover(new Position(0, 0), Direction.N, grid);
    }

    @Test
    void testMoveCommand() {
        Command move = new MoveCommand();
        String status = move.execute(rover);
        assertEquals("Success", status);
        assertEquals(0, rover.getPosition().getX());
        assertEquals(1, rover.getPosition().getY());
    }

    @Test
    void testTurnLeftCommand4Directions() {
        Command left = new TurnLeftCommand(false);
        left.execute(rover);
        assertEquals(Direction.W, rover.getDirection());
    }

    @Test
    void testTurnRightCommand4Directions() {
        Command right = new TurnRightCommand(false);
        right.execute(rover);
        assertEquals(Direction.E, rover.getDirection());
    }

    @Test
    void testDiagonalMoveNE() {
        rover.setPosition(new Position(1, 1));
        rover.setDirection(Direction.NE);
        String status = new DiagonalMoveCommand(Direction.NE).execute(rover);
        assertEquals("Success", status);
        assertEquals(2, rover.getPosition().getX());
        assertEquals(2, rover.getPosition().getY());
    }
}
