package com.marsrover.command;

import com.marsrover.rover.Rover;
import com.marsrover.constants.NavigationConstants;

public record TurnLeftCommand(boolean use8Directions) implements Command {

    @Override
    public String execute(Rover rover) {
        rover.setDirection(rover.getDirection().left(use8Directions));
        return NavigationConstants.STATUS_SUCCESS;
    }
}
