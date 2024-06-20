package main;

import entities.*;
import entities.gameobjects.*;
import entities.Mon_Bat;
import entities.Mon_Log;
import entities.Mon_sqrdCat;

public class AssetPlacer {
	GamePanel gp;

	public AssetPlacer (GamePanel gp){
		this.gp = gp;
		
	}

	//GESTIONE OGGETTI

	public void setObject(String objectName, int x, int y, int times, int mapVerifier) {
		for (int i = 0; i < gp.obj.length && times > 0; i++) {
			if (gp.obj[i] == null) {
				gp.obj[i] = createObject(objectName);
				gp.progressObj[i]= createObject(objectName);
				if (gp.obj[i] != null) {
					gp.obj[i].worldX = x * gp.ingame_size;
					gp.obj[i].worldY = y * gp.ingame_size;
					gp.obj[i].mapVerifier = mapVerifier;
					gp.progressObj[i].worldX = x * gp.ingame_size;
					gp.progressObj[i].worldY = y * gp.ingame_size;
					gp.progressObj[i].mapVerifier = mapVerifier;
					times--;
				}
			}
		}
	}
	
	private GameEntity createObject(String objectName) {
		switch (objectName) {
			case "Shoes":
				return new ObjShoes(gp);
			case "Key":
				return new ObjKey(gp);
			case "Door_Right":
				return new Door_Right(gp);
			case "Door_Left":
				return new Door_Left(gp);
			case "BigTreasure":
				return new BigTreasure(gp);
			case "GoldCoin":
				return new ObjCoin(gp);
			case "Heart":
				return new ObjHeart(gp);
			case "BigHeart":
				return new ObjBigHeart(gp);
			default:
				return null;
		}
	}
	

	
	public void placeObject () {
		// mappa StartingWoods
		setObject("Door_Right", 26, 37, 1,0);
		setObject("Door_Left", 25, 37, 1,0);
		setObject("BigTreasure", 26, 40, 1,0);
		setObject("Heart", 22, 23, 1,0);
		setObject("Heart", 22, 27, 1,0);
		setObject("Heart", 29, 23, 1,0);
		setObject("Shoes",26,26,1,0);
		
		// mappa DarkWoods
		setObject("Shoes",34,18,1,1);
		//mappa Jungle
		setObject("GoldCoin", 33, 20, 1,2);
		//mappa Beach
		setObject("BigHeart", 32, 17, 1,3);
	}

	public void restartPlaceObject(){
		for (int i=0; i<gp.progressObj.length;i++){
			if(gp.progressObj[i]!= null && gp.obj[i]==null){
				gp.obj[i]=gp.progressObj[i];
			}
		}
	}

	//GESTIONE NEMICI

	public void setEnemy(String enemyType, int x, int y, int times, int mapVerifier) {
		for (int i = 0; i < gp.mon.length && times > 0; i++) {
			if (gp.mon[i] == null) {
				gp.mon[i] = createEnemy(enemyType);
				gp.progressMon[i] = createEnemy(enemyType);
				
				if (gp.mon[i] != null) {
					gp.mon[i].worldX = x * gp.ingame_size;
					gp.mon[i].worldY = y * gp.ingame_size;
					gp.mon[i].mapVerifier = mapVerifier;
					gp.progressMon[i].worldX = x * gp.ingame_size;
					gp.progressMon[i].worldY = y * gp.ingame_size;
					gp.progressMon[i].mapVerifier = mapVerifier;
					times--;
				}
			}
		}
	}

	private GameEntity createEnemy(String enemyType) {
		switch (enemyType) {
			case "Squared_cat":	return new Mon_sqrdCat(gp);
			case "Bat":	return new Mon_Bat(gp);
			case "Log": return new Mon_Log(gp);
			default:
				return null;
		}
	}

	public void placeEnemy () {
		

		setEnemy("Bat", 23, 18, 1, 1);
		setEnemy("Bat", 30, 27, 1, 1);
		setEnemy("Bat", 18, 32, 1, 1);
		setEnemy("Bat", 29, 29, 1, 1);

		setEnemy("Log", 23, 25, 1, 2);
		setEnemy("Log", 24, 27, 1, 2);
		setEnemy("Log", 26, 22, 1, 2);
		setEnemy("Log", 29, 29, 1, 2);

		//setEnemy("Squared_cat", 24, 30, 1, gp.eventHandler.beachMap);


	}

	public void restartPlaceEnemies(){

		for (int i=0; i<gp.progressMon.length;i++){
			if(gp.progressMon[i]!= null && gp.mon[i]==null){
				gp.mon[i]=gp.progressMon[i];
			}
		}
	}


}