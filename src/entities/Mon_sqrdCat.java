package entities;

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
        MoveUpAnimation = loadAnimation(1, "Monsters/Sprites/Up");
        MoveDownAnimation = loadAnimation(1, "Monsters/Sprites/Down");
        MoveLeftAnimation = loadAnimation(1, "Monsters/Sprites/Left");
        MoveRightAnimation = loadAnimation(1, "Monsters/Sprites/Right");
       
    }

    public void update(){
        
        checkStatus();
        setAction();
        movement();
        invincible(invincibleTime);
        
    }

}
