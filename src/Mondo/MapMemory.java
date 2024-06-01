package Mondo;

public class MapMemory {

    public Mappa[]mapHandler=new Mappa[6];

    public MapMemory(){
        
    }

    public void loadToMapMemory (Mappa map){

        int counter=1;
        for (int i=0; i<mapHandler.length; i++){
            if (mapHandler[i]==null && counter==1){
                mapHandler[i]=map;
                counter--;
            }
        }

    }
    
}
