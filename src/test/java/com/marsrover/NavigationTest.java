package com.marsrover;

import com.marsrover.grid.Grid;
import com.marsrover.navigation.NavigationResult;
import com.marsrover.rover.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class NavigationTest {

    @Test
    void testNavigationAppSimpleMove() {
        NavigationResult result = NavigationApp.navigateRover(
                5,
                Collections.emptyList(),
                "M",
                false
        );
        assertEquals(0, result.x());
        assertEquals(1, result.y());
        assertEquals("Success", result.status());
    }


    @Test
    void testNavigationAppTurnRight() {
        NavigationResult result = NavigationApp.navigateRover(
                5,
                Collections.emptyList(),
                "R",
                false
        );
        assertEquals("E", result.direction());
    }


    @Test
    void testNavigationWithObstacle() {
            Grid grid = new Grid(5, 5);
            grid.addObstacle(0, 1);
            Rover rover = new Rover(new Position(0, 0), Direction.N, grid);
            String status = rover.moveForward();
            assertEquals("Obstacle encountered", status);
        }
    }
