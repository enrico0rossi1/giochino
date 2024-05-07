package main;

public class EventHandler {

    Pannello gp;
    EventRectangle eventRect[][];
    

    public EventHandler (Pannello gp) {

      this.gp = gp;

      eventRect = new EventRectangle[gp.worldCol][gp.worldRow];
      
      int col = 0;
      int row = 0;

      while (col < gp.worldCol && row < gp.worldRow ){

      eventRect[col][row] = new EventRectangle();
      eventRect[col][row].x = 12;
      eventRect[col][row].y = 12;
      eventRect[col][row].width = 8; 
      eventRect[col][row].height = 8;
      eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
      eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
      
      col++;
      if (col == gp.worldCol) {
           col = 0;
           row++;
      }
    }
    
  }
    public void checkEvent() {
      if (hitEvent(23, 23, "any")== true) {hiddenTrap(23,23);
        
      }
      if (hitEvent(10, 23, "any")== true) { damagePool(10,23);
       
      }
      if (hitEvent(25, 25, "any")== true) { healingPool();
       
      }
      if (hitEvent(26,6, "any")){teleport();
        
      }
    }

    public boolean hitEvent (int col , int row, String reqDirection) {

        boolean hit = false ;

        gp.giocatore.collArea.x = gp.giocatore.GiocatoreX + gp.giocatore.collArea.x ; 
        gp.giocatore.collArea.y = gp.giocatore.GiocatoreY + gp.giocatore.collArea.y ; 

        eventRect[col][row].x = col*gp.ingame_size + eventRect[col][row].x ;
        eventRect[col][row].y = row*gp.ingame_size + eventRect[col][row].y ;

        if(gp.giocatore.collArea.intersects(eventRect[col][row]) && eventRect[col][row].happened == false) {
              if (gp.giocatore.direzione.contentEquals(reqDirection) || reqDirection.contentEquals("any") ) {
            hit = true;
          }
        }

        gp.giocatore.collArea.x = gp.giocatore.solidAreaDefaultX ; 
        gp.giocatore.collArea.y = gp.giocatore.solidAreaDefaultY ; 

        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX ;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY ;

        return hit ; 
    }

    public void hiddenTrap (int col,int row){
        gp.giocatore.vita -= 1 ;
        
        eventRect[col][row].happened = true ;
    }

    public void damagePool (int col,int row){
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
