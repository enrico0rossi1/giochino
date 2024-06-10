package Monsters;

import java.awt.image.BufferedImage;

import Personaggi.Entità;
import main.Pannello;

public class Mon_sqrdCat extends Entità {

    MonsterTool mTool;

    public Mon_sqrdCat(Pannello gp){
        super(gp);
        mTool = new MonsterTool(gp);
        name = "Squared_cat";
        velocità=1;
        vitaMax=4;
        vita=vitaMax;

        solid=true;
        collArea.x=3;
        collArea.y=6;
        collArea.width=42;
        collArea.height=42;
        solidAreaDefaultX=collArea.x;
        solidAreaDefaultY=collArea.y;

        getImage();
    }
    
    public void getImage(){
        BufferedImage handle[]=new BufferedImage[6];
        handle= loadAnimation(1, "Sprites/Down");
        image = handle[0];
    }

    public void update(){
        mTool.setAction();
        mTool.movement();
    }

}
