package main;


import Player.Entità;

public class CollisionManager {

    Pannello gp;
    public int index;

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

        int num1,num2,num3,num4;

        switch(e.direzione){
            case "up":
                entitàTopRow = (entitàTopBound - e.velocità)/gp.ingame_size;
                num1 = gp.mappa.mapGraphic[entitàTopRow][entitàLeftCol];
                num2 = gp.mappa.mapGraphic[entitàTopRow][entitàRightCol];
                num3 = gp.mappa.mapGraphic2[entitàTopRow][entitàLeftCol];
                num4 = gp.mappa.mapGraphic2[entitàTopRow][entitàRightCol];
                // System.out.println(""+entitàTopRow+" "+entitàLeftCol);
                
                

                if(gp.mappa.Tile[num1].collisione == true || gp.mappa.Tile[num2].collisione == true ||
                    gp.mappa2.Tile[num3].collisione==true || gp.mappa2.Tile[num4].collisione==true ){
                    e.solid = true;

                }
            break;
            
            case "down":
                entitàBottomRow = (entitàBottomBound + e.velocità)/gp.ingame_size;
                num1 = gp.mappa.mapGraphic[entitàBottomRow][entitàLeftCol];
                num2 = gp.mappa.mapGraphic[entitàBottomRow][entitàRightCol];
                num3 = gp.mappa.mapGraphic2[entitàBottomRow][entitàLeftCol];
                num4 = gp.mappa.mapGraphic2[entitàBottomRow][entitàRightCol];
                // System.out.println(""+entitàBottomRow+" "+entitàLeftCol);
                
                

                if(gp.mappa.Tile[num1].collisione == true || gp.mappa.Tile[num2].collisione == true 
                    ||gp.mappa2.Tile[num3].collisione==true || gp.mappa2.Tile[num4].collisione==true){
                    e.solid = true;

                }
            break;
            
            case "right":
                entitàRightCol = (entitàRightBound + e.velocità)/gp.ingame_size;
                num1 = gp.mappa.mapGraphic[entitàTopRow][entitàRightCol];
                num2 = gp.mappa.mapGraphic[entitàBottomRow][entitàRightCol];
                num3 = gp.mappa.mapGraphic2[entitàTopRow][entitàRightCol];
                num4 = gp.mappa.mapGraphic2[entitàBottomRow][entitàRightCol];
                // System.out.println(""+entitàTopRow+" "+entitàRightCol);
                
                

                if(gp.mappa.Tile[num1].collisione == true || gp.mappa.Tile[num2].collisione == true ||
                    gp.mappa2.Tile[num3].collisione==true || gp.mappa2.Tile[num4].collisione==true){
                    e.solid = true;

                }
            break;
            
            case "left":
                entitàLeftCol = (entitàLeftBound - e.velocità)/gp.ingame_size;
                num1 = gp.mappa.mapGraphic[entitàTopRow][entitàLeftCol];
                num2 = gp.mappa.mapGraphic[entitàBottomRow][entitàLeftCol];
                num3 = gp.mappa.mapGraphic2[entitàTopRow][entitàLeftCol];
                num4 = gp.mappa.mapGraphic2[entitàBottomRow][entitàLeftCol];
                // System.out.println(""+entitàTopRow+" "+entitàLeftCol);
                
                

                if(gp.mappa.Tile[num1].collisione == true || gp.mappa.Tile[num2].collisione == true ||
                    gp.mappa2.Tile[num3].collisione==true || gp.mappa2.Tile[num4].collisione==true ){
                    e.solid = true;

                }
            break;
        }

    }

    public int checkObject (Entità e, boolean player){
        int index=999;
       

        for(int i =0; i<gp.obj.length;i++){
            if(gp.obj[i]!=null){
                e.collArea.x= e.GiocatoreX + e.collArea.x;
                e.collArea.y= e.GiocatoreY + e.collArea.y;

                gp.obj[i].solidArea.x= gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y= gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (e.direzione) {
                    case "up":
                        e.collArea.y -= e.velocità;
                        if(e.collArea.intersects(gp.obj[i].solidArea)){
                            System.out.println("up collision!");
                            if (gp.obj[i].collision==true){
                                e.solid = true;
                            }
                            if(player==true){
                                
                                index=i;
                            }

                        }
                        break;
                    case "down":
                        e.collArea.y += e.velocità;
                        if(e.collArea.intersects(gp.obj[i].solidArea)){
                            System.out.println("down collision!");
                            if (gp.obj[i].collision==true){
                                e.solid = true;
                            }
                            if(player==true){
                                
                                index=i;
                            }

                        }
                        break;
                    case "right":
                        e.collArea.x +=e.velocità;
                        if(e.collArea.intersects(gp.obj[i].solidArea)){
                            System.out.println("right collision!");
                            if (gp.obj[i].collision==true){
                                e.solid = true;
                            }
                            if(player==true){
                                
                                index=i;
                            }

                        }
                        break;
                    case "left":
                        e.collArea.x -= e.velocità;
                        if(e.collArea.intersects(gp.obj[i].solidArea)){
                            System.out.println("left collision!");
                            if (gp.obj[i].collision==true){
                                e.solid = true;
                            }
                            if(player==true){
                                
                                index=i;
                            }

                        }
                        break;
                        
                        
                    
                }
                e.collArea.x = e.solidAreaDefaultX;
                e.collArea.y = e.solidAreaDefaultY;
                gp.obj[i].solidArea.x= e.solidAreaDefaultX;
                gp.obj[i].solidArea.y=e.solidAreaDefaultY;

            }
            
        }

        return index;

    }
    
}
