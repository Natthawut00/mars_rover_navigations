package com.marsrover;

import com.marsrover.grid.Grid;
import com.marsrover.rover.*;
import com.marsrover.navigation.*;

import java.util.List;

public class NavigationApp {

    public static NavigationResult navigateRover(int gridSize, List<Position> obstacles, String commands, boolean use8Directions) {
        Grid grid = new Grid(gridSize, gridSize);
        for (Position o : obstacles) {
            grid.addObstacle(o.getX(), o.getY());
        }

        Rover rover = new Rover(new Position(0, 0), Direction.N, grid);
        RoverController ctrl = new RoverController(rover);

        return ctrl.executeCommands(commands, use8Directions);
    }
}
