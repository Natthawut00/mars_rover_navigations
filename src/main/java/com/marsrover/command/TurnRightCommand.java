package com.marsrover.command;

import com.marsrover.rover.Rover;
import com.marsrover.constants.NavigationConstants;

public record TurnRightCommand(boolean use8Directions) implements Command {

    @Override
    public String execute(Rover rover) {
        rover.setDirection(rover.getDirection().right(use8Directions));
        return NavigationConstants.STATUS_SUCCESS;
    }
}
