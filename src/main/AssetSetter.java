package main;

import object.*;

public class AssetSetter {
	Pannello gp;
	public AssetSetter (Pannello gp){
		this.gp = gp;
	}
	public void setObject () {

		gp.obj[0] = new GoldCoin();
		gp.obj[0].worldX = 21*gp.ingame_size;
		gp.obj[0].worldY = 21*gp.ingame_size; 
		

		gp.obj[1] = new ObjKey();
		gp.obj[1].worldX = 23*gp.ingame_size;
		gp.obj[1].worldY = 24*gp.ingame_size; 
		

		gp.obj[2] = new Door_Left();
		gp.obj[2].worldX = 5*gp.ingame_size;
		gp.obj[2].worldY = 25*gp.ingame_size;
		
		gp.obj[3] = new Door_Right();
		gp.obj[3].worldX = 5*gp.ingame_size;
		gp.obj[3].worldY = 26*gp.ingame_size;
		
	}
}