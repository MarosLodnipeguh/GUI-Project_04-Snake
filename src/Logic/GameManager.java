package Logic;

import Enums.Direction;
import Handlers.*;

public class GameManager implements EventListener, MovementListener {

    private Game game;

    private EventListener logicEventListener;
    private EventListener graphicsEventListener;

    private MovementListener logicMovementListener;
    private MovementListener graphicsMovementListener;


    @Override
    public void startGame (StartGameEvent evt) {

        if (game == null) {

            game = new Game(evt.getTick(), evt.getBoardWidth(), evt.getBoardHeight());
            game.setMovementListener(graphicsMovementListener);


            Thread gameThread = new Thread(game);
            gameThread.start();

        }
        else {
            gameOver(null);
        }
    }

    @Override
    public EventListener newGame (EventListener graphics) {
        return null;
    }

    @Override
    public void updateScore (ConsumptionEvent evt) {

    }

    @Override
    public void changeTick (TickEvent evt) {

    }

    @Override
    public void gameOver (ImpactEvent evt) {
        game.setRunning(false);
        game = null;
    }

    @Override
    public void gameWon () {

    }

    @Override
    public void setDirection (Direction direction) {
        game.setDirection(direction);
    }

    @Override
    public void fillGraphics (int[][] board) {

    }

    public void setLogicEventListener (EventListener logicEventListener) {
        this.logicEventListener = logicEventListener;
    }

    public void setGraphicsEventListener (EventListener graphicsEventListener) {
        this.graphicsEventListener = graphicsEventListener;
    }

    public void setLogicMovementListener (MovementListener logicMovementListener) {
        this.logicMovementListener = logicMovementListener;
    }

    public void setGraphicsMovementListener (MovementListener graphicsMovementListener) {
        this.graphicsMovementListener = graphicsMovementListener;
    }
}
