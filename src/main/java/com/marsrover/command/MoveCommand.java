package com.marsrover.command;

import com.marsrover.rover.Rover;

public class MoveCommand implements Command {
    @Override
    public String execute(Rover rover) {
        return rover.moveForward();
    }
}
