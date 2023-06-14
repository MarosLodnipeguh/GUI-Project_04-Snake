package Graphics;

import Handlers.EventListener;
import Handlers.MovementListener;
import Enums.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MainFrame extends JFrame implements KeyListener, MovementListener {

    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private Table table;
    private UserPanel userPanel;

    private MovementListener movementListener;
    private EventListener eventListener;

    public MainFrame (int boardWidth, int boardHeight) {
        setTitle("Graphics");
//        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);

        table = new Table(boardHeight, boardWidth);
        scrollPane = new JScrollPane(table);
        this.getContentPane().add(scrollPane, BorderLayout.NORTH);

        userPanel = new UserPanel();

        this.getContentPane().add(userPanel, BorderLayout.SOUTH);

        addKeyListener(this);

        userPanel.setFocusable(false);
        scrollPane.setFocusable(false);
        table.setFocusable(false);

        if (scrollPane.isFocusOwner()) System.out.println("scrollPane is focus owner");
        System.out.println("scrollPane focusable: " + scrollPane.isFocusable());

        pack();
        setVisible(true);

    }

    @Override
    public void fillGraphics (int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                switch (board[i][j]) {
                    case 0 -> table.setValueAt(Color.WHITE, i, j); //empty
                    case 1 -> table.setValueAt(Color.GREEN, i, j); //head
                    case 2 -> table.setValueAt(Color.GRAY, i, j); //body
                    case 3 -> table.setValueAt(Color.RED, i, j); //fruit
                }

            }
        }
        repaint();
    }

    @Override
    public void setDirection (Direction direction) {
        movementListener.setDirection(direction);
    }

    public void setMovementListener (MovementListener movementListener) {
        this.movementListener = movementListener;
        System.out.println("MainFrame MovementListener set to: " + movementListener.getClass());
    }

    public void setEventListener (EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void setUserPanelListener (EventListener eventListener) {
        this.userPanel.setLogicListener(eventListener);
    }

    @Override
    public void keyPressed (KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> {
                System.out.println("UP");
                movementListener.setDirection(Direction.UP); // -> GameManager
            }
            case KeyEvent.VK_DOWN -> {
                System.out.println("DOWN");
                movementListener.setDirection(Direction.DOWN);
            }
            case KeyEvent.VK_LEFT -> {
                System.out.println("LEFT");
                movementListener.setDirection(Direction.LEFT);
            }
            case KeyEvent.VK_RIGHT -> {
                System.out.println("RIGHT");
                movementListener.setDirection(Direction.RIGHT);
            }
        }
    }

    @Override
    public void keyTyped (KeyEvent e) {

    }

    @Override
    public void keyReleased (KeyEvent e) {

    }


}
