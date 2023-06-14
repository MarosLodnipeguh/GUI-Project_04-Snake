package Handlers;

import java.util.EventObject;

public class TickEvent extends EventObject {

    // slider w UI na change tick

    private int tick;

    public TickEvent (Object source, int tick) {
        super(source);
        this.tick = tick;
        System.out.println("Tick changed to: " + tick);
    }

}
