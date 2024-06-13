package Entità;

import main.Pannello;

public class Mon_Bat extends Entità{

    public Mon_Bat(Pannello gp){
        super(gp);

        name = "Bat";
        type=2;
        velocità=2;
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

        MoveUpAnimation = loadAnimation(3, "Monsters/MonsterSprites/Bat/Up");
        MoveDownAnimation = loadAnimation(3, "Monsters/MonsterSprites/Bat/Down");
        MoveLeftAnimation = loadAnimation(3, "Monsters/MonsterSprites/Bat/Left");
        MoveRightAnimation = loadAnimation(3, "Monsters/MonsterSprites/Bat/Right");

    }

    public void update(){
        
        setAction();
        spriteCount++;
        if(spriteCount>5){
            if(spriteNum==0){
                spriteNum=1;
            }else if (spriteNum==1){
                spriteNum=2;
            }else if (spriteNum==2){
                spriteNum=0;
            }
            spriteCount=0;
        }
        movement();
        System.out.println(spriteNum);
        invincible(invincibleTime);
        
    }
    
}
