package gameworld;

public class MapMemory {

    public Map[]mapHandler=new Map[6];

    public MapMemory(){
        
    }

    public void loadToMapMemory (Map map){

        int counter=1;
        for (int i=0; i<mapHandler.length; i++){
            if (mapHandler[i]==null && counter==1){
                mapHandler[i]=map;
                counter--;
            }
        }

    }
    
}
