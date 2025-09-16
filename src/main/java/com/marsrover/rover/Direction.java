package com.marsrover.rover;

public enum Direction {
    N(0, 1),
    NE(1, 1),
    E(1, 0),
    SE(1, -1),
    S(0, -1),
    SW(-1, -1),
    W(-1, 0),
    NW(-1, 1);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public Direction left(boolean use8Directions) {
        if (use8Directions) {
            int index = (this.ordinal() + 7) % 8;
            return Direction.values()[index];
        }
        return switch (this) {
            case N -> W;
            case W -> S;
            case S -> E;
            case E -> N;
            default -> this;
        };
    }

    public Direction right(boolean use8Directions) {
        if (use8Directions) {
            int index = (this.ordinal() + 1) % 8;
            return Direction.values()[index];
        }
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
            default -> this;
        };
    }
}
