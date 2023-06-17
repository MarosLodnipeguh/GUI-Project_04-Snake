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

        this.game = new Game(this, evt.getPlayerName(), evt.getTick(), evt.getBoardWidth(), evt.getBoardHeight());
        Thread gameThread = new Thread(game, "GameThread");
        gameThread.start();

    }

    @Override
    public void updateScore (ConsumptionEvent evt) {
        fromLogicEventListener.updateScore(evt);
    }

    @Override
    public void changeTick (TickEvent evt) {
        if (game != null) {
            game.setTick(evt.getTick());
        }
    }

    @Override
    public void gameOver (ImpactEvent evt) {
        System.out.println("Game Over wywołanie");
        game.setRunning(false);

        writeScoreToFile(evt.getPlayerName(), evt.getFinalScore());

        game = null;
        System.out.println("Game Nulled");
    }

    @Override
    public void forceGameOver () {
        game.forceGameOver(); // -> gameOver()
    }



    public void writeScoreToFile (String playerName, int score) {

        Scoreboard.addScore(playerName, score);
        Scoreboard.displayScores();

    }



    @Override
    public void setDirection (Direction direction) {
        if (game == null) {
            fromLogicEventListener.startGameNoButton();
        }
        game.setDirection(direction);
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


    @Override
    public void startGameNoButton () {

    }


}
