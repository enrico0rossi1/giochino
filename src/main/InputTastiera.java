package main;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class InputTastiera implements KeyListener {

    public boolean w,a,s,d;

    public InputTastiera(){}
    public void keyTyped(KeyEvent e){}
    
    @Override 
    public void keyPressed(KeyEvent e) {

        int premuto = e.getKeyCode();
        
        if(premuto == KeyEvent.VK_W){
            w=true;
            
        }

        if(premuto == KeyEvent.VK_A){
            a=true;
        }

        if(premuto == KeyEvent.VK_S){
            s=true;
        }

        if(premuto == KeyEvent.VK_D){
            d=true;

        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int rilasciato = e.getKeyCode();

        if(rilasciato == KeyEvent.VK_W){
            w=false;
        }

        if(rilasciato == KeyEvent.VK_A){
            a=false;
        }

        if(rilasciato == KeyEvent.VK_S){
            s=false;
        }

        if(rilasciato == KeyEvent.VK_D){
            d=false;
        }
        
    }
    
}
