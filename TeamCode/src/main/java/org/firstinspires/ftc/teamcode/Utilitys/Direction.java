package org.firstinspires.ftc.teamcode.Utilitys;

/**
 * Created by ethan.hampton on 10/18/2017.
 * List of possible directions for future use, classes that support this enum may not support all possible elements
 */

public enum Direction {
    LEFT, RIGHT, MIDDLE, UP, DOWN, FORWARD, BACKWARDS, UNKNOWN, BOTTOM, TOP;

    public static Direction getOpposite(Direction currentlyPointing) {
        switch (currentlyPointing) {
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case MIDDLE:
                return UNKNOWN;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case FORWARD:
                return BACKWARDS;
            case BACKWARDS:
                return FORWARD;
            case UNKNOWN:
               return UNKNOWN;
        }
        return null;
    }
}
