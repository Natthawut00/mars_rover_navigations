package com.marsrover.multi;

import com.marsrover.navigation.RoverController;
import com.marsrover.navigation.NavigationResult;
import com.marsrover.rover.Rover;

import java.util.HashMap;
import java.util.Map;

public record MultiRoverController(RoverManager manager) {

    public Map<String, NavigationResult> execute(Map<String, String> commands, boolean use8Directions) {
        Map<String, NavigationResult> results = new HashMap<>();

        for (var entry : commands.entrySet()) {
            String name = entry.getKey();
            String cmdStr = entry.getValue();
            Rover rover = manager.getRover(name);

            if (rover != null) {
                RoverController ctrl = new RoverController(rover);
                NavigationResult result = ctrl.executeCommands(cmdStr, use8Directions);
                results.put(name, result);
            } else {
                results.put(name, new NavigationResult(0, 0, "Unknown", "Rover not found"));
            }
        }

        return results;
    }
}
