package warrioradventure;


import entities.GameEntity;
import gameworld.Map;

public class CollisionManager {

    GamePanel gp;
    public int index;

    public CollisionManager(GamePanel gp){
        this.gp=gp;

    }

    public void checkTile(GameEntity e){

        for(int i=0; i<gp.mapMemory.mapHandler.length;i++){
            if (i==gp.eventHandler.currentMapIndex && gp.mapMemory.mapHandler[i]!=null){
                checkMap(e,gp.mapMemory.mapHandler[i]);
            }

        }
    }


    public void checkMap(GameEntity e, Map map) {
        int entityLeftBound = e.worldX + e.collArea.x;
        int entityRightBound = e.worldX + e.collArea.x + e.collArea.width;
        int entityTopBound = e.worldY + e.collArea.y;
        int entityBottomBound = e.worldY + e.collArea.y + e.collArea.height;
    
        int entityLeftCol = entityLeftBound / gp.ingame_size;
        int entityRightCol = entityRightBound / gp.ingame_size;
        int entityTopRow = entityTopBound / gp.ingame_size;
        int entityBottomRow = entityBottomBound / gp.ingame_size;
    
        int newRow, newCol;
    
        switch (e.direzione) {
            case "up":
                newRow = (entityTopBound - (int)(gp.giocatore.speed*gp.screenManager.scaleY)) / gp.ingame_size;
                checkCollision(e, map, entityLeftCol, entityRightCol, newRow, newRow);
                break;
            case "down":
                newRow = (entityBottomBound + (int)(gp.giocatore.speed*gp.screenManager.scaleY)) / gp.ingame_size;
                checkCollision(e, map, entityLeftCol, entityRightCol, newRow, newRow);
                break;
            case "right":
                newCol = (entityRightBound + (int)(gp.giocatore.speed*gp.screenManager.scaleX)) / gp.ingame_size;
                checkCollision(e, map, newCol, newCol, entityTopRow, entityBottomRow);
                break;
            case "left":
                newCol = (entityLeftBound - (int)(gp.giocatore.speed*gp.screenManager.scaleX)) / gp.ingame_size;
                checkCollision(e, map, newCol, newCol, entityTopRow, entityBottomRow);
                break;
        }
    }
    
    //TILE COLLISION OF BOTH LAYERS OF MAP
    private void checkCollision(GameEntity e, Map map, int col1, int col2, int row1, int row2) {
        int[] groundTiles = {
            map.ground[row1][col1],
            map.ground[row1][col2],
            map.ground[row2][col1],
            map.ground[row2][col2]
        };
    
        int[] decoTiles = {
            map.deco[row1][col1],
            map.deco[row1][col2],
            map.deco[row2][col1],
            map.deco[row2][col2]
        };
    
        for (int num : groundTiles) {
            if (gp.tileManager.Tile[num].collisione) {
                e.solid = true;
                break;
            }else{
                e.solid = false;
            }
        }
    
        for (int num : decoTiles) {
            if (gp.tileManager.Tile[num].collisione) {
                e.solid = true;
                break;
            }else{
                e.solid=false;
            }
        }
    }
    

