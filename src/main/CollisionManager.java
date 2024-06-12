package main;


import Entità.Entità;
import Mondo.Mappa;

public class CollisionManager {

    Pannello gp;
    public int index;

    public CollisionManager(Pannello gp){
        this.gp=gp;

    }

    public void checkTile(Entità e){

        if (gp.eventHandler.currentMap==0){
            checkMap(e, gp.start);
        }
        if(gp.eventHandler.currentMap==1){
            checkMap(e, gp.dungeon1);
        }
    }


    public void checkMap(Entità e, Mappa map) {
        int entitàLeftBound = e.worldX + e.collArea.x;
        int entitàRightBound = e.worldX + e.collArea.x + e.collArea.width;
        int entitàTopBound = e.worldY + e.collArea.y;
        int entitàBottomBound = e.worldY + e.collArea.y + e.collArea.height;
    
        int entitàLeftCol = entitàLeftBound / gp.ingame_size;
        int entitàRightCol = entitàRightBound / gp.ingame_size;
        int entitàTopRow = entitàTopBound / gp.ingame_size;
        int entitàBottomRow = entitàBottomBound / gp.ingame_size;
    
        int newRow, newCol;
    
        switch (e.direzione) {
            case "up":
                newRow = (entitàTopBound - e.velocità) / gp.ingame_size;
                checkCollision(e, map, entitàLeftCol, entitàRightCol, newRow, newRow);
                break;
            case "down":
                newRow = (entitàBottomBound + e.velocità) / gp.ingame_size;
                checkCollision(e, map, entitàLeftCol, entitàRightCol, newRow, newRow);
                break;
            case "right":
                newCol = (entitàRightBound + e.velocità) / gp.ingame_size;
                checkCollision(e, map, newCol, newCol, entitàTopRow, entitàBottomRow);
                break;
            case "left":
                newCol = (entitàLeftBound - e.velocità) / gp.ingame_size;
                checkCollision(e, map, newCol, newCol, entitàTopRow, entitàBottomRow);
                break;
        }
    }
    
    //TILE COLLISION OF BOTH LAYERS OF MAP
    private void checkCollision(Entità e, Mappa map, int col1, int col2, int row1, int row2) {
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
    public int checkObject(Entità e, boolean player) {
        int index = 999; // Initialize to a high number as a default "not found" value

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null && gp.obj[i].mapVerifier == gp.eventHandler.currentMap) {
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
                        e.collArea.y -= e.velocità;
                        break;
                    case "down":
                        e.collArea.y += e.velocità;
                        break;
                    case "right":
                        e.collArea.x += e.velocità;
                        break;
                    case "left":
                        e.collArea.x -= e.velocità;
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
    public int checkEntity(Entità e, Entità [] target){

        int index = 999; // Initialize to a high number as a default "not found" value

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null && target[i].mapVerifier == gp.eventHandler.currentMap) {
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
                        e.collArea.y -= e.velocità;
                        break;
                    case "down":
                        e.collArea.y += e.velocità;
                        break;
                    case "right":
                        e.collArea.x += e.velocità;
                        break;
                    case "left":
                        e.collArea.x -= e.velocità;
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

    public boolean checkPlayer(Entità e){


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
                e.collArea.y -= e.velocità;
                break;
            case "down":
                e.collArea.y += e.velocità;
                break;
            case "right":
                e.collArea.x += e.velocità;
                break;
            case "left":
                e.collArea.x -= e.velocità;
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

