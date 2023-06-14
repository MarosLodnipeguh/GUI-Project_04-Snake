package Handlers;

public interface EventListener {

    void startGame(StartGameEvent evt);     // Graphics -> GameManager
    EventListener newGame(EventListener graphics);                // Graphics -> Logic

    void updateScore(ConsumptionEvent evt); // Logic -> Graphics

    void changeTick(TickEvent evt);         // Graphics -> Logic

    void gameOver(ImpactEvent evt);         // Logic -> Graphics

    void gameWon();                         // Logic -> Graphics

}
