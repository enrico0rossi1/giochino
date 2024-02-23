package main;

import Player.Giocatore;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import Mondo.tileManager;


public class Pannello extends JPanel implements Runnable {

    public final static int character_size = 16;
    public final static int scale = 3;
    public final static int ingame_size = character_size*scale;
    public final static int pix_row = 12;
    public final static int pix_cols = 15;
    final int screen_width = ingame_size*pix_cols;
    final int screen_length = ingame_size*pix_row;

    Thread ThreadGioco;
    InputTastiera keyh= new InputTastiera();
    Giocatore giocatore = new Giocatore(this, keyh);
    tileManager mappa = new tileManager(this);
    
    //FPS
    int FPS = 60;
    
     
    public Pannello(){
        this.setPreferredSize(new Dimension(screen_width,screen_length));
        this.setBackground(new Color(46,75,255));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyh);
        this.setFocusable(true);

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
        
        mappa.draw(graphics2);
        giocatore.draw(graphics2);
        
        graphics2.dispose();
       
    };


}


