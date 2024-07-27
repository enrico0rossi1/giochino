package warrioradventure;

import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Graphics;

public class ScreenSizeManager {

    GamePanel gp;
    int screen_height,screen_width;
    public double scaleX;
    public double scaleY;

    public ScreenSizeManager(GamePanel gp){
        this.gp=gp;
    }


    public void setFullScreen() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(WarriorAdventureGame.window);

        screen_width = WarriorAdventureGame.window.getWidth();
        screen_height = WarriorAdventureGame.window.getHeight();
       

    }

    public void setStandardScreen(){

        screen_width = gp.screen_width;
        screen_height = gp.screen_height;
  

    }

    public void drawScreen (){
        
        if(gp.fullScreenOn==false){
            setStandardScreen();
            scaleX =2.3;
            scaleY =2.3;
        }
        
        if(gp.fullScreenOn==true){
            setFullScreen();
            scaleX=3.2;
            scaleY=3.2;
        }
        
        Graphics graphics = gp.getGraphics();
        graphics.drawImage(gp.gameScreen,0,0,screen_width,screen_height,null);
        graphics.dispose();

    }
        


    
    
}
