package com.marsrover;

import com.marsrover.grid.Grid;
import com.marsrover.rover.*;
import com.marsrover.multi.*;
import com.marsrover.navigation.NavigationResult;
import com.marsrover.utils.InputValidator;

import java.util.*;

public class Main {
    private static final int MAX_COMMAND_LENGTH = 10;
    private static final int MIN_GRID_SIZE = 2;
    private static final int MIN_ROVERS = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int mode;
        while (true) {
            System.out.print("Choose mode (4 for 4 directions, 8 for 8 directions): ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }
            mode = sc.nextInt();
            sc.nextLine();
            if (mode == 4 || mode == 8) break;
            System.out.println("Invalid input! Please enter only 4 or 8.");
        }
        boolean use8Directions = (mode == 8);

        int size;
        while (true) {
            System.out.print("Enter grid size (must be >= 2): ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }
            size = sc.nextInt();
            sc.nextLine();
            if (size >= MIN_GRID_SIZE) break;
            System.out.println("Grid size too small! Please enter at least 2.");
        }
        Grid grid = new Grid(size, size);

        System.out.println("Enter obstacles x,y per line (empty line to finish):");
        while (true) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) break;

            String[] parts = line.split(",");
            if (parts.length != 2) {
                System.out.println("Invalid obstacle format! Please use x,y (e.g., 1,2).");
                continue;
            }

            try {
                int ox = Integer.parseInt(parts[0].trim());
                int oy = Integer.parseInt(parts[1].trim());
                grid.addObstacle(ox, oy);
            } catch (NumberFormatException e) {
                System.out.println("Invalid numbers! Please enter integers only.");
            }
        }

        int numRovers;
        while (true) {
            System.out.print("Enter number of rovers (must be >= 1): ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }
            numRovers = sc.nextInt();
            sc.nextLine();
            if (numRovers >= MIN_ROVERS) break;
            System.out.println("Number of rovers must be at least 1.");
        }

        RoverManager manager = new RoverManager();
        for (int i = 1; i <= numRovers; i++) {
            String name = "Rover" + i;
            Rover rover = new Rover(new Position(0, 0), Direction.N, grid);
            manager.addRover(name, rover);
        }

        MultiRoverController multiCtrl = new MultiRoverController(manager);

        while (true) {
            System.out.println("Enter commands for each rover (format: Rover1:MMR,Rover2:LM) or Q to quit:");
            String cmdLine = sc.nextLine().trim();
            if (cmdLine.equalsIgnoreCase("Q")) break;

            Map<String, String> commands = new HashMap<>();
            String[] roverCmds = cmdLine.split(",");
            for (String rc : roverCmds) {
                String[] pair = rc.split(":");
                if (pair.length == 2) {
                    String roverName = pair[0].trim();
                    String cmd = pair[1].trim();

                    if (cmd.length() > MAX_COMMAND_LENGTH) {
                        System.out.println("Command too long for " + roverName + " (max " + MAX_COMMAND_LENGTH + " chars).");
                        continue;
                    }
                    if (!InputValidator.isValidCommand(cmd, use8Directions)) {
                        System.out.println("Invalid commands for " + roverName);
                        continue;
                    }
                    commands.put(roverName, cmd);
                } else {
                    System.out.println("Invalid command format! Use RoverName:Commands (e.g., Rover1:MMR)");
                }
            }

            Map<String, NavigationResult> results = multiCtrl.execute(commands, use8Directions);
            results.forEach((name, result) -> System.out.println(name + " => " + result.toJson()));

            manager.getAllRovers().values().forEach(Rover::reset);
        }
    }
}
