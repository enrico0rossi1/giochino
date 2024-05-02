package main;

import Player.Giocatore;
import object.GameObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Mondo.tileManager;


public class Pannello extends JPanel implements Runnable {

    public final int character_size = 16;
    public final int scale = 3;
    public final int ingame_size = character_size*scale;
    public final int pix_row = 12;
    public final int pix_cols = 15;
    public final int screen_width = ingame_size*pix_cols;
    public final int screen_height = ingame_size*pix_row;


    //Impostazioni di mappa
    public final int worldCol = 50, worldRow=50;
    public final int worldWidth = ingame_size*worldCol;
    public final int worldHeight = ingame_size*worldRow;

    Thread ThreadGioco;
    InputTastiera keyh= new InputTastiera(this);
    public tileManager mappa = new tileManager(this);
    public tileManager mappa2 = new tileManager(this);
    public Giocatore giocatore = new Giocatore(this, keyh);
    public CollisionManager CollisionManager = new CollisionManager(this);
    public GameObject obj[] = new GameObject[15];
    public ObjectPlacer objPlacer = new ObjectPlacer(this);
    public UI ui = new UI(this);
    
    public Sound music = new Sound();
    Sound sfx = new Sound();
   
    public int gameState;
    public int titleState = 0;
    public int playState = 1;
    public int pauseState = 2;

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
        objPlacer.setObject();

        playMusic(0);
    
    }
    
     public void startThreadGioco(){
        
        // qui ci assicuriamo che il thread starti 
        ThreadGioco = new Thread(this);
        ThreadGioco.start();
    }

    
    @Override
    public void run(){
        double drawInterval=1000000000/FPS;
        double delta=0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(ThreadGioco!=null){
            currentTime=System.nanoTime();
            delta+= (currentTime-lastTime)/drawInterval;
            lastTime=currentTime;

            if(delta >=1){
                update();
                repaint();
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
    public void paintComponent(Graphics graphics){


        super.paintComponent(graphics);
        Graphics2D graphics2= (Graphics2D)graphics;
        Graphics2D graphics3= (Graphics2D)graphics;
        
        //SCHERMATA INIZIALE
if (gameState == titleState) {
  ui.draw(graphics2);

}
else {
        //MAPPA
        mappa.draw(graphics2,graphics3);

      

        //OGGETTI
        for (int i =0; i<obj.length;i++){
            if (obj[i]!=null){
                obj[i].draw(graphics2,this);
            }
        }
          //INTERFACCIA
        ui.draw(graphics2);

        //GIOCATORE
        giocatore.draw(graphics2);
        
        graphics2.dispose();
       
    };
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


