import Enums.Direction;
import Graphics.MainFrame;
import Logic.GameManager;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainApp {

    private static int tick = 5; //tps (refresh per second)
    private static int boardWidth = 25;
    private static int boardHeight = 16;

    public static void main (String[] args) {






        SwingUtilities.invokeLater(() -> {


//            Game game = new Game(tick, boardWidth, boardHeight);

            GameManager manager = new GameManager();

            MainFrame graphics = new MainFrame(boardWidth, boardHeight);

            graphics.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed (KeyEvent e) {

                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> {
                            manager.setDirection(Direction.UP);
//                            System.out.println("UP");
                        }
                        case KeyEvent.VK_DOWN -> {
                            manager.setDirection(Direction.DOWN);
//                            System.out.println("DOWN");
                        }
                        case KeyEvent.VK_LEFT -> {
                            manager.setDirection(Direction.LEFT);
//                            System.out.println("LEFT");
                        }
                        case KeyEvent.VK_RIGHT -> {
                            manager.setDirection(Direction.RIGHT);
//                            System.out.println("RIGHT");
                        }
                    }
                }
            });



            graphics.setMovementListener(manager);
            graphics.setEventListener(manager);

            graphics.setUserPanelListener(manager);

            manager.setFromGraphicsMovementListener(graphics);
            manager.setFromLogicMovementListener(graphics);
//            manager.setGraphicsEventListener(graphics);



//            Thread logicThread = new Thread(logic);
//            logicThread.start();

            // co sie zmieniło? i tak thread startuje dopiero po utworzeniu wsystkich obiektow i przypisania listenerow
            // wczesniej tylko obiekt klasy Game był tworzony jako pierwszy


        });



    }

}