package com.marsrover.rover;

import com.marsrover.grid.Grid;
import com.marsrover.constants.NavigationConstants;

public class Rover {

    private Position position;
    private Direction direction;
    private final Grid grid;

    private final Position startPosition;
    private final Direction startDirection;

    public Rover(Position position, Direction direction, Grid grid) {
        this.position = position;
        this.direction = direction;
        this.grid = grid;

        this.startPosition = new Position(position.getX(), position.getY());
        this.startDirection = direction;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Grid getGrid() {
        return grid;
    }

    public String moveForward() {
        Position next = new Position(position.getX() + direction.getDx(), position.getY() + direction.getDy());

        if (!grid.isInside(next)) {
            return NavigationConstants.STATUS_OUT;
        }
        if (grid.hasObstacle(next)) {
            return NavigationConstants.STATUS_OBSTACLE;
        }

        this.position = next;
        return NavigationConstants.STATUS_SUCCESS;
    }

    public void reset() {
        this.position = new Position(startPosition.getX(), startPosition.getY());
        this.direction = startDirection;
    }
}
