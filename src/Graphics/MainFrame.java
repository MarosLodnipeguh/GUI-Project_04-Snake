package Graphics;

import Enums.Direction;
import Handlers.MovementListener;
import Logic.Scoreboard;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class MainFrame extends JFrame implements /*KeyListener,*/ MovementListener {

    private GamePanel gamePanel;
    private UserPanel userPanel;

    private MovementListener movementListener;

    public MainFrame (int boardWidth, int boardHeight) {

        setPreferredSize(new Dimension(600, 600));
        setTitle("Graphics");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);

        gamePanel = new GamePanel(boardWidth, boardHeight);
        userPanel = new UserPanel();

        getContentPane().add(gamePanel, BorderLayout.NORTH);
        getContentPane().add(userPanel, BorderLayout.SOUTH);

//        addKeyListener(this);

        pack();
        setVisible(true);

    }

    @Override
    public void showGameboard() {
        gamePanel.showGameboard();
        pack();
        repaint();
        revalidate();
    }

    @Override
    public void showScoreboard (List<Scoreboard.ScoreEntry> scores) {
        gamePanel.showScoreboard(scores);
        pack();
        repaint();
        revalidate();
    }

    @Override
    public void fillGameboard (int[][] board) {
        gamePanel.fillGameboard(board);
        requestFocus();
        repaint();
    }




    @Override
    public void setDirection (Direction direction) {
        movementListener.setDirection(direction);
    }

    public void setMovementListener (MovementListener movementListener) {
        this.movementListener = movementListener;
//        System.out.println("MainFrame MovementListener set to: " + movementListener.getClass());
    }

//    public void setEventListener (EventListener eventListener) {
//        this.eventListener = eventListener;
//    }

    //    @Override
//    public void keyPressed (KeyEvent e) {
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_UP -> {
//                System.out.println("UP");
//                movementListener.setDirection(Direction.UP); // -> GameManager
//            }
//            case KeyEvent.VK_DOWN -> {
//                System.out.println("DOWN");
//                movementListener.setDirection(Direction.DOWN);
//            }
//            case KeyEvent.VK_LEFT -> {
//                System.out.println("LEFT");
//                movementListener.setDirection(Direction.LEFT);
//            }
//            case KeyEvent.VK_RIGHT -> {
//                System.out.println("RIGHT");
//                movementListener.setDirection(Direction.RIGHT);
//            }
//        }
//    }
//
//    @Override
//    public void keyTyped (KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyReleased (KeyEvent e) {
//
//    }


    public UserPanel getUserPanel () {
        return userPanel;
    }
}
