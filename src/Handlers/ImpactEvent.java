package Handlers;

import java.util.EventObject;

public class ImpactEvent extends EventObject {

    private int x;
    private int y;

    public ImpactEvent (Object source, int x, int y) {
        super(source);
        this.x = x;
        this.y = y;
        System.out.println("Impact at: " + x + ", " + y);
    }


}
