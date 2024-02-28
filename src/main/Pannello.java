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
    InputTastiera keyh= new InputTastiera();
    public tileManager mappa = new tileManager(this);
    public tileManager mappa2 = new tileManager(this);
    public Giocatore giocatore = new Giocatore(this, keyh);
    public CollisionManager CollisionManager = new CollisionManager(this);
    public GameObject obj[] = new GameObject[8];
    public AssetSetter aSetter = new AssetSetter(this);
    
    //FPS
    int FPS = 60;
    
     
    public Pannello(){
        this.setPreferredSize(new Dimension(screen_width,screen_height));
        this.setBackground(new Color(46,75,255));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyh);
        this.setFocusable(true);

    }

    public void setUpGioco() {
        aSetter.setObject();
    }
    
     public void startThreadGioco(){
        
        // qui ci assicuriamo che il thread starti 
        ThreadGioco = new Thread(this);
        ThreadGioco.start();
    }

    @Override
    public void run() {
        

        double intervallo = 1000000000/FPS; //refreshiamo lo schermo 
        double prox_int = System.nanoTime() + intervallo;
        long timer =0;
        long ultimoTempo = System.nanoTime();
        long tempoCorrente;
        int drawCount=0;

        while (ThreadGioco != null){
            tempoCorrente=System.nanoTime();
            timer += tempoCorrente - ultimoTempo;
            ultimoTempo = tempoCorrente;
            update();
            repaint();
            drawCount++;
            
            try {
                double tempoRimasto = prox_int - System.nanoTime();
                tempoRimasto=tempoRimasto/1000000;
                

                if (tempoRimasto<0){
                    tempoRimasto=0;
                }
                Thread.sleep((long) tempoRimasto);

                prox_int = prox_int+intervallo;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(timer>=1000000000){
                System.out.println("FPS ="+drawCount);
                drawCount=0;
                timer=0;
            }
            

            
        }
         

    }
    public void update(){
        
        giocatore.update();

    }
    
    public void paintComponent(Graphics graphics){


        super.paintComponent(graphics);
        Graphics2D graphics2= (Graphics2D)graphics;
        Graphics2D graphics3= (Graphics2D)graphics;
        
        //MAPPA
        mappa.draw(graphics2,graphics3);

        //OGGETTI
        for (int i =0; i<obj.length;i++){
            if (obj[i]!=null){
                obj[i].draw(graphics2,this);
            }
        }
        
        //GIOCATORE
        giocatore.draw(graphics2);
        
        graphics2.dispose();
       
    };

}


