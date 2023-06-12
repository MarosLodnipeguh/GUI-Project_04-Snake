package Handlers;

public class ImpactEvent {

    private int x;
    private int y;

    public ImpactEvent (int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("Impact at: " + x + ", " + y);
    }


}
