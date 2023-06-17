package Handlers;

public interface EventListener {

    void startGame(NewGameEvent evt);       // UserPanel -> GameManager

    void updateScore(ConsumptionEvent evt); // GameManager -> UserPanel

    void changeTick(TickEvent evt);         // UserPanel -> GameManager

    void gameOver(ImpactEvent evt);         // GameManager -> MainFrame (dodać jakiś game over scree?)

    void startGameNoButton();               // Start game without Start button (by pressing movement key)

    boolean getGameState();
    
    
    // scrapped:
    // EventListener newGame(EventListener graphics);   // Graphics -> Logic
    // void gameWon();                                  // Logic -> Graphics

}
