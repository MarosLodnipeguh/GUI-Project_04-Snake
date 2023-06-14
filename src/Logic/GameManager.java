package Logic;

import Enums.Direction;
import Handlers.*;

public class GameManager implements EventListener, MovementListener {

    private Game game;

    private EventListener logicEventListener;
    private EventListener graphicsEventListener;

    private MovementListener fromLogicMovementListener;
    private MovementListener fromGraphicsMovementListener;


    @Override
    public void startGame (StartGameEvent evt) {

        if (game == null) {

            game = new Game(evt.getTick(), evt.getBoardWidth(), evt.getBoardHeight());
            game.setMovementListener(fromGraphicsMovementListener);
            System.out.println("Game is now a listener of: " + fromGraphicsMovementListener.getClass());


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
        System.out.println("Direction set to: " + direction);
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

    public void setFromLogicMovementListener(MovementListener fromLogicMovementListener) {
        this.fromLogicMovementListener = fromLogicMovementListener;
    }

    public void setFromGraphicsMovementListener(MovementListener fromGraphicsMovementListener) {
        this.fromGraphicsMovementListener = fromGraphicsMovementListener;
    }
}
