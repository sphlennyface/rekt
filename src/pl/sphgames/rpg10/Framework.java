package pl.sphgames.rpg10;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;



public class Framework extends Canvas {
    
	
	/*
	 * WITAJ W MOIM KODZIE
	 * TODO LIST:
	 * 
	 * PRZEPISAÆ PLAYERA BO JEST SPIERDOLONY [ONGOING]
	 * - RUCH, ZEBY £ATWO WCHODZILO SIE POMIEDZY KRATKI
	 * - STRZELANIE TAM GDZIE JEST MYSZKA, MYSZK¥
	 * - ANIMACJE
	 * - SORC/WARRIOR/£UCZNIK
	 * - SKILLE
	 * - INVENTORY
	 * - MANA/STAMINA
	 * 
	 * PRZYJRZEC SIE ACTION, OBJECTOM I EVENTOM BO PRAWDOPODOBNIE ROWNIEZ SA ZJEBANE [TODO]
	 * NAWET NA PEWNO ;/
	 * 
	 * ULEPSZY AKCJE I EVENTY [TODO]
	 * 
	 * WYMYSLEC CIEKAW¥ FABU£E, ZAPROJEKTOWAC LEVELE [ONGOING]
	 * 
	 * GRAFIKE TEZ PROPABLY MUSIMY ZROBIC SAMI BO NIKT SIE NIE ZNA [TODO]
	 * 
	 * STWORZYC KLASY SOUND I MUSIC [TODO]
	 * 
	 * MAINMENU [TODO]
	 * 
	 * WPASC NA POMYSL JAK ZAPISYWAC CURRENT PROGRESS GRACZA [TODO]
	 * 
	 * CALE AI [TODO]
	 * TU MAM DUZO POMYSLOW, TRZEBA OBGADAÆ
	 * 
	 * DOWIEDZIEÆ SIÊ DO CZEGO S£U¯¥ INTERFEJSY I KLASY ABSTRAKCYJNE I MO¯E JE ZASTOSOWAÆ (xD) [TODO]
	 * 
	 * PRAWDOPODOBNIE JESZCZE CALE MNOSTWO INNYCH RZECZY, ALE POKI CO NAPISALEM TYLKO PLAN NA KOLEJNE DNI
	 * 
	 * 
	 * JESTEM SPIERDOLONY I CZASEM FORMATUJE RZECZY W TEN SPOSÓB
	 * EventHandler eventHandler_;
	 * I POTEM JAK TWORZE FUNKCJE TO ROBIE TAK
	 * public static void passEventHandler(EventHandler eventHandler) {
	 * eventHandler_ = eventHandler;
	 * }
	 * A CZASEM KURWA NA ODWRÓT
	 * EventHandler eventHandler;
	 * NIE PYTAJ OD CZEGO TO ZALE¯Y
	 * 
	 * 
	 * NIE OCENIAJ.
	 * 
	 * ALSO JESTEM DUMNY Z TWORZENIA LEVELI I KLASY LEVELENCODER. PEWNIE TE¯ JEST SPIERDOLONA ALE PRZYNAJMNIEJ JESTEM DUMNY 8-)
	 */
    public static int frameWidth;
    public static int frameHeight;
    public static final long secInNanosec = 1000000000L;
    public static final long milisecInNanosec = 1000000L;
    private final int GAME_FPS = 60;
    private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;
    public static enum GameState{STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU, OPTIONS, PLAYING, GAMEOVER, DESTROYED}
    public static GameState gameState;
    public static long gameTime;
    private static long lastTime; 
    private static MainMenu mainMenu;
    private static Game game;
    
    
    public Framework ()
    {
    	super();
    	    

        this.setBackground(Color.white);
        gameState = GameState.VISUALIZING;
        
       
        Thread gameThread = new Thread() {
            @Override
            public void run(){
                GameLoop();
            }
        };
        gameThread.start();
    }
    
    
   
    private static void Initialize()
    {
    	
    	createMainMenu();
    }
    
    
    private void LoadContent()
    {
    
    }
    
    
  
    private void GameLoop()
    {
        long visualizingTime = 0, lastVisualizingTime = System.nanoTime();
        
        long beginTime, timeTaken, timeLeft;
        
        while(true)
        {
            beginTime = System.nanoTime();
            
            switch (gameState)
            {
                case PLAYING:
                    gameTime += System.nanoTime() - lastTime;
                    
                    game.UpdateGame(gameTime, mousePosition());
                    
                    lastTime = System.nanoTime();
                break;
                case GAMEOVER:
                    //...
                break;
                case MAIN_MENU:
                    mainMenu.update();
                break;
                case OPTIONS:

                break;
                case GAME_CONTENT_LOADING:

                break;
                case STARTING:
                    Initialize();
                    LoadContent();

                    
                break;
                case VISUALIZING:
                  if(this.getWidth() > 1 && visualizingTime > secInNanosec)
                    {
                        frameWidth = this.getWidth();
                        frameHeight = this.getHeight();

                        gameState = GameState.STARTING;
                    }
                    else
                    {
                        visualizingTime += System.nanoTime() - lastVisualizingTime;
                        lastVisualizingTime = System.nanoTime();
                    }
                break;
            }
            
            repaint();
            
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec; 
            if (timeLeft < 10) 
                timeLeft = 10; 
            try {
                  Thread.sleep(timeLeft);
            } catch (InterruptedException ex) { }
        }
    }
    

    @Override
    public void Draw(Graphics2D g2d)
    {
        switch (gameState)
        {
            case PLAYING:
                game.Draw(g2d, mousePosition());
            break;
            case GAMEOVER:
                //...
            break;
            case MAIN_MENU:
                mainMenu.Draw(g2d);
            break;
            case OPTIONS:
                //...
            break;
            case GAME_CONTENT_LOADING:
                //...
            break;
        }
    }
    
    public static void createMainMenu() {
    	gameState = GameState.MAIN_MENU;
    	mainMenu = new MainMenu();
    }
    
 
    
    public static void newGame()
    {
        gameTime = 0;
        lastTime = System.nanoTime();
        
        game = new Game();
    }
    

    private void restartGame()
    {
        gameTime = 0;
        lastTime = System.nanoTime();
        
        game.RestartGame();
        
        gameState = GameState.PLAYING;
    }
    
    private Point mousePosition()
    {
        try
        {
            Point mp = this.getMousePosition();
            
            if(mp != null)
                return this.getMousePosition();
            else
                return new Point(0, 0);
        }
        catch (Exception e)
        {
            return new Point(0, 0);
        }
    }
    
    
    @Override
    public void keyReleasedFramework(KeyEvent e)
    {
        switch (gameState)
        {
            case MAIN_MENU:
                
            break;
            case GAMEOVER:
                if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)
                    restartGame();
            break;
        }
    }
  
}
