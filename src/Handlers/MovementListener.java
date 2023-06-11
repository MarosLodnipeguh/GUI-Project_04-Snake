package Handlers;

import Logic.Direction;

public interface MovementListener {

    void setDirection(Direction direction);

    void fillGraphics (int[][] board);
}