    //OBJECT COLLISION
    public int checkObject(GameEntity e, boolean player) {
        int index = Integer.MAX_VALUE ; // Initialize to a high number as a default "not found" value

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null && gp.obj[i].mapVerifier == gp.eventHandler.currentMapIndex) {
                // Backup original positions
                int originalCollAreaX = e.collArea.x;
                int originalCollAreaY = e.collArea.y;
                int originalSolidAreaX = gp.obj[i].collArea.x;
                int originalSolidAreaY = gp.obj[i].collArea.y;

                // Update positions based on current object
                e.collArea.x = e.worldX + e.collArea.x;
                e.collArea.y = e.worldY + e.collArea.y;
                gp.obj[i].collArea.x = gp.obj[i].worldX + gp.obj[i].collArea.x;
                gp.obj[i].collArea.y = gp.obj[i].worldY + gp.obj[i].collArea.y;

                // Adjust collision area based on direction
                switch (e.direzione) {
                    case "up":
                        e.collArea.y -= e.speed;
                        break;
                    case "down":
                        e.collArea.y += e.speed;
                        break;
                    case "right":
                        e.collArea.x += e.speed;
                        break;
                    case "left":
                        e.collArea.x -= e.speed;
                        break;
                }

                // Check for collision
                if (e.collArea.intersects(gp.obj[i].collArea)) {
                    System.out.println(e.direzione + " collision!");

                    if (gp.obj[i].solid) {
                        e.solid = true;
                        
                        if (player) {
                            index = i;
                        }
                    }
                }

                // Reset positions to original values
                e.collArea.x = originalCollAreaX;
                e.collArea.y = originalCollAreaY;
                gp.obj[i].collArea.x = originalSolidAreaX;
                gp.obj[i].collArea.y = originalSolidAreaY;
            }
        }

        return index;
    }

    //MONSTERS COLLISION
    public int checkEntity(GameEntity e, GameEntity [] target){

        int index = Integer.MAX_VALUE; // Initialize to a high number as a default "not found" value

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null && target[i].mapVerifier == gp.eventHandler.currentMapIndex) {
                // Backup original positions
                int originalCollAreaX = e.collArea.x;
                int originalCollAreaY = e.collArea.y;
                int originalSolidAreaX = target[i].collArea.x;
                int originalSolidAreaY = target[i].collArea.y;

                // Update positions based on current object
                e.collArea.x = e.worldX + e.collArea.x;
                e.collArea.y = e.worldY + e.collArea.y;
                target[i].collArea.x = target[i].worldX + target[i].collArea.x;
                target[i].collArea.y = target[i].worldY + target[i].collArea.y;

                // Adjust collision area based on direction
                switch (e.direzione) {
                    case "up":
                        e.collArea.y -= e.speed*gp.screenManager.scaleY;
                        break;
                    case "down":
                        e.collArea.y += e.speed*gp.screenManager.scaleY;
                        break;
                    case "right":
                        e.collArea.x += e.speed*gp.screenManager.scaleX;
                        break;
                    case "left":
                        e.collArea.x -= e.speed*gp.screenManager.scaleX;
                        break;
                }

                // Check for collision
                if (e.collArea.intersects(target[i].collArea)&&target[i]!=e) {
                    System.out.println(e.direzione + " collision!");
                    
                    e.solid=true;
                    index=i;
                    
                }

                // Reset positions to original values
                e.collArea.x = originalCollAreaX;
                e.collArea.y = originalCollAreaY;
                target[i].collArea.x = originalSolidAreaX;
                target[i].collArea.y = originalSolidAreaY;
            }
        }

        return index;


    }

    public boolean checkPlayer(GameEntity e){


        boolean contactPlayer=false;


       // Backup original positions
        int originalCollAreaX = e.collArea.x;
        int originalCollAreaY = e.collArea.y;
        int originalSolidAreaX = gp.giocatore.collArea.x;
        int originalSolidAreaY = gp.giocatore.collArea.y;
        // Update positions based on current object
        e.collArea.x = e.worldX + e.collArea.x;
        e.collArea.y = e.worldY + e.collArea.y;
        gp.giocatore.collArea.x = gp.giocatore.worldX + gp.giocatore.collArea.x;
        gp.giocatore.collArea.y = gp.giocatore.worldY + gp.giocatore.collArea.y;
        // Adjust collision area based on direction
        switch (e.direzione) {
            case "up":
                e.collArea.y -= e.speed*gp.screenManager.scaleY;
                break;
            case "down":
                e.collArea.y += e.speed*gp.screenManager.scaleY;
                break;
            case "right":
                e.collArea.x += e.speed*gp.screenManager.scaleX;
                break;
            case "left":
                e.collArea.x -= e.speed*gp.screenManager.scaleX;
                break;
        }
        // Check for collision
        if (e.collArea.intersects(gp.giocatore.collArea)) {
            System.out.println(e.direzione + " collision!");
            
            contactPlayer=true;
            e.solid=true;
            
        }
        // Reset positions to original values
        e.collArea.x = originalCollAreaX;
        e.collArea.y = originalCollAreaY;
        gp.giocatore.collArea.x = originalSolidAreaX;
        gp.giocatore.collArea.y = originalSolidAreaY;

        return contactPlayer;
    
    }



}

