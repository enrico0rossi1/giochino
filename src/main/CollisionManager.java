package main;


import Personaggi.Entità;
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
        int entitàLeftBound = e.posizioneX + e.collArea.x;
        int entitàRightBound = e.posizioneX + e.collArea.x + e.collArea.width;
        int entitàTopBound = e.posizioneY + e.collArea.y;
        int entitàBottomBound = e.posizioneY + e.collArea.y + e.collArea.height;
    
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
            }
        }
    
        for (int num : decoTiles) {
            if (gp.tileManager.Tile[num].collisione) {
                e.solid = true;
                break;
            }
        }
    }
    
    
    
    
 

    public int checkObject(Entità e, boolean player) {
        int index = 999; // Initialize to a high number as a default "not found" value

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null && gp.obj[i].mapVerifier == gp.eventHandler.currentMap) {
                // Backup original positions
                int originalCollAreaX = e.collArea.x;
                int originalCollAreaY = e.collArea.y;
                int originalSolidAreaX = gp.obj[i].solidArea.x;
                int originalSolidAreaY = gp.obj[i].solidArea.y;

                // Update positions based on current object
                e.collArea.x = e.posizioneX + e.collArea.x;
                e.collArea.y = e.posizioneY + e.collArea.y;
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

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
                if (e.collArea.intersects(gp.obj[i].solidArea)) {
                    System.out.println(e.direzione + " collision!");

                    if (gp.obj[i].collision) {
                        e.solid = true;

                        if (player) {
                            index = i;
                        }
                    }
                }

                // Reset positions to original values
                e.collArea.x = originalCollAreaX;
                e.collArea.y = originalCollAreaY;
                gp.obj[i].solidArea.x = originalSolidAreaX;
                gp.obj[i].solidArea.y = originalSolidAreaY;
            }
        }

        return index;
    }
}
