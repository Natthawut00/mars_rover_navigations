package com.marsrover.command;

import com.marsrover.rover.Rover;
import com.marsrover.constants.NavigationConstants;
import com.marsrover.rover.Position;
import com.marsrover.rover.Direction;

public record DiagonalMoveCommand(Direction direction) implements Command {

    @Override
    public String execute(Rover rover) {
        Position next = new Position(
                rover.getPosition().getX() + direction.getDx(),
                rover.getPosition().getY() + direction.getDy()
        );

        if (!rover.getGrid().isInside(next)) {
            return NavigationConstants.STATUS_OUT;
        }
        if (rover.getGrid().hasObstacle(next)) {
            return NavigationConstants.STATUS_OBSTACLE;
        }

        rover.setPosition(next);
        return NavigationConstants.STATUS_SUCCESS;
    }
}
