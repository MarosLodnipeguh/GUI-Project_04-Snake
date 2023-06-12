package Handlers;

public class TickEvent {

    // slider w UI na change tick

    private int tick;

    public TickEvent (int tick) {
        this.tick = tick;
        System.out.println("Tick changed to: " + tick);
    }

}
