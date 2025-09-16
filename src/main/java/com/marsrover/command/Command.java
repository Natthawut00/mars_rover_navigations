package com.marsrover.command;

import com.marsrover.rover.Rover;

public interface Command {
    String execute(Rover rover);
}
