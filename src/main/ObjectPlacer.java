package main;

import object.*;

public class ObjectPlacer {
	Pannello gp;
	public ObjectPlacer (Pannello gp){
		this.gp = gp;
	}
	public void setObject () {

		gp.obj[0] = new ObjShoes(gp);
		gp.obj[0].worldX = 26*gp.ingame_size;
		gp.obj[0].worldY = 26*gp.ingame_size; 
		

		gp.obj[1] = new ObjKey(gp);
		gp.obj[1].worldX = 23*gp.ingame_size;
		gp.obj[1].worldY = 24*gp.ingame_size; 
		
		gp.obj[2] = new ObjKey(gp);
		gp.obj[2].worldX = 28*gp.ingame_size;
		gp.obj[2].worldY = 24*gp.ingame_size; 

		gp.obj[3] = new Door_Left(gp);
		gp.obj[3].worldX = 5*gp.ingame_size;
		gp.obj[3].worldY = 25*gp.ingame_size;
		
		gp.obj[4] = new Door_Right(gp);
		gp.obj[4].worldX = 5*gp.ingame_size;
		gp.obj[4].worldY = 26*gp.ingame_size;
		
		gp.obj[5] = new BigTreasure(gp);
		gp.obj[5].worldX = 26*gp.ingame_size;
		gp.obj[5].worldY = 40*gp.ingame_size;


		gp.obj[6] = new Door_Right(gp);
		gp.obj[6].worldX = 26*gp.ingame_size;
		gp.obj[6].worldY = 37*gp.ingame_size;


		gp.obj[7] = new Door_Left(gp);
		gp.obj[7].worldX = 25*gp.ingame_size;
		gp.obj[7].worldY = 37*gp.ingame_size;

		gp.obj[8] = new ObjSpike(gp);
		gp.obj[8].worldX = 10*gp.ingame_size;
		gp.obj[8].worldY = 23*gp.ingame_size;

		gp.obj[9] = new GoldCoin(gp);
		gp.obj[9].worldX = 26*gp.ingame_size;
		gp.obj[9].worldY = 23*gp.ingame_size;
	
		gp.obj[10] = new ObjSpike(gp);
		gp.obj[10].worldX = 23*gp.ingame_size;
		gp.obj[10].worldY = 23*gp.ingame_size;

	}
}