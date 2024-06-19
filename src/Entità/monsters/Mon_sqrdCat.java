package entities.monsters;

import entities.GameEntity;
import main.GamePanel;

public class Mon_sqrdCat extends GameEntity {

    public Mon_sqrdCat(GamePanel gp){
        super(gp);
        name = "Squared_cat";
        type=2;
        speed=1;
        vitaMax=4;
        vita=vitaMax;
        invincibleTime=20;
       


        collArea.x=0;
        collArea.y=0;
        collArea.width=gp.ingame_size;
        collArea.height=gp.ingame_size;
        solidAreaDefaultX=collArea.x;
        solidAreaDefaultY=collArea.y;

        getImage();
    }
    
    public void getImage(){
        MoveUpAnimation = loadAnimation(1, " MonsterSprites/Squared_Cat/Up");
        MoveDownAnimation = loadAnimation(1, "MonsterSprites/Squared_Cat/Down");
        MoveLeftAnimation = loadAnimation(1, "MonsterSprites/Squared_Cat/Left");
        MoveRightAnimation = loadAnimation(1, "MonsterSprites/Squared_Cat/Right");
        DeathAnimation = loadAnimation(1, "MonsterSprites/Squared_Cat/Death");
    }

    public void update(){
        
        checkStatus();
        setAction();
        movement();
        invincible(invincibleTime);
        
    }

}
