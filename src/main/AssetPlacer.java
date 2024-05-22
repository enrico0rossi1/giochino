package main;

import object.*;

public class AssetPlacer {
	Pannello gp;
	public AssetPlacer (Pannello gp){
		this.gp = gp;
	}

	public void setObject(String objectName,int x,int y, int times){

		switch (objectName) {
			case "Shoes":
				for(int i=0; i<gp.obj.length; i++){
					if(gp.obj[i]==null && times>0){
						gp.obj[i]=new ObjShoes(gp);
						gp.obj[i].worldX = x*gp.ingame_size;
						gp.obj[i].worldY = y*gp.ingame_size;
						times--;
					}
				}
				
			break;

			case "Key":
				for(int i=0; i<gp.obj.length; i++){
					if(gp.obj[i]==null && times>0){
						gp.obj[i]=new ObjKey(gp);
						gp.obj[i].worldX = x*gp.ingame_size;
						gp.obj[i].worldY = y*gp.ingame_size;
						times--;
					}
				}
			break;

			case "Door_Right":
				for(int i=0; i<gp.obj.length; i++){
					if(gp.obj[i]==null && times>0){
						gp.obj[i]=new Door_Right(gp);
						gp.obj[i].worldX = x*gp.ingame_size;
						gp.obj[i].worldY = y*gp.ingame_size;
						times--;
					}
				}
			break;

			case "Door_Left":
				for(int i=0; i<gp.obj.length; i++){
					if(gp.obj[i]==null && times>0){
						gp.obj[i]=new Door_Left(gp);
						gp.obj[i].worldX = x*gp.ingame_size;
						gp.obj[i].worldY = y*gp.ingame_size;
						times--;
					}
				}
			break;

			case "BigTreasure":
				for(int i=0; i<gp.obj.length; i++){
					if(gp.obj[i]==null && times>0){
						gp.obj[i]=new BigTreasure(gp);
						gp.obj[i].worldX = x*gp.ingame_size;
						gp.obj[i].worldY = y*gp.ingame_size;
						times--;
					}
				}
			break;

			case "Spike":
				for(int i=0; i<gp.obj.length; i++){
					if(gp.obj[i]==null && times>0){
						gp.obj[i]=new ObjSpike(gp);
						gp.obj[i].worldX = x*gp.ingame_size;
						gp.obj[i].worldY = y*gp.ingame_size;
						times--;
					}
				}
			break;

			case "GoldCoin":
				for(int i=0; i<gp.obj.length; i++){
					if(gp.obj[i]==null && times>0){
						gp.obj[i]=new GoldCoin(gp);
						gp.obj[i].worldX = x*gp.ingame_size;
						gp.obj[i].worldY = y*gp.ingame_size;
						times--;
					}
				}
			break;
		}
			
		
		 
	}


	public void placeObject () {

		setObject("Shoes",26,26,1);
		setObject("Key",23,24,1);
		setObject("Key",28,24,1);
		setObject("Door_Right", 5, 26, 1);
		setObject("Door_Left", 5, 25, 1);
		setObject("Door_Right", 26, 37, 1);
		setObject("Door_Left", 25, 37, 1);
		setObject("BigTreasure", 26, 40, 1);
		setObject("Spike", 10, 23, 1);
		setObject("Spike", 23, 23, 1);
		setObject("GoldCoin", 26, 23, 1);
	
	}
}