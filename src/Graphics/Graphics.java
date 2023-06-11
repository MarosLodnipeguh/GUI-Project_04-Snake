package Graphics;

import Handlers.MovementListener;
import Logic.Direction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Graphics extends JFrame implements MovementListener {

    private JScrollPane scrollPane;
    private Table table;
    private MovementListener movementListener;

    public Graphics() {
        setTitle("Graphics");
//        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setFocusable(true);



        table = new Table(25, 16);
        scrollPane = new JScrollPane(table);
        this.getContentPane().add(scrollPane);

        table.setValueAt(Color.GREEN, 0, 3);


//        JPanel panel = new JPanel();
//        panel.setLayout(null);
//
//
//        DefaultTableModel model = new DefaultTableModel(10, 10);
//
//        JTable table = new Table2(model);
//        JScrollPane scrollPane = new JScrollPane(table);
//        this.getContentPane().add(scrollPane);

//        scrollPane.setBounds(10, 10, cellSize * 10, cellSize * 10); // Ustawienie wymiarów JScrollPane na podstawie rozmiaru komórek
//        panel.add(scrollPane);

//        add(panel);
//        setSize(cellSize * 10 + 40, cellSize * 10 + 60);

        pack();
        setVisible(true);

    }

    public void refresh() {
        repaint();
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
        refresh();
//        System.out.println("Graphics refreshed");
    }






    @Override
    public void setDirection (Direction direction) {
        movementListener.setDirection(direction);
    }

    public void setMovementListener (MovementListener movementListener) {
        this.movementListener = movementListener;
    }


}
