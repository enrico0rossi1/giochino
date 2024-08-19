package warrioradventure;

import java.util.Random;

import entities.*;
import entities.gameobjects.*;
import entities.Monsters.*;


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

	public GameEntity[] removeObject(String obj){
		
		for (int i=0; i<gp.obj.length; i++ ){
			if(gp.obj[i]!= null && gp.obj[i].name == obj){
				gp.obj[i]=null;
			}
		}
		return gp.obj;
	}
	
	
	public GameEntity createObject(String objectName) {
		switch (objectName) {
			case "Shoes":
				return new ObjShoes(gp);
			case "Key":
				return new ObjKey(gp);
			case "Door_Right":
				return new ObjDoorRight(gp);
			case "Door_Left":
				return new ObjDoorLeft(gp);
			case "BigTreasure":
				return new ObjBigTreasure(gp);
			case "GoldCoin":
				return new ObjCoin(gp);
			case "Heart":
				return new ObjHeart(gp);
			case "BigHeart":
				return new ObjBigHeart(gp);
			case "void":
				return new ObjVoid(gp);
			default:
				return null;
		}
	}
	
	public void placeObject () {
		// mappa StartingWoods
		setObject("BigTreasure", 26, 42, 1,gp.eventHandler.startingWoodsMap);
		setObject("Heart", 22, 23, 1,gp.eventHandler.startingWoodsMap);
		setObject("Heart", 22, 27, 1,gp.eventHandler.startingWoodsMap);
		setObject("Heart", 29, 23, 1,gp.eventHandler.startingWoodsMap);
		setObject("Door_Left", 25, 38, 1,gp.eventHandler.startingWoodsMap);
		setObject("Door_Right",26,38,1,gp.eventHandler.startingWoodsMap);
		
		// mappa DarkWoods
		setObject("Shoes",33,19,1,gp.eventHandler.darkWoodsMap);
		//mappa Jungle
		setObject("GoldCoin", 33, 19, 1,gp.eventHandler.jungleMap);
		//mappa Beach
		setObject("BigHeart", 33, 19, 1,gp.eventHandler.beachMap);
	}

	public void restartPlaceObject(){
		for (int i=0; i<gp.progressObj.length;i++){
			if(gp.progressObj[i]!=null && gp.obj[i]!=null){
				if(gp.obj[i].name=="void"){
					gp.obj[i]=gp.progressObj[i];
				}
				
			}
		}
	}

	//GESTIONE NEMICI

	public void setEnemy(String enemyType, int times, int mapVerifier) {
		Random randomX= new Random();
		Random randomY= new Random();
		int x = randomX.nextInt(18,33);
		int y =randomY.nextInt(19, 30);
		if(x==25){
			x=27;
			
		}else if(y==25){
			y=27;
		}
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
			case "Slime": return new MonSlime(gp);
			case "Bat":	return new MonBat(gp);
			case "Log": return new MonLog(gp);

			default:
				return null;
		}
	}

	public void placeEnemy () {
		

		setEnemy("Bat", 1, gp.eventHandler.darkWoodsMap);
		setEnemy("Bat", 1, gp.eventHandler.darkWoodsMap);
		setEnemy("Bat", 1, gp.eventHandler.darkWoodsMap);
		setEnemy("Bat", 1, gp.eventHandler.darkWoodsMap);
		setEnemy("Bat", 1, gp.eventHandler.darkWoodsMap);
		setEnemy("Bat", 1, gp.eventHandler.darkWoodsMap);

		setEnemy("Log", 1, gp.eventHandler.jungleMap);
		setEnemy("Log", 1, gp.eventHandler.jungleMap);
		setEnemy("Log", 1, gp.eventHandler.jungleMap);
		setEnemy("Log", 1, gp.eventHandler.jungleMap);
		setEnemy("Log", 1, gp.eventHandler.jungleMap);
		setEnemy("Log", 1, gp.eventHandler.jungleMap);

		setEnemy("Slime", 1, gp.eventHandler.beachMap);
		setEnemy("Slime", 1, gp.eventHandler.beachMap);
		setEnemy("Slime", 1, gp.eventHandler.beachMap);
		setEnemy("Slime", 1, gp.eventHandler.beachMap);
		setEnemy("Slime", 1, gp.eventHandler.beachMap);
		setEnemy("Slime", 1, gp.eventHandler.beachMap);
	}

	public void restartPlaceEnemies(){
		System.out.println("restart mostri chiamato");
		for (int i=0; i<gp.progressMon.length;i++){
			if(gp.progressMon[i]!= null && gp.mon[i]==null){
				gp.mon[i]=gp.progressMon[i];
			}
			if(gp.mon[i]!=null){
				gp.mon[i].vita=gp.mon[i].vitaMax;
			}
		}
	}


}