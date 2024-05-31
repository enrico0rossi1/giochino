package main;

public class EventHandler {

  Pannello gp;
  EventRectangle eventRect[][];
  int previousEventX,previousEventY;
  boolean canTouchEvent = true;
  public int telNum;
  
  public EventHandler (Pannello gp, int telNum) {
    
    this.gp = gp;
    this.telNum=telNum;
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

    int xDistance = Math.abs(gp.giocatore.posizioneX - previousEventX);
    int yDistance = Math.abs(gp.giocatore.posizioneY - previousEventY);
    int distance = Math.max(xDistance,yDistance);

    if (distance > gp.ingame_size){
      canTouchEvent = true ;
    }
    
    if (canTouchEvent == true) {
      if (hitEvent(23, 23, "any",0)) {hiddenTrap(23,23);}
      if (hitEvent(10, 23, "any",0)) {damagePool(10,23);}
      if (hitEvent(11, 23, "any",0)) {damagePool(11,23);}
      if (hitEvent(12, 23, "any",0)) {damagePool(12,23);}
      if (hitEvent(25, 25, "any",0)) {healingPool();}
      if (hitEvent(26,6, "any",0)){teleport();}
      if (hitEvent(25,6, "any",0)){teleport();}
      if (hitEvent(20,18, "any",1)){antiTeleport();}
    }
  }
  
  
  public boolean hitEvent (int col , int row, String reqDirection, int mapindex) {

    boolean hit = false;
    
    gp.giocatore.collArea.x = gp.giocatore.posizioneX + gp.giocatore.collArea.x ; 
    gp.giocatore.collArea.y = gp.giocatore.posizioneY + gp.giocatore.collArea.y ;
    eventRect[col][row].x = col*gp.ingame_size + eventRect[col][row].x ;
    eventRect[col][row].y = row*gp.ingame_size + eventRect[col][row].y ;
        
    if(gp.giocatore.collArea.intersects(eventRect[col][row]) && eventRect[col][row].happened == false
        && mapindex==telNum) {
      
      if (gp.giocatore.direzione.contentEquals(reqDirection) || reqDirection.contentEquals("any") ) {
        hit = true;
        previousEventX = gp.giocatore.posizioneX;
        previousEventY = gp.giocatore.posizioneY;
      }
        
    }

    gp.giocatore.collArea.x = gp.giocatore.solidAreaDefaultX ; 
    gp.giocatore.collArea.y = gp.giocatore.solidAreaDefaultY ;
    eventRect[col][row].x = eventRect[col][row].eventRectDefaultX ;
    eventRect[col][row].y = eventRect[col][row].eventRectDefaultY; 
    
    return hit; 
  }

  // fa danno una volta sola
  public void hiddenTrap (int col,int row){
      gp.giocatore.vita -= 1 ;
      
      eventRect[col][row].happened = true ;
  }
  
  //fa danno ogni volta che ci passi sopra
  public void damagePool (int col,int row){
    gp.giocatore.vita -= 1 ;
    canTouchEvent = false;
    //  eventRect[col][row].happened = true ;
  }

  //accade fintanto che ci stai sopra
  public void healingPool() {
    if (gp.giocatore.vita < gp.giocatore.vitaMax)
    gp.giocatore.vita += 1 ;
  }
  
  public void teleport() {

    gp.giocatore.posizioneX = gp.ingame_size * 25;
    gp.giocatore.posizioneY = gp.ingame_size * 25;
    telNum++;
     
  
  }

  public void antiTeleport(){
    gp.giocatore.posizioneX = gp.ingame_size * 25;
    gp.giocatore.posizioneY = gp.ingame_size * 25;
    telNum--;
  }
}
