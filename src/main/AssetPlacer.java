package main;

import Entità.Entità;
import Entità.Mon_sqrdCat;
import object.*;

public class AssetPlacer {
	Pannello gp;

	public AssetPlacer (Pannello gp){
		this.gp = gp;
		
	}

	//GESTIONE OGGETTI

	public void setObject(String objectName, int x, int y, int times, int mapVerifier) {
		for (int i = 0; i < gp.obj.length && times > 0; i++) {
			if (gp.obj[i] == null) {
				gp.obj[i] = createObject(objectName);
				if (gp.obj[i] != null) {
					gp.obj[i].worldX = x * gp.ingame_size;
					gp.obj[i].worldY = y * gp.ingame_size;
					gp.obj[i].mapVerifier = mapVerifier;
					times--;
				}
			}
		}
	}
	
	private Entità createObject(String objectName) {
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
			case "Spike":
				return new ObjSpike(gp);
			case "GoldCoin":
				return new GoldCoin(gp);
			default:
				return null;
		}
	}
	

	
	public void placeObject () {
		setObject("Shoes",26,26,1,0);
		setObject("Key",23,24,1,0);
		setObject("Key",28,24,1,0);
		setObject("Door_Right", 5, 26, 1,0);
		setObject("Door_Left", 5, 25, 1,0);
		setObject("Door_Right", 26, 37, 1,0);
		setObject("Door_Left", 25, 37, 1,0);
		setObject("BigTreasure", 26, 40, 1,0);
		setObject("Spike", 10, 23, 1,0);
		setObject("Spike", 23, 23, 1,0);
		setObject("GoldCoin", 26, 23, 1,0);
		
		setObject("Shoes",26,26,1,1);
		setObject("Key",25,30,1,1);
		setObject("GoldCoin", 26, 23, 1,1);
	}

	//GESTIONE NEMICI

	public void setEnemy(String enemyType, int x, int y, int times, int mapVerifier) {
		for (int i = 0; i < gp.mon.length && times > 0; i++) {
			if (gp.mon[i] == null) {
				gp.mon[i] = createEnemy(enemyType);
				if (gp.mon[i] != null) {
					gp.mon[i].worldX = x * gp.ingame_size;
					gp.mon[i].worldY = y * gp.ingame_size;
					gp.mon[i].mapVerifier = mapVerifier;
					times--;
				}
			}
		}
	}

	private Entità createEnemy(String enemyType) {
		switch (enemyType) {
			case "Squared_cat":
				return new Mon_sqrdCat(gp);
			default:
				return null;
		}
	}

	public void placeEnemy () {
		setEnemy("Squared_cat", 23, 25, 1, 1);
		setEnemy("Squared_cat", 24, 27, 1, 1);
		setEnemy("Squared_cat", 26, 30, 1, 1);
		setEnemy("Squared_cat", 29, 29, 1, 1);
	}


}