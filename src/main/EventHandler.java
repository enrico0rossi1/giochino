package main;


import java.awt.Rectangle;

public class EventHandler {

    Pannello gp;
    Rectangle eventRect;
    int eventRectDefaultX,eventRectDefaultY;
    

    public EventHandler (Pannello gp) {

      this.gp = gp;
      eventRect = new Rectangle();
      eventRect.x = 12;
      eventRect.y = 12;
      eventRect.width = 8; 
      eventRect.height = 8;
      eventRectDefaultX = eventRect.x;
      eventRectDefaultY = eventRect.y;

    }
    
    public void checkEvent() {
      if (hitEvent(23, 23, "any")== true) {damagePit();
        
      }
      if (hitEvent(23, 10, "any")== true) { damagePit();
       
      }
      if (hitEvent(25, 25, "any")== true) { healingPool();
       
      }
      if (hitEvent(26,6, "any")){teleport();
        
      }
    }

    public boolean hitEvent (int eventCol , int eventRow, String reqDirection) {

        boolean hit = false ;

        gp.giocatore.collArea.x = gp.giocatore.GiocatoreX + gp.giocatore.collArea.x ; 
        gp.giocatore.collArea.y = gp.giocatore.GiocatoreY + gp.giocatore.collArea.y ; 

        eventRect.x = eventCol*gp.ingame_size + eventRect.x ;
        eventRect.y = eventRow*gp.ingame_size + eventRect.y ;

        if(gp.giocatore.collArea.intersects(eventRect)) {
              if (gp.giocatore.direzione.contentEquals(reqDirection) || reqDirection.contentEquals("any") ) {
            hit = true;
          }
        }

        gp.giocatore.collArea.x = gp.giocatore.solidAreaDefaultX ; 
        gp.giocatore.collArea.y = gp.giocatore.solidAreaDefaultY ; 

        eventRect.x =  eventRectDefaultX ;
        eventRect.y =  eventRectDefaultY ;

        return hit ; 
    }

    public void damagePit (){
        gp.giocatore.vita -= 1 ;
    }
    public void healingPool() {
      if (gp.giocatore.vita < gp.giocatore.vitaMax)
      gp.giocatore.vita += 1 ;
    }
    public void teleport() {
  
       gp.giocatore.GiocatoreX = gp.ingame_size * 27;
       gp.giocatore.GiocatoreY = gp.ingame_size * 47;
   

    }
}
