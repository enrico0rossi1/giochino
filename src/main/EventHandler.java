package main;

public class EventHandler {

  GamePanel gp;
  EventRectangle eventRect[][];
  int previousEventX,previousEventY;
  boolean canTouchEvent = true;
  public int currentMapIndex =0;
  public int nextMap;
  final int startingWoodsMap = 0;
  final int darkWoodsMap = 1;
  final int jungleMap = 2;
  final int beachMap = 3;
  
  public EventHandler (GamePanel gp) {
    
    this.gp = gp;
    eventRect = new EventRectangle[gp.worldCol][gp.worldRow];
    
    int col = 0;
    int row = 0;

    while (col < gp.worldCol && row < gp.worldRow ){
      eventRect[col][row] = new EventRectangle();
      eventRect[col][row].x = 0-7;
      eventRect[col][row].y = 0;
      eventRect[col][row].width = gp.ingame_size + 20; 
      eventRect[col][row].height = gp.ingame_size + 5;
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
  
      //messaggio di presentazione
      if (hitEvent(25, 25, "any", startingWoodsMap)){presentation(25,25);}

      // ritorno a startingWoods
      if(monChecker()){
        if (hitEvent(18,19, "any",darkWoodsMap )){askForTeleport(startingWoodsMap);}
        if (hitEvent(18,19, "any",jungleMap )){askForTeleport(startingWoodsMap);}
        if (hitEvent(18,19, "any",beachMap )){askForTeleport(startingWoodsMap);}
      }else{
        if (hitEvent(18,19, "any",darkWoodsMap )){deniesTeleport();}
        if (hitEvent(18,19, "any",jungleMap )){deniesTeleport();}
        if (hitEvent(18,19, "any",beachMap )){deniesTeleport();}

      }
      
     
      //viaggio verso località diverse da startingWoods
      if (hitEvent(39,25, "any",startingWoodsMap)){askForTeleport(jungleMap);}
      if (hitEvent(26,5, "any",startingWoodsMap)){askForTeleport(darkWoodsMap);}
      if (hitEvent(25,5, "any",startingWoodsMap)){askForTeleport(darkWoodsMap);}
      if (hitEvent(9,25, "any",startingWoodsMap)){askForTeleport(beachMap);}
  
    }
  }
  
  
  public boolean hitEvent (int col , int row, String reqDirection, int mapindex) {

    boolean hit = false;
    
    gp.giocatore.collArea.x = gp.giocatore.worldX + gp.giocatore.collArea.x ; 
    gp.giocatore.collArea.y = gp.giocatore.worldY + gp.giocatore.collArea.y ;
    eventRect[col][row].x = col*gp.ingame_size + eventRect[col][row].x ;
    eventRect[col][row].y = row*gp.ingame_size + eventRect[col][row].y ;
        
    if(gp.giocatore.collArea.intersects(eventRect[col][row]) && eventRect[col][row].happened == false
        && mapindex==currentMapIndex) {
      
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


//CONTROLLIAMO SE SONO RIMASTI MOSTRI NEL DUNGEON
public boolean monChecker(){
  boolean checker=true;
  for(int a=0;a<gp.mon.length;a++){
    if(gp.mon[a]!=null&&gp.mon[a].mapVerifier==gp.eventHandler.currentMapIndex){
      for(int i=a; i<gp.mon.length;i++){
          if (gp.mon[i]!=null&& gp.mon[a]!=null&&gp.mon[i].name==gp.mon[a].name){
            checker=false;
          }
        }
      }
  }

  return checker;
}
  
  // fa danno una volta sola
  public void hiddenTrap (int col,int row){
      gp.giocatore.vita -= 1 ;
      
      eventRect[col][row].happened = true ;
  }
  
  //fa danno dintanto che ci stai sopra
  public void damagePool (int col,int row){
    gp.giocatore.vita -= 1 ;
   
  }

  public void presentation(int col,int row) {
   
    gp.ui.currentDialogue = "Esplora il mondo per trovare la chiave che aprirà la porta \nper il tesoro della cripta"; 
    gp.ui.dialogueChoice1 = "";
    gp.ui.dialogueChoice2 = "";
    gp.ui.dialogueChoice = 3;
    gp.gameState = gp.dialogueState;
    eventRect[col][row].happened = true ;
  }

  public void deniesTeleport(){
    gp.gameState = gp.dialogueState;
    gp.ui.currentDialogue = "Non puoi tornare indietro finchè non sconfiggi \ntutti i nemici !!";
    gp.ui.dialogueChoice1 = "Abbi coraggio";
    gp.ui.dialogueChoice2 = "";
    gp.ui.dialogueChoice = 3;
    canTouchEvent = false;
  }


  public void askForTeleport(int map) { 
     gp.gameState = gp.dialogueState;
     gp.ui.dialogueChoice1 = "No,grazie";
     gp.ui.dialogueChoice2 = "Sì";
     gp.ui.dialogueChoice = 1;

     switch (map) {
     case darkWoodsMap:
     nextMap = darkWoodsMap;
     gp.ui.currentDialogue = "Vuoi esplorare le foreste bule? Sconfiggi tutti\n i nemici per tornare indietro";
   
   

     break;
     case jungleMap:
     nextMap = jungleMap;
     gp.ui.currentDialogue = "Vuoi esplorare la giungla? Sconfiggi tutti\n i nemici per tornare indietro";


     break;
     case beachMap: 
     nextMap = beachMap;
     gp.ui.currentDialogue = "Vuoi esplorare la spiaggia? Sconfiggi tutti\n i nemici per tornare indietro";


     break;
     case startingWoodsMap: 
     nextMap = startingWoodsMap;
     gp.ui.currentDialogue = "Stai per tornare al boschetto tranquillo, ti va?";


     break;
     }

     canTouchEvent = false;
  }

  public void teleport(int map) {
  
  gp.giocatore.worldX = gp.ingame_size * 25;
  gp.giocatore.worldY = gp.ingame_size * 25;
  gp.stopMusic(currentMapIndex);
  currentMapIndex = map;
  gp.playMusic(map);

  }
  
}
