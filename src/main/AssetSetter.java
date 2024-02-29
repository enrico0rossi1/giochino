package main;

import object.*;

public class AssetSetter {
	Pannello gp;
	public AssetSetter (Pannello gp){
		this.gp = gp;
	}
	public void setObject () {

		gp.obj[0] = new GoldCoin();
		gp.obj[0].worldX = 12*gp.ingame_size;
		gp.obj[0].worldY = 17*gp.ingame_size; 
		

		gp.obj[1] = new ObjKey();
		gp.obj[1].worldX = 38*gp.ingame_size;
		gp.obj[1].worldY = 27*gp.ingame_size; 
		

		gp.obj[2] = new BigTreasure();
		gp.obj[2].worldX = 41*gp.ingame_size;
		gp.obj[2].worldY = 15*gp.ingame_size; 
		
	}
}