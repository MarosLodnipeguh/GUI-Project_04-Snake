package Logic;

import Enums.Direction;
import Handlers.*;

public class GameManager implements EventListener, MovementListener {

    private Game game;

    private EventListener fromLogicEventListener;
    private EventListener fromGraphicsEventListener;

    private MovementListener fromLogicMovementListener; // (słuchaczem jest MainFrame)
    private MovementListener fromGraphicsMovementListener; // (słuchaczem jest Manager)


    @Override
    public void startGame (NewGameEvent evt) {

        this.game = new Game(this, evt.getTick(), evt.getBoardWidth(), evt.getBoardHeight());
        Thread gameThread = new Thread(game, "GameThread");
        gameThread.start();

//        if (game == null) {
//
//            this.game = new Game(this, evt.getTick(), evt.getBoardWidth(), evt.getBoardHeight());
////            game.setMovementListener(fromGraphicsMovementListener);
////            System.out.println("Game is now a listener of: " + fromGraphicsMovementListener.getClass());
//
//
//            Thread gameThread = new Thread(game, "GameThread");
//            gameThread.start();
//
//        }
//        else {
//            gameOver(null);
//        }
    }

    @Override
    public void updateScore (ConsumptionEvent evt) {
        fromLogicEventListener.updateScore(evt);
    }

    @Override
    public void changeTick (TickEvent evt) {
        game.setTick(evt.getTick());
    }

    @Override
    public void gameOver (ImpactEvent evt) {
        game.setRunning(false);
        game = null;
    }

    @Override
    public void startGameNoButton () {

    }

    @Override
    public void setDirection (Direction direction) {
        if (game == null) {
            fromLogicEventListener.startGameNoButton();
        }
        game.setDirection(direction);
        System.out.println("Direction set to: " + direction);
    }

    @Override
    public void fillGraphics (int[][] board) {
        fromLogicMovementListener.fillGraphics(board);
    }

    public void setFromLogicEventListener (EventListener fromLogicEventListener) {
        this.fromLogicEventListener = fromLogicEventListener;
    }

    public void setFromGraphicsEventListener (EventListener fromGraphicsEventListener) {
        this.fromGraphicsEventListener = fromGraphicsEventListener;
    }

    public void setFromLogicMovementListener(MovementListener fromLogicMovementListener) {
        this.fromLogicMovementListener = fromLogicMovementListener;
    }

    public void setFromGraphicsMovementListener(MovementListener fromGraphicsMovementListener) {
        this.fromGraphicsMovementListener = fromGraphicsMovementListener;
    }

    @Override
    public boolean getGameState () {
        if (game == null) {
            return false;
        }
        else {
            return true;
        }
    }
}
