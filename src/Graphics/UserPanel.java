package Graphics;

import Handlers.*;
import com.sun.tools.javac.Main;

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

    private EventListener logicListener;

    public UserPanel () {
        this.startButton = startButton;
        this.tickSlider = tickSlider;
        this.score = score;
        this.logicListener = logicListener;

        setPreferredSize(new Dimension(210, 160));
        setBorder(BorderFactory.createTitledBorder("User Panel"));

        // ===================================== SLIDER ===================================== //
        tickLabel = new JLabel("Ticks per second: ");

        //Slider w ms będzie 100ms = 0.1s, 10000ms = 10s

        tickSlider = new JSlider(1, 30, 5);
        tickSlider.setMajorTickSpacing(10); // Ustawienie większych kroków
        tickSlider.setMinorTickSpacing(1); // Ustawienie mniejszych kroków
        tickSlider.setPaintTicks(true);
        tickSlider.setPaintLabels(true);

        // opisy przedziałów
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(1, new JLabel("1"));
        labelTable.put(3, new JLabel("3"));
        labelTable.put(5, new JLabel("5"));
        labelTable.put(10, new JLabel("10"));
        labelTable.put(30, new JLabel("30"));
        tickSlider.setLabelTable(labelTable);


        tickSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                tickLabel.setText("Ticks per second: " + value);

                logicListener.changeTick(new TickEvent(this, value));
            }
        });
        // ===================================== STOP BUTTON ===================================== //
        startButton = new JButton("Start game");

        startButton.addActionListener(e -> {
            startGame(new StartGameEvent(this, "dupadupa", 5, 25, 16));
        });

        add(startButton);
        add(tickLabel);
        add(tickSlider);


    }

    public void setScore (int score) {
        this.score = score;
    }

    public void setLogicListener (EventListener logicListener) {
        this.logicListener = logicListener;
        System.out.println("UserPanel: Logic listener set" + logicListener.getClass());
    }

    @Override
    public void startGame (StartGameEvent evt) {
        logicListener.startGame(evt);
    }

    @Override
    public EventListener newGame (EventListener graphics) {
        return null;
    }

    @Override
    public void updateScore (ConsumptionEvent evt) {

    }

    @Override
    public void changeTick (TickEvent evt) {

    }

    @Override
    public void gameOver (ImpactEvent evt) {

    }

    @Override
    public void gameWon () {

    }
}
