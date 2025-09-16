package com.marsrover.command;

import com.marsrover.rover.Direction;

public class CommandFactory {

    public static Command createCommand(char c, boolean use8Directions) {
        return switch (Character.toUpperCase(c)) {
            case 'L' -> new TurnLeftCommand(use8Directions);
            case 'R' -> new TurnRightCommand(use8Directions);
            case 'M' -> new MoveCommand();
            case 'Q' -> new DiagonalMoveCommand(Direction.NE);
            case 'E' -> new DiagonalMoveCommand(Direction.SE);
            case 'Z' -> new DiagonalMoveCommand(Direction.SW);
            case 'C' -> new DiagonalMoveCommand(Direction.NW);
            default -> throw new IllegalArgumentException("Unknown command: " + c);
        };
    }
}
