# 🚀 Mars Rover Navigation

## 📌 Project Description
This project simulates **Mars rovers** navigating on a 2D grid with obstacles.  
Rovers can receive command sequences to **move** and **turn**.  
The system supports:
- **Multiple rovers** in one session
- **Two navigation modes**:
    - 4 directions: N, E, S, W
    - 8 directions: N, NE, E, SE, S, SW, W, NW (mapped to `Q,E,Z,C`)
- **Collision detection**:
    - Prevents moving out of the grid
    - Prevents moving into obstacles
- **Reset behavior**: After each command sequence, all rovers reset to `(0,0,N)`

Design patterns:
- **Factory Pattern** → for command creation
- **Strategy Pattern** → for movement strategies (4 or 8 directions)

The code is modular, OOP-based, and easily extensible.

---

## ⚙️ Installation Instructions
### Requirements
- Java JDK **17+**
- Maven **3.8+**

### Setup
```bash
git clone https://github.com/Natthawut00/mars_rover_navigations.git
cd mars_rover_navigation
mvn clean install
```

---

## ▶️ Usage Instructions

### Run the application
```bash
mvn exec:java -Dexec.mainClass="com.marsrover.Main"
```

### Input format
1. **Choose mode**
    - `4` → 4 directions
    - `8` → 8 directions
    - ❌ Invalid input → will re-prompt until correct

2. **Grid size**
    - Must be an integer ≥ 2
    - ❌ If smaller than 2 → will re-prompt

3. **Obstacles**
    - Enter as `x,y` per line, empty line to finish
    - Both `x` and `y` must be integers
    - Example:
      ```
      1,2
      3,3
      ```
    - ❌ Invalid format (e.g. `1;2`) → `"Invalid obstacle format!"`

4. **Number of rovers**
    - Must be an integer ≥ 1
    - ❌ If less → will re-prompt

5. **Commands**
    - Format: `RoverName:Commands` separated by commas
    - Available commands:
        - `L` → Turn left
        - `R` → Turn right
        - `M` → Move forward
        - `Q,E,Z,C` → Diagonal moves (**only in 8-direction mode**)
    - Length must not exceed 10 characters
    - Example:
      ```
      Rover1:MMR,Rover2:LMC
      ```
    - ❌ Too long → `"Invalid command (too long)"`
    - ❌ Wrong format → `"Invalid command format!"`

6. **Quit**
    - Enter `Q`

---

## ⚠️ Error Handling
- Invalid mode → re-prompt
- Grid size < 2 → re-prompt
- Invalid obstacle format → `"Invalid obstacle format!"`
- Command too long → `"Invalid command (too long)"`
- Invalid command character → `"Invalid command: X"`

---

## 💻 Example Session
```
Choose mode (4 for 4 directions, 8 for 8 directions): 5
Invalid input! Please enter only 4 or 8.
Choose mode (4 for 4 directions, 8 for 8 directions): 8

Enter grid size (must be >= 2): 1
Grid size too small! Please enter at least 2.
Enter grid size (must be >= 2): 5

Enter obstacles x,y per line (empty line to finish):
1;2
Invalid obstacle format! Please use x,y (e.g., 1,2).
1,2
<empty line to finish>

Enter number of rovers (must be >= 1): 0
Number of rovers must be at least 1.
Enter number of rovers (must be >= 1): 2

Enter commands for each rover (format: Rover1:MMR,Rover2:LM) or Q to quit:
Rover1:M
Rover1 => {"x":0,"y":1,"direction":"N","status":"Success"}

Enter commands for each rover (format: Rover1:MMR,Rover2:LM) or Q to quit:
Rover2:C
Rover2 => {"x":1,"y":-1,"direction":"N","status":"Out of bounds"}
```

---

## 🧪 Testing Instructions
Run unit tests:
```bash
mvn test
```

### Test Coverage
- ✅ Normal movement without obstacles
- ✅ Boundary collisions (out of grid)
- ✅ Obstacle collisions
- ✅ Invalid commands
- ✅ Multiple rovers
- ✅ Diagonal commands (Q,E,Z,C)
- ✅ Edge cases (1x1 grid, long command strings)

### Example Test
```java
@Test
void testOutOfBounds() {
    Grid grid = new Grid(5,5);
    Rover rover = new Rover(new Position(0,0), Direction.N, grid);
    RoverController ctrl = new RoverController(rover);

    NavigationResult result = ctrl.executeCommands("MMMMMM", false);
    assertEquals(NavigationConstants.STATUS_OUT, result.status());
}
```

---

## 🧪 Test Case Summary

| Test Case                          | Input                         | Expected Output                          |
|------------------------------------|-------------------------------|------------------------------------------|
| Normal move forward                | `M`                           | `{"x":0,"y":1,"direction":"N","status":"Success"}` |
| Turn right                         | `R`                           | `direction = "E", status = "Success"`    |
| Turn left                          | `L`                           | `direction = "W", status = "Success"`    |
| Collision with obstacle (N)        | Grid: 5x5, Obstacle at (0,1), Commands: `M` | `status = "Obstacle encountered"` and rover stays `(0,0)` |
| Collision with obstacle (E)        | Grid: 5x5, Obstacle at (1,0), Commands: `R` + `M` | `status = "Obstacle encountered"` |
| Out of bounds (North)              | Grid: 3x3, Start (2,2,N), Commands: `M` | `status = "Out of bounds"` |
| Out of bounds (West, negative pos) | Grid: 3x3, Start (0,0,W), Commands: `M` | `status = "Out of bounds"` |
| Minimal grid 1x1                   | Grid: 1x1, Start (0,0,N), Commands: `M` | `status = "Out of bounds"` |
| Large grid                         | Grid: 1000x1000, Start (500,500,N), Commands: `M` | `status = "Success"` |
| Long command string                | Grid: 5x5, Commands: `"M"*20` | `status = "Invalid command (too long)"` |
| Invalid command                    | Grid: 5x5, Commands: `MXM`    | `status = "Invalid command: X"` |
| Multi-rover (2 rovers)             | `Rover1:MMR, Rover2:LM`       | Each rover outputs JSON result separately |
| Diagonal move (8-mode, NE)         | Mode=8, Commands: `Q`         | Rover moves `(1,1)` with `status = "Success"` |
| Diagonal out of bounds             | Mode=8, Grid=5x5, Start (0,0), Commands: `Z` | `status = "Out of bounds"` |

---

## 📌 Additional Notes
- Known limitations:
    - Reset is always `(0,0,N)` after each sequence
- Future enhancements:
    - Save state between command sequences
    - Integration with real-time GUI / Web interface
    - CI/CD with GitHub Actions

---
