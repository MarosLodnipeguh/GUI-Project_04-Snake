package Graphics;

import Handlers.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Hashtable;

public class UserPanel extends JPanel implements EventListener {

    private JButton startButton;

    private JLabel tickLabel;
    private JSlider tickSlider;

    private int score;
    private int tickValue;

    private EventListener fromGraphicsListener;

    public UserPanel () {
        this.startButton = startButton;
        this.tickSlider = tickSlider;
        this.score = score;
        this.fromGraphicsListener = fromGraphicsListener;
        this.tickValue = 5;

        setPreferredSize(new Dimension(210, 160));
        setBorder(BorderFactory.createTitledBorder("User Panel"));

        // ===================================== TICK SLIDER ===================================== //
        tickLabel = new JLabel("Ticks per second: ");

        tickSlider = new JSlider(1, 15, 5);
        tickSlider.setMajorTickSpacing(10); // Ustawienie większych kroków
        tickSlider.setMinorTickSpacing(1); // Ustawienie mniejszych kroków
        tickSlider.setPaintTicks(true);
        tickSlider.setPaintLabels(true);

        // opisy przedziałów
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(1, new JLabel("1"));
//        labelTable.put(3, new JLabel("3"));
        labelTable.put(5, new JLabel("5"));
        labelTable.put(10, new JLabel("10"));
        labelTable.put(15, new JLabel("15"));
        tickSlider.setLabelTable(labelTable);


        tickSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                tickValue = source.getValue();
                tickLabel.setText("Ticks per second: " + tickValue);

                fromGraphicsListener.changeTick(new TickEvent(this, tickValue));
            }
        });
        // ===================================== NEW GAME BUTTON ===================================== //
        startButton = new JButton("New game");

        startButton.addActionListener(e -> {
            if (fromGraphicsListener.getGameState() == true) {
                gameOver(new ImpactEvent(this, 0, 0));
                String name = GetSnakeName();
                startGame(new NewGameEvent(this, name, tickValue, 25, 16));
            }
            else {
                String name = GetSnakeName();
                startGame(new NewGameEvent(this, name, tickValue, 25, 16));
            }

        });
        // ===================================== SCORE COUNTER ===================================== //




        add(startButton);
        add(tickLabel);
        add(tickSlider);


    }

    private String GetSnakeName () {
        String text = JOptionPane.showInputDialog("Name your Snake: ");
        if (text != null && !text.isEmpty()) {
            return text;
        }
        return null;
    }

    @Override
    public void startGameNoButton () {
        String name = GetSnakeName();
        startGame(new NewGameEvent(this, name, 5, 25, 16));
    }

    @Override
    public boolean getGameState () {
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Score: " + score, 70, 80);
    }

    public void setFromGraphicsListener (EventListener fromGraphicsListener) {
        this.fromGraphicsListener = fromGraphicsListener;
        System.out.println("UserPanel: Logic listener set" + fromGraphicsListener.getClass());
    }

    @Override
    public void startGame (NewGameEvent evt) {
        fromGraphicsListener.startGame(evt);
    }

    @Override
    public void updateScore (ConsumptionEvent evt) {
        score = evt.getScore();
    }

    @Override
    public void changeTick (TickEvent evt) {

    }

    @Override
    public void gameOver (ImpactEvent evt) {
        fromGraphicsListener.gameOver(evt);
    }

}
