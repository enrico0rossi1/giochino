package entities;

import warrioradventure.*;

public class PlayerTools {

    GamePanel gp;
    

    public PlayerTools(GamePanel gp){
        this.gp=gp;
    }

    public void checkAttack(int i){
        if(i!=Integer.MAX_VALUE && gp.mon[i].invincible == false){
            gp.mon[i].vita--;
            gp.mon[i].invincible=true;
            gp.mon[i].damageReaction();
            gp.playSFX(14);
            gp.ui.showMessage("colpo a segno");
        }
         
    }

    public void attacking(){
        if(gp.giocatore.spriteNum>=1 && gp.giocatore.spriteNum<=5){
            
            //SALVIAMO LE COORDINATE CORRENTI
            int currentWorldX= gp.giocatore.worldX;
            int currentWorldY= gp.giocatore.worldY;
            int solidAreaWidth = gp.giocatore.collArea.width;
            int solidAreaHeight = gp.giocatore.collArea.height;
            

            //SPOSTIAMO LA COLLISION AREA PER RENDERE L'ATTACCO
            switch (gp.giocatore.direzione) {
                case "up":gp.giocatore.worldY-= gp.giocatore.attackArea.height;break;
                case "down":gp.giocatore.worldY+= gp.giocatore.attackArea.height;break;
                case "left":gp.giocatore.worldX-= gp.giocatore.attackArea.width;break;
                case "right":gp.giocatore.worldX+= gp.giocatore.attackArea.width;break;
            }

            //ATTACK DIVENTA COLLISION
            gp.giocatore.collArea.width = gp.giocatore.attackArea.width;
            gp.giocatore.collArea.height = gp.giocatore.attackArea.height;

            //CHECK COLLISION
            int monVerifier = gp.CollisionManager.checkEntity(gp.giocatore, gp.mon);
            checkAttack(monVerifier);

            //RITORNO AI VALORI STANDARD
            gp.giocatore.worldX=currentWorldX;
            gp.giocatore.worldY=currentWorldY;
            gp.giocatore.collArea.width=solidAreaWidth;
            gp.giocatore.collArea.height=solidAreaHeight;

        }
    }

    public void attackAnimationRoller(){
        gp.giocatore.spriteCount++;
        if(gp.giocatore.spriteNum>=gp.giocatore.AttackDown.length){
            gp.giocatore.spriteNum=0;
        }

        if(gp.giocatore.spriteCount==3){
            gp.giocatore.spriteNum++;
            if(gp.giocatore.spriteNum==gp.giocatore.AttackDown.length){
                gp.giocatore.spriteNum=0;
            }

            gp.giocatore.spriteCount=0;
        }
        
    }

    public void pickUpObj(int i) {
        if (i == Integer.MAX_VALUE) {
            return;
        }

        GameEntity obj = gp.obj[i];
        if (obj == null || gp.eventHandler.currentMapIndex != obj.mapVerifier) {
            return;
        }

        String objName = obj.name;

        switch (objName) {
            case "Key":
                handleKeyPickup(i);
                break;
            case "Door_Right":
            case "Door_Left":
                handleDoorUnlock(i);
                break;
            case "GoldCoin":
                handleGoldCoinPickup(i);
                break;
            case "Shoes":
                handleShoesPickup(i);
                break;
            case "BigTreasure":
                handleBigTreasurePickup(i);
                break;
            case "Heart":
                handleHeartPickup(i);
                break;
                case "BigHeart":
                handleBigHeartPickup(i);
                break;
        }
    }

    private void handleKeyPickup(int i) {
        gp.obj[i] = null;
        gp.ui.currentDialogue = "Hai ottenute la chiave, ora potrai accedere al tesoro\n della cripta";
        gp.ui.dialogueChoice1 = "";
        gp.ui.dialogueChoice2 = "";
        gp.ui.dialogueChoice = 3;
        gp.gameState=gp.dialogueState;
        gp.playSFX(6);
        gp.giocatore.numKeys++;
    }

    private void handleDoorUnlock(int i) {
        if (gp.giocatore.numKeys > 0) {
            gp.obj[i] = gp.assetPlacer.createObject("void");
            gp.ui.showMessage("Hai sbloccato una porta!");
            gp.giocatore.numKeys--;
            gp.playSFX(6);
        } else {
            gp.ui.showMessage2("Niente chiave? Peccato...");
        }
    }

    private void handleGoldCoinPickup(int i) {
        gp.obj[i] = gp.assetPlacer.createObject("void");
        gp.giocatore.rich = true;
        gp.ui.currentDialogue = "Hai ottenute una moneta, ora sei DAVVERO ricco";
        gp.ui.dialogueChoice1 = "";
        gp.ui.dialogueChoice2 = "";
        gp.ui.dialogueChoice = 3;
        gp.gameState=gp.dialogueState;
        gp.playSFX(6);
    }

    private void handleShoesPickup(int i) {
        gp.obj[i] = gp.assetPlacer.createObject("void");
        gp.giocatore.speedUp = true;
        gp.ui.currentDialogue = "Hai ottenute le scarpe, ora puoi correre \npremendo O";
        gp.ui.dialogueChoice1 = "";
        gp.ui.dialogueChoice2 = "";
        gp.ui.dialogueChoice = 3;
        gp.gameState=gp.dialogueState;
        gp.playSFX(6);
    }

    private void handleBigTreasurePickup(int i) {
        gp.stopMusic(gp.eventHandler.currentMapIndex);
        gp.playMusic(12);
      
        gp.gameState = gp.endGame;
    }
    private void handleHeartPickup(int i) {
        gp.obj[i] = gp.assetPlacer.createObject("void");
        gp.giocatore.vita = gp.giocatore.vitaMax;
        gp.ui.currentDialogue = "Una strana energia ti rinvigorisce, recuperi tutta la vita"; 
        gp.ui.dialogueChoice1 = "";
        gp.ui.dialogueChoice2 = "";
        gp.ui.dialogueChoice = 3;
        gp.gameState = gp.dialogueState;
        gp.ui.showMessage("Vitalità ripristinata");
        gp.playSFX(6);

    }

    private void handleBigHeartPickup(int i) {
        gp.obj[i] = gp.assetPlacer.createObject("void");
        gp.giocatore.vitaMax += 4;
        gp.giocatore.vita = gp.giocatore.vitaMax;
        gp.ui.currentDialogue = "Hai ottenute un cuore, la tua vitalità aumenta \nesponenzialmente";
        gp.ui.dialogueChoice1 = "";
        gp.ui.dialogueChoice2 = "";
        gp.ui.dialogueChoice = 3;
        gp.gameState=gp.dialogueState;
        gp.ui.showMessage("Vitalità aumentata");
        gp.playSFX(6);

    }

    public void chooseSprite() {
        Player giocatore = gp.giocatore;
        KeyboardInput keyh = gp.keyh;
        String direzione = giocatore.direzione;
        int spriteNum = giocatore.spriteNum;
        
        if (keyh.p) {
            attacking();
            switch (direzione) {
                case "up":
                    giocatore.image = giocatore.AttackUp[spriteNum];
                    break;
                case "down":
                    giocatore.image = giocatore.AttackDown[spriteNum];
                    break;
                case "right":
                    giocatore.image = giocatore.AttackRight[spriteNum];
                    break;
                case "left":
                    giocatore.image = giocatore.AttackLeft[spriteNum];
                    break;
            }
        } else {
            switch (direzione) {
                case "up":
                    giocatore.image = keyh.w ? giocatore.MoveUpAnimation[spriteNum] : giocatore.UpAnimation[spriteNum];
                    break;
                case "down":
                    giocatore.image = keyh.s ? giocatore.MoveDownAnimation[spriteNum] : giocatore.DownAnimation[spriteNum];
                    break;
                case "right":
                    giocatore.image = keyh.d ? giocatore.MoveRightAnimation[spriteNum] : giocatore.RightAnimation[spriteNum];
                    break;
                case "left":
                    giocatore.image = keyh.a ? giocatore.MoveLeftAnimation[spriteNum] : giocatore.LeftAnimation[spriteNum];
                    break;
            }
        }
    }
    

    public void moveOBJChecker(){

        //GESTIONE TASTI/DIREZIONE
        if(gp.keyh.w==true||gp.keyh.a==true||gp.keyh.s==true||gp.keyh.d==true||gp.keyh.o==true){

            if(gp.keyh.s==true){
                gp.giocatore.direzione = "down";
            }
           
            if(gp.keyh.w==true){
                gp.giocatore.direzione="up";
            }
    
            if(gp.keyh.d==true){
                gp.giocatore.direzione="right";
            }
            
            if(gp.keyh.a==true){
                gp.giocatore.direzione="left";
            }

            //GESTIONE speedPLAYER
            if(
               (gp.keyh.w==true ||
                gp.keyh.a==true ||
                gp.keyh.s==true ||
                gp.keyh.d==true) && gp.keyh.o==true && gp.giocatore.speedUp==true ){
                  gp.giocatore.speed= 7 ;
             } else if(
                (gp.keyh.w==true ||
                 gp.keyh.a==true ||
                 gp.keyh.s==true ||
                 gp.keyh.d==true) && gp.keyh.o==true){
                    gp.giocatore.speed= 4;
                 }
                  else {gp.giocatore.speed= 0;
                    }
            
            if(gp.keyh.o==false){
                gp.giocatore.speed=4;
            }

            //CHECK TILE
            gp.giocatore.solid = false;
            gp.CollisionManager.checkTile(gp.giocatore);
            
            //CHECK OBJECT
            int objVerifier = gp.CollisionManager.checkObject(gp.giocatore,true);
            pickUpObj(objVerifier);

            //CHECK MONSTERS
            int monVerifier = gp.CollisionManager.checkEntity(gp.giocatore, gp.mon);
            contactMonster(monVerifier);
            

            gp.eventHandler.checkEvent();
            

            if (gp.giocatore.solid == false){
                switch(gp.giocatore.direzione){
                    case "up": gp.giocatore.worldY -= gp.giocatore.speed*gp.screenManager.scaleY; break;
                    case "down": gp.giocatore.worldY += gp.giocatore.speed*gp.screenManager.scaleY; break;
                    case "right": gp.giocatore.worldX += gp.giocatore.speed*gp.screenManager.scaleX; break;
                    case "left": gp.giocatore.worldX-= gp.giocatore.speed*gp.screenManager.scaleX; break;
                }
            }
        }

        gp.giocatore.invincible(gp.giocatore.invincibleTime);
    }

    public void contactMonster(int monVerifier){
        
        if(monVerifier!=Integer.MAX_VALUE){
            gp.ui.showMessage2("hai subito danni!");
        }
        if(monVerifier!=Integer.MAX_VALUE && gp.giocatore.invincible ==false){

            gp.giocatore.invincible=true;
            gp.giocatore.vita--;
            gp.giocatore.invincibleCounter--;
            gp.playSFX(13);
           

        }
    }
            
    

}
