package com.marsrover.multi;

import com.marsrover.rover.Rover;
import java.util.HashMap;
import java.util.Map;

public class RoverManager {
    private final Map<String, Rover> rovers = new HashMap<>();

    public void addRover(String name, Rover rover) {
        rovers.put(name, rover);
    }

    public Rover getRover(String name) {
        return rovers.get(name);
    }

    public Map<String, Rover> getAllRovers() {
        return rovers;
    }

}
