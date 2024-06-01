package main;

import object.GameObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Mondo.tileManager;
import Mondo.MapMemory;
import Mondo.Mappa;
import Personaggi.Giocatore;
import Personaggi.PlayerTools;


public class Pannello extends JPanel implements Runnable {

    public final int character_size = 16;
    public final int scale = 3;
    public final int ingame_size = character_size*scale;
    public final int pix_row = 12;  // for full screen 20
    public final int pix_cols = 20; // for full screen 40
    public final int screen_width = ingame_size*pix_cols;
    public final int screen_height = ingame_size*pix_row;


    //Impostazioni di mappa
    public final int worldCol = 50, worldRow = 50;
    public final int worldWidth = ingame_size*worldCol;
    public final int worldHeight = ingame_size*worldRow;

    //Impostazioni Schermo Intero
    int fullScreen_width = screen_width;
    int fullScreen_height = screen_height;
    BufferedImage gameScreen ;
    Graphics2D graphics2;
    Graphics2D graphics3;
    public boolean fullScreenOn = false;


    Thread ThreadGioco;
    public InputTastiera keyh= new InputTastiera(this);
    public PlayerTools pTools = new PlayerTools(this);
    public Mappa start = new Mappa(this, "Mappe/StartingWoods", "Mappe/StartingWoodsDeco");
    public Mappa dungeon1= new Mappa(this, "Mappe/DarkWoods","Mappe/DarkWoodsDeco");
    public Mappa dungeon2 = new Mappa(this, "Mappe/dungeon2", "Mappe/dungeon2Deco");
    public MapMemory mapMemory = new MapMemory();
    public tileManager tileManager = new tileManager(this);
    public ScreenManager screenManager = new ScreenManager(this);
    public Giocatore giocatore = new Giocatore(this, keyh,screenManager);
    public CollisionManager CollisionManager = new CollisionManager(this);
    public GameObject obj[] = new GameObject[50];
    public AssetPlacer objPlacer = new AssetPlacer(this);
    public AssetPlacer objPlacer2 = new AssetPlacer(this);
    public EventHandler eventHandler = new EventHandler(this);
    
    public UI ui = new UI(this);
    
    public Sound music = new Sound();
    Sound sfx = new Sound();
   
    public int gameState;
    public int titleState = 0;
    public int playState = 1;
    public int pauseState = 2;
    public int dialogueState = 3;
    public int optionsState = 4;
    public int gameOver = 5;
    public long lastTime = System.nanoTime();
    public long currentTime;

    //FPS
    public double FPS = 120;
    
     
    public Pannello(){
        this.setPreferredSize(new Dimension(screen_width,screen_height));
        this.setBackground(new Color(173,255,47));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyh);
        this.setFocusable(true);

    }

    public void setUpGioco() {
        gameState = titleState;
        objPlacer.placeObject();
      
        playMusic(0);
    
        gameScreen = new BufferedImage(screen_width,screen_height,BufferedImage.TYPE_INT_ARGB_PRE);
        graphics2 = (Graphics2D)gameScreen.getGraphics();
        graphics3 = (Graphics2D)gameScreen.getGraphics();
       

    }

    public void startThreadGioco(){
        
        // qui ci assicuriamo che il thread starti 
        ThreadGioco = new Thread(this);
        ThreadGioco.start();
    }

    
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
    
        boolean isRunning = true;
    
        while (isRunning) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
    
            if (delta >= 1) {
                update();
                drawToSizedScreen();
                screenManager.drawScreen();
                
                delta--;
            }
    
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
    
        }
    }
  
    public void update(){
        if (gameState == playState) {
        giocatore.update();
   
        }
        if (gameState == pauseState) {
 
        }
    }

    public void drawToSizedScreen(){

        //debug
        long drawStart =0;
        if(keyh.z==true){
            
            drawStart = System.nanoTime();
        }


        
        
        //SCHERMATA INIZIALE
        if (gameState == titleState) {
            ui.draw(graphics2);}
        else {
        
        //MAPPA
        mapMemory.loadToMapMemory(start);
        mapMemory.loadToMapMemory(dungeon1);
        mapMemory.loadToMapMemory(dungeon2);
        Mappa currentMap = mapMemory.mapHandler[eventHandler.currentMap];
        currentMap.draw(graphics3, graphics2, tileManager);

      

        //OGGETTI
        
        for (int i =0; i<obj.length;i++){
            if (obj[i]!=null && obj[i].mapVerifier == eventHandler.currentMap){
                obj[i].draw(graphics2,this);
            }
        }
            
        
        

        
        //INTERFACCIA
        ui.draw(graphics2);

        //GIOCATORE
        if (gameState == playState || gameState == pauseState || gameState == dialogueState) {
            giocatore.draw(graphics2);
        }

        //debug 
        if(keyh.z==true){
            long drawEnd= System.nanoTime();
            long passed = drawEnd-drawStart;
            graphics2.setColor(Color.WHITE);
            graphics2.drawString("drawTime: "+passed, 10, 400);
            System.out.println("drawTime: "+passed);
        }
        
    }
       
  }


    public void playMusic (int i){

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(int i){
        music.stop();
    }

    public void playSFX(int i) {

        sfx.setFile(i);
        sfx.play();
    }

}


