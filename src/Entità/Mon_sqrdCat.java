package Entità;

import main.Pannello;

public class Mon_sqrdCat extends Entità {

    public Mon_sqrdCat(Pannello gp){
        super(gp);
        name = "Squared_cat";
        velocità=2;
        vitaMax=4;
        vita=vitaMax;

        collArea.x=gp.giocatore.collArea.x;
        collArea.y=gp.giocatore.collArea.y;
        collArea.width=gp.giocatore.collArea.width;;
        collArea.height=gp.giocatore.collArea.height;
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
        
    }

}
