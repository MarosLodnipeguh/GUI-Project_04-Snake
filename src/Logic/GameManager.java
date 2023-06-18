package Logic;

import Enums.Direction;
import Handlers.*;

import java.util.List;

public class GameManager implements EventListener, MovementListener {

    private Game game;

    private EventListener fromLogicEventListener; // (słuchaczem jest UserPanel)
    private MovementListener fromLogicMovementListener; // (słuchaczem jest MainFrame)

// USELESS:
    private EventListener fromGraphicsEventListener;
    private MovementListener fromGraphicsMovementListener; // (słuchaczem jest Manager)


    @Override
    public void startGame (NewGameEvent evt) {
        showGameboard();

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

    public void writeScoreToFile (String playerName, int score) {

        Scoreboard.addScore(playerName, score);
//        Scoreboard.displayScores();
        showScoreboard(Scoreboard.displayScores());

    }

    @Override
    public void showScoreboard (List<Scoreboard.ScoreEntry> scores) {
        fromLogicMovementListener.showScoreboard(scores);
    }

    @Override
    public void showGameboard () {
        fromLogicMovementListener.showGameboard();
    }

    @Override
    public void forceGameOver () {
        game.forceGameOver(); // -> gameOver()
    }







    @Override
    public void setDirection (Direction direction) {
        if (game == null) {
            fromLogicEventListener.startGameNoButton();
        }
        game.setDirection(direction);
    }

    @Override
    public void fillGameboard (int[][] board) {
        fromLogicMovementListener.fillGameboard(board);
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
