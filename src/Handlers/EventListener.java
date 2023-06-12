package Handlers;

public interface EventListener {

    void startGame(StartGameEvent evt);     // Graphics -> Logic (w logic to będzie tworzyło new Thread(logic))

    void updateScore(ConsumptionEvent evt); // Logic -> Graphics

    void changeTick(TickEvent evt);         // Graphics -> Logic

    void gameOver(ImpactEvent evt);         // Logic -> Graphics

    void gameWon();                         // Logic -> Graphics

}
