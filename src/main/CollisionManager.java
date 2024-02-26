package main;


import Player.Entità;

public class CollisionManager {

    Pannello gp;

    public CollisionManager(Pannello gp){
        this.gp=gp;

    }

    public void checkTile(Entità e){

        int entitàLeftBound = e.GiocatoreX + e.collArea.x;
        int entitàRightBound = e.GiocatoreX + e.collArea.x + e.collArea.width;
        int entitàTopBound = e.GiocatoreY + e.collArea.y;
        int entitàBottomBound = e.GiocatoreY + e.collArea.y + e.collArea.height;

        int entitàLeftCol = entitàLeftBound/gp.ingame_size;
        int entitàRightCol = entitàRightBound/gp.ingame_size;
        int entitàTopRow = entitàTopBound/gp.ingame_size;
        int entitàBottomRow = entitàBottomBound/gp.ingame_size;

        int num1,num2;

        switch(e.direzione){
            case "up":
                entitàTopRow = (entitàTopBound - e.velocità)/gp.ingame_size;
                num1 = gp.mappa.mapGraphic[entitàLeftCol][entitàTopRow];
                num2 = gp.mappa.mapGraphic[entitàRightCol][entitàTopRow];
                System.out.println(""+entitàLeftCol+" "+entitàTopRow+" "+num1);

                if(gp.mappa.Tile[num1].collisione == true || gp.mappa.Tile[num2].collisione == true){
                    e.solid = true;

                }
             break;
            case "down":
             break;
            case "right":
             break;
            case "left":
             break;
        }

    }
    
}
