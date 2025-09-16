package com.marsrover.navigation;

import com.marsrover.command.Command;
import com.marsrover.command.CommandFactory;
import com.marsrover.constants.NavigationConstants;
import com.marsrover.rover.Rover;

public class RoverController {

    private static final int MAX_COMMAND_LENGTH = 10;

    private final Rover rover;

    public RoverController(Rover rover) {
        this.rover = rover;
    }

    public NavigationResult executeCommands(String commands, boolean use8Directions) {
        String status = NavigationConstants.STATUS_SUCCESS;

        if (commands.length() > MAX_COMMAND_LENGTH) {
            status = NavigationConstants.STATUS_INVALID + " (too long)";
            return new NavigationResult(
                    rover.getPosition().getX(),
                    rover.getPosition().getY(),
                    rover.getDirection().name(),
                    status
            );
        }

        for (char c : commands.toCharArray()) {
            try {
                Command cmd = CommandFactory.createCommand(c, use8Directions);
                status = cmd.execute(rover);

                if (!status.equals(NavigationConstants.STATUS_SUCCESS)) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                status = NavigationConstants.STATUS_INVALID + c;
                break;
            }
        }

        return new NavigationResult(
                rover.getPosition().getX(),
                rover.getPosition().getY(),
                rover.getDirection().name(),
                status
        );
    }
}
