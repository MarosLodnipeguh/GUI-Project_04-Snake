import Logic.Game;
import Graphics.MainFrame;
import Logic.GameManager;

import javax.swing.*;

public class MainApp {

    private static int tick = 5; //tps (refresh per second)
    private static int boardWidth = 25;
    private static int boardHeight = 16;

    public static void main (String[] args) {






        SwingUtilities.invokeLater(() -> {


//            Game game = new Game(tick, boardWidth, boardHeight);

            GameManager manager = new GameManager();

            MainFrame graphics = new MainFrame(boardWidth, boardHeight);

            graphics.setMovementListener(manager);
            graphics.setEventListener(manager);

            graphics.setUserPanelListener(manager);

            manager.setGraphicsMovementListener(graphics);
            manager.setLogicMovementListener(graphics);
//            manager.setGraphicsEventListener(graphics);



//            Thread logicThread = new Thread(logic);
//            logicThread.start();


        });



    }

}