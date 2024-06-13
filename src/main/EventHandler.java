package main;

public class EventHandler {

  Pannello gp;
  EventRectangle eventRect[][];
  int previousEventX,previousEventY;
  boolean canTouchEvent = true;
  public int currentMap =0;
  final int startingWoodsMap = 0;
  final int darkWoodsMap = 1;
  final int jungleMap = 2;
  final int beachMap = 3;
  
  public EventHandler (Pannello gp) {
    
    this.gp = gp;
    eventRect = new EventRectangle[gp.worldCol][gp.worldRow];
    
    int col = 0;
    int row = 0;

    while (col < gp.worldCol && row < gp.worldRow ){
      eventRect[col][row] = new EventRectangle();
      eventRect[col][row].x = 0;
      eventRect[col][row].y = 0;
      eventRect[col][row].width = gp.ingame_size; 
      eventRect[col][row].height = gp.ingame_size;
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

    int xDistance = Math.abs(gp.giocatore.worldX - previousEventX);
    int yDistance = Math.abs(gp.giocatore.worldY - previousEventY);
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

      if (hitEvent(20,27, "any",darkWoodsMap)){teleportTostartingWoods();}
      //if (hitEvent(20,27, "any",jungleMap)){teleportTostartingWoods();}
      if (hitEvent(19,25, "any",beachMap)){teleportTostartingWoods();}
      
      if (hitEvent(39,25, "any",startingWoodsMap)){teleportToJungle();}
      if (hitEvent(25,5, "any",startingWoodsMap)){teleportToDarkWoods();}
      if (hitEvent(26,5, "any",startingWoodsMap)){teleportToDarkWoods();}
      if (hitEvent(9,25, "any",startingWoodsMap)){teleportToBeach();}
      
      // eventi di prova
      //if (hitEvent(24,7, "any",startingWoodsMap)){dialogueTest(24,7);}
      //if (hitEvent(30,23, "any",startingWoodsMap)){stopMusicTest();}
    }
  }
  
  
  public boolean hitEvent (int col , int row, String reqDirection, int mapindex) {

    boolean hit = false;
    
    gp.giocatore.collArea.x = gp.giocatore.worldX + gp.giocatore.collArea.x ; 
    gp.giocatore.collArea.y = gp.giocatore.worldY + gp.giocatore.collArea.y ;
    eventRect[col][row].x = col*gp.ingame_size + eventRect[col][row].x ;
    eventRect[col][row].y = row*gp.ingame_size + eventRect[col][row].y ;
        
    if(gp.giocatore.collArea.intersects(eventRect[col][row]) && eventRect[col][row].happened == false
        && mapindex==currentMap) {
      
      if (gp.giocatore.direzione.contentEquals(reqDirection) || reqDirection.contentEquals("any") ) {
        hit = true;
        previousEventX = gp.giocatore.worldX;
        previousEventY = gp.giocatore.worldY;
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

 public void teleportTostartingWoods() {
    gp.giocatore.worldX = gp.ingame_size * 25;
    gp.giocatore.worldY = gp.ingame_size * 25;
    gp.stopMusic(currentMap);
    currentMap = startingWoodsMap;
    gp.playMusic(startingWoodsMap);
  }

  public void teleportToDarkWoods() {
    gp.giocatore.worldX = gp.ingame_size * 25;
    gp.giocatore.worldY = gp.ingame_size * 25;
    gp.stopMusic(currentMap);
    currentMap = darkWoodsMap;
  //gp.playMusic(darkWoodsMap);
    }


  public void teleportToJungle() {
    gp.giocatore.worldX = gp.ingame_size * 25;
    gp.giocatore.worldY = gp.ingame_size * 25;
    currentMap = jungleMap;
   // gp.playMusic(jungleMap);
  }

  public void teleportToBeach() {
    gp.giocatore.worldX = gp.ingame_size * 25;
    gp.giocatore.worldY = gp.ingame_size * 25;
    currentMap = beachMap;
    //gp.playMusic(beachMap);
  }
  public void dialogueTest (int col,int row) {

    gp.gameState = gp.dialogueState;
    gp.stopMusic(currentMap);
    canTouchEvent = false;

  }

  public void stopMusicTest () {
    gp.stopMusic(currentMap);
    gp.playMusic(jungleMap);
  }

}
