package Handlers;

import Enums.Direction;

public interface MovementListener {

    void setDirection(Direction direction); // Graphics -> Logic

    void fillGraphics (int[][] board);      // Logic -> Graphics
}
