package com.marsrover.utils;

public class InputValidator {


    public static boolean isValidCommand(String cmd, boolean use8Directions) {
        if (cmd == null || cmd.isEmpty()) {
            return false;
        }

        if (use8Directions) {
            return cmd.matches("[LRMQEZC]+");
        } else {
            return cmd.matches("[LRM]+");
        }
    }
}
