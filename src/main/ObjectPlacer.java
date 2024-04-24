package main;

import object.*;

public class ObjectPlacer {
	Pannello gp;
	public ObjectPlacer (Pannello gp){
		this.gp = gp;
	}
	public void setObject () {

		gp.obj[0] = new GoldCoin();
		gp.obj[0].worldX = 26*gp.ingame_size;
		gp.obj[0].worldY = 26*gp.ingame_size; 
		

		gp.obj[1] = new ObjKey();
		gp.obj[1].worldX = 23*gp.ingame_size;
		gp.obj[1].worldY = 24*gp.ingame_size; 
		
		gp.obj[7] = new ObjKey();
		gp.obj[7].worldX = 28*gp.ingame_size;
		gp.obj[7].worldY = 24*gp.ingame_size; 

		gp.obj[2] = new Door_Left();
		gp.obj[2].worldX = 5*gp.ingame_size;
		gp.obj[2].worldY = 25*gp.ingame_size;
		
		gp.obj[3] = new Door_Right();
		gp.obj[3].worldX = 5*gp.ingame_size;
		gp.obj[3].worldY = 26*gp.ingame_size;
		
		gp.obj[4] = new BigTreasure();
		gp.obj[4].worldX = 26*gp.ingame_size;
		gp.obj[4].worldY = 45*gp.ingame_size;


		gp.obj[5] = new Door_Right();
		gp.obj[5].worldX = 30*gp.ingame_size;
		gp.obj[5].worldY = 26*gp.ingame_size;


		gp.obj[6] = new Door_Right();
		gp.obj[6].worldX = 32*gp.ingame_size;
		gp.obj[6].worldY = 22*gp.ingame_size;
	}
}