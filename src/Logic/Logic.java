package Logic;

import Handlers.MovementListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;


public class Logic extends Thread implements MovementListener {

    private int tick;
    private int[][] board;
    private volatile boolean running;

    private Direction direction;
    private int bodyParts;


    private MovementListener movementListener;

    public Logic (int tick, int[][] board) {
        this.tick = tick;
        this.board = board;

        running = true;
        direction = Direction.DOWN;
        bodyParts = 0;

        generateHead();
        generateFood();

//        bodyParts++;
//        bodyParts++;
//        bodyParts++;


//        addKeyListener(this);
//        Graphics.addKeyListener(this);
    }

    // 1 - snake head
    // 2 - snake body
    // 3 - food

    public void run() {
        System.out.println("Logic thread started");


        while (running) {

            checkEat();

            fillGraphics(board);

            try {
                move();
                checkCollision();
            } catch (ArrayIndexOutOfBoundsException e) {
                running = false;
                System.out.println("GAME OVER");
            }


            fillGraphics(board);



            try {
//                Thread.sleep(tick);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    private final int x[] = new int[400];
    private final int y[] = new int[400];


    private void move() {

        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[(i - 1)]; // ostatnia wartość pozycji ciała jest teraz przedostatnią
            y[i] = y[(i - 1)];
        }

        switch (direction) {

            case UP -> {
                y[0]--;
                checkCollision();
            }

            case DOWN -> {
                y[0]++;
                checkCollision();
            }

            case LEFT -> {
                x[0]--;
                checkCollision();
            }

            case RIGHT -> {
                x[0]++;
                checkCollision();
            }
        }

        // clear the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 3) {
                    board[i][j] = 0;
                }
            }
        }

        board[y[0]][x[0]] = 1; // dodanie głowy
        for (int i = 1; i <= bodyParts; i++) {
            board[y[i]][x[i]] = 2; // dodanie ciała
        }

//        System.out.println("head at: " + x[0] + " " + y[0]);
    }

    private void checkEat() {
        if (x[0] == foodY && y[0] == foodX) {
            System.out.println("EAT");
            bodyParts++;
            System.out.println("Points: " + bodyParts);
            generateFood();
        }
    }

    private void checkCollision() {

        for (int i = bodyParts; i > 0; i--) {
            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                System.out.println("COLLISION");
                running = false;
            }
        }

//        if (y[0] < 0) {
//            running = false;
//        }
//
//        if (y[0] >= board.length-1) {
//            System.out.println("BORDER");
//            running = false;
//        }
//
//        if (x[0] < 0) {
//            running = false;
//        }
//
//        if (x[0] >= board[0].length-1) {
//            running = false;
//        }


    }

    @Override
    public void setDirection (Direction direction) {
        if (this.direction == Direction.UP && direction == Direction.DOWN) {
            return;
        }
        if (this.direction == Direction.DOWN && direction == Direction.UP) {
            return;
        }
        if (this.direction == Direction.LEFT && direction == Direction.RIGHT) {
            return;
        }
        if (this.direction == Direction.RIGHT && direction == Direction.LEFT) {
            return;
        }
        this.direction = direction;
    }

    @Override
    public void fillGraphics (int[][] board) {
        movementListener.fillGraphics(board);
    }


    public void generateHead() {

        int randomRow = (int) (Math.random() * board.length);
        int randomCol = (int) (Math.random() * board[0].length);

        x[0] = 8;
        y[0] = 8;

    }

    private int foodX;
    private int foodY;
    public void generateFood() {

        int randomX = (int) (Math.random() * board.length);
        int randomY = (int) (Math.random() * board[0].length);

        if (board[randomX][randomY] == 0) {
            board[randomX][randomY] = 3;
            foodX = randomX;
            foodY = randomY;
            System.out.println("Food generated at: " + randomX + " " + randomY);
        } else {
            generateFood();
        }

    }


    public void setMovementListener (MovementListener movementListener) {
        this.movementListener = movementListener;
    }


}
