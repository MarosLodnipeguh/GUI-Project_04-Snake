package Graphics;

import Handlers.MovementListener;
import Logic.Direction;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame implements MovementListener {

    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private Table table;
    private JPanel scorePanel;
    private MovementListener movementListener;

    public MainFrame () {
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
    }






    @Override
    public void setDirection (Direction direction) {
        movementListener.setDirection(direction);
    }

    public void setMovementListener (MovementListener movementListener) {
        this.movementListener = movementListener;
    }


}
