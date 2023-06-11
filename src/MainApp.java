import Logic.Logic;
import Graphics.Graphics;
import Logic.Direction;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainApp {
    public static void main (String[] args) {






        SwingUtilities.invokeLater(() -> {

            Logic logic = new Logic(1, new int[25][16]); // tick 1 = 1 refresh per second

            Graphics graphics = new Graphics();

            graphics.setMovementListener(logic);
            logic.setMovementListener(graphics);

            graphics.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed (KeyEvent e) {

                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> {
                            logic.setDirection(Direction.UP);
//                            System.out.println("UP");
                        }
                        case KeyEvent.VK_DOWN -> {
                            logic.setDirection(Direction.DOWN);
//                            System.out.println("DOWN");
                        }
                        case KeyEvent.VK_LEFT -> {
                            logic.setDirection(Direction.LEFT);
//                            System.out.println("LEFT");
                        }
                        case KeyEvent.VK_RIGHT -> {
                            logic.setDirection(Direction.RIGHT);
//                            System.out.println("RIGHT");
                        }
                    }
                }
            });

            Thread logicThread = new Thread(logic);
            logicThread.start();


        });



    }

}