package Entità;

import main.InputTastiera;
import main.Pannello;


public class PlayerTools {

    Pannello gp;

    public PlayerTools(Pannello gp){
        this.gp=gp;
    }

    public void interactMonster(int monVerifier){
        if(monVerifier!=999){
            System.out.println(" you got hit");
        }
    }

    public void checkAttack(int i){
        if(i!=999 && gp.mon[i].invincible == false){
            gp.mon[i].vita--;
            gp.mon[i].invincible=true;
            if(gp.mon[i].vita <=0){
                gp.mon[i]=null;
            }
        }
         
    }

    public void attacking(){
        if(gp.giocatore.spriteNum>=2 && gp.giocatore.spriteNum<=4){
            
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

    public void pickUpObj(int i) {
        if (i == 999) {
            return;
        }

        Entità obj = gp.obj[i];
        if (obj == null || gp.eventHandler.currentMap != obj.mapVerifier) {
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
        }
    }

    private void handleKeyPickup(int i) {
        gp.obj[i] = null;
        gp.ui.showMessage("You got a key! That's cool.");
        gp.playSFX(5);
        gp.giocatore.numKeys++;
    }

    private void handleDoorUnlock(int i) {
        if (gp.giocatore.numKeys > 0) {
            gp.obj[i] = null;
            gp.ui.showMessage("You unlocked a door! Let's go!");
            gp.giocatore.numKeys--;
            gp.playSFX(5);
        } else {
            gp.ui.showMessage2("No keys? So lame...");
        }
    }

    private void handleGoldCoinPickup(int i) {
        gp.obj[i] = null;
        gp.ui.showMessage2("Richer!");
        gp.playSFX(5);
    }

    private void handleShoesPickup(int i) {
        gp.obj[i] = null;
        gp.giocatore.speedUp = true;
        gp.ui.showMessage("Press O to run.");
        gp.playSFX(5);
    }

    private void handleBigTreasurePickup(int i) {
        gp.obj[i] = null;
        gp.playSFX(5);
        gp.stopMusic(0);
        gp.ui.endGame = true;
    }


    public void printSprite() {
        Giocatore giocatore = gp.giocatore;
        InputTastiera keyh = gp.keyh;
        String direzione = giocatore.direzione;
        int spriteNum = giocatore.spriteNum;
        
        if (keyh.p) {
            gp.giocatore.attacking=true;
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

            //GESTIONE VELOCITà PLAYER
            if(
               (gp.keyh.w==true ||
                gp.keyh.a==true ||
                gp.keyh.s==true ||
                gp.keyh.d==true) && gp.keyh.o==true && gp.giocatore.speedUp==true ){
                  gp.giocatore.velocità = 5 ;
             } else if(
                (gp.keyh.w==true ||
                 gp.keyh.a==true ||
                 gp.keyh.s==true ||
                 gp.keyh.d==true) && gp.keyh.o==true){
                    gp.giocatore.velocità = 3;
                 }
                  else {gp.giocatore.velocità = 0;
                    }
            
            if(gp.keyh.o==false){
                gp.giocatore.velocità=3;
            }

            //CHECK TILE
            gp.giocatore.solid = false;
            gp.CollisionManager.checkTile(gp.giocatore);
            
            //CHECK OBJECT
            int objVerifier = gp.CollisionManager.checkObject(gp.giocatore,true);
            pickUpObj(objVerifier);

            //CHECK MONSTERS
            int monVerifier = gp.CollisionManager.checkEntity(gp.giocatore, gp.mon);
            interactMonster(monVerifier);
            contactMonster(monVerifier);
            

            gp.eventHandler.checkEvent();
            

            if (gp.giocatore.solid == false){
                switch(gp.giocatore.direzione){
                    case "up": gp.giocatore.worldY -= gp.giocatore.velocità*gp.screenManager.scaleY; break;
                    case "down": gp.giocatore.worldY += gp.giocatore.velocità*gp.screenManager.scaleY; break;
                    case "right": gp.giocatore.worldX += gp.giocatore.velocità*gp.screenManager.scaleX; break;
                    case "left": gp.giocatore.worldX-= gp.giocatore.velocità*gp.screenManager.scaleX; break;
                }
            }
        }

        gp.giocatore.invincible(gp.giocatore.invincibleTime);
    }

    public void contactMonster(int monVerifier){
        
        if(monVerifier!=999 && gp.giocatore.invincible ==false){
            
            gp.giocatore.invincible=true;
            gp.giocatore.vita--;
            gp.giocatore.invincibleCounter--;

        }
    }
            
    

}
