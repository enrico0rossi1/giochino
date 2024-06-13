package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import Entità.Entità;
import Entità.Giocatore;
import Entità.PlayerTools;
import Mondo.tileManager;
import Mondo.MapMemory;
import Mondo.Mappa;


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
    public Graphics2D graphics2;
    public Graphics2D graphics3;
    public boolean fullScreenOn = false;


    Thread ThreadGioco;
    public InputTastiera keyh= new InputTastiera(this);
    public PlayerTools pTools = new PlayerTools(this);
    public Mappa start = new Mappa(this, "Mappe/StartingWoods", "Mappe/StartingWoodsDeco");
    public Mappa dungeon1= new Mappa(this, "Mappe/DarkWoods","Mappe/DarkWoodsDeco");
    public Mappa dungeon2 = new Mappa(this, "Mappe/Jungle", "Mappe/JungleDeco");
    public Mappa dungeon3 = new Mappa(this,"Mappe/Beach","Mappe/BeachDeco");
    public MapMemory mapMemory = new MapMemory();
    public tileManager tileManager = new tileManager(this);
    public ScreenManager screenManager = new ScreenManager(this);
    public Giocatore giocatore = new Giocatore(this, keyh,screenManager);
    public CollisionManager CollisionManager = new CollisionManager(this);
    public Entità obj[] = new Entità[20];   
    public Entità mon[] = new Entità[20]; 
    public Entità e= new Entità(this);
    public AssetPlacer assetPlacer = new AssetPlacer(this);
    public EventHandler eventHandler = new EventHandler(this);
    public ArrayList <Entità> entityList = new ArrayList<>();

    
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
    public int endGame = 6;

    public long lastTime = System.nanoTime();
    public long currentTime;

    //FPS
    public double FPS = 60;
    
     
    public Pannello(){
        this.setPreferredSize(new Dimension(screen_width,screen_height));
        this.setBackground(new Color(173,255,47));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyh);
        this.setFocusable(true);

    }

    public void setUpGioco() {
        gameState = titleState;
        assetPlacer.placeObject();
        assetPlacer.placeEnemy();
      
        playMusic(4);
    
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
        final double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        int frames = 0;
        boolean isRunning = true;
    
        while (isRunning == true) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
    
            if (delta >= 1) {
                update();
                drawToSizedScreen();
                screenManager.drawScreen();
                delta--;
                frames++;
            }
    
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            // Optional: Print FPS every second for debugging
            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames + "  " + giocatore.worldX/ingame_size + " " + giocatore.worldY/ingame_size);
                frames = 0;
                timer += 1000;
            }
        }
    }
    
    public void update(){
        if (gameState == playState) {
            giocatore.update();

            for(int i=0; i<mon.length; i++){
                if(mon[i]!=null && mon[i].mapVerifier == eventHandler.currentMap){
                    mon[i].update();
                    if(mon[i].dead==true){
                        mon[i]=null;
                    }
                }
                
            }
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
        mapMemory.loadToMapMemory(dungeon3);
        Mappa currentMap = mapMemory.mapHandler[eventHandler.currentMap];
        currentMap.draw(graphics3, graphics2, tileManager);

        //AGGIUNGIAMO LE ENTITà ALLA LISTA
        
        for(int i =0; i<obj.length; i++ ){
             if(mon[i]!=null){
                entityList.add(mon[i]);
            } if(obj[i]!= null){
                entityList.add(obj[i]);
            }
          
        }
        entityList.add(giocatore);
 
        //DISEGNIAMO LE ENTITà
        for(int i =0; i<entityList.size(); i++){
            if(entityList.get(i).mapVerifier == eventHandler.currentMap){
                entityList.get(i).draw(graphics2);

            }
        }

        //SVUOTIAMO LA LISTA
        for(int i =0; i<entityList.size(); i++){
            entityList.remove(i);
        }
 
       //INTERFACCIA
        ui.draw(graphics2);

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


