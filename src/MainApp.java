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

            GameManager manager = new GameManager();

            MainFrame graphics = new MainFrame(boardWidth, boardHeight);





            graphics.setMovementListener(manager);
//            graphics.setEventListener(manager);

//            graphics.setUserPanelListener(manager);
            graphics.getUserPanel().setFromGraphicsListener(manager);

            manager.setFromGraphicsEventListener(graphics.getUserPanel());
            manager.setFromLogicEventListener(graphics.getUserPanel());

            manager.setFromGraphicsMovementListener(graphics);
            manager.setFromLogicMovementListener(graphics);

            graphics.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed (KeyEvent e) {

                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> {
                            System.out.println("UP");
                            manager.setDirection(Direction.UP);
                        }
                        case KeyEvent.VK_DOWN -> {
                            System.out.println("DOWN");
                            manager.setDirection(Direction.DOWN);
                        }
                        case KeyEvent.VK_LEFT -> {
                            System.out.println("LEFT");
                            manager.setDirection(Direction.LEFT);
                        }
                        case KeyEvent.VK_RIGHT -> {
                            System.out.println("RIGHT");
                            manager.setDirection(Direction.RIGHT);
                        }
                    }
                }
            });


//            manager.setGraphicsEventListener(graphics);



//            Thread logicThread = new Thread(logic);
//            logicThread.start();


        });



    }

}