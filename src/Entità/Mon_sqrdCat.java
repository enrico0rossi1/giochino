package Entità;

import main.Pannello;

public class Mon_sqrdCat extends Entità {

    public Mon_sqrdCat(Pannello gp){
        super(gp);
        name = "Squared_cat";
        type=2;
        velocità=0;
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
        
        setAction();
        movement();
        invincible(invincibleTime);
        
    }

}
