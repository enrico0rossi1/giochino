package main;

import Personaggi.NPC;
import Personaggi.SquaredCat;
import object.*;

public class AssetPlacer {
	Pannello gp;
	public AssetPlacer (Pannello gp){
		this.gp = gp;
	}

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
	
	private GameObject createObject(String objectName) {
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


	private NPC createNPC(String npcName) {	
		switch (npcName) {
			case ("SquaredCat"):
               return new SquaredCat(gp);
			   case("SqrdCat"):
			   return new SquaredCat(gp);
		   default:
		return null;
		
		}

	}

	public void setNPC(String npcName, int x, int y, int times, int mapVerifier) {
		for (int i = 0; i < gp.npc.length; i++) {
			if (gp.npc[i] == null && times !=0) {
				gp.npc[i] = createNPC(npcName);
				if (gp.npc[i] != null) {
					gp.npc[i].posizioneX = x * gp.ingame_size;
					gp.npc[i].posizioneY = y * gp.ingame_size;
					gp.npc[i].mapVerifier = mapVerifier;
					times--;
				}
			}
		}
	}
	public void placeNPC(){
		setNPC("SquaredCat", 25, 26, 1, 0);
		setNPC("SquaredCat", 23, 26, 1, 0);
		setNPC("SquaredCat", 23, 30, 1, 0);
		setNPC("SquaredCat", 26, 24, 1, 0);

	}
}