package warrioradventure;

import javax.swing.JFrame;


public class WarriorAdventureGame {

    public static JFrame window;
    public static void main(String[] args) throws Exception {
       
       
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Warrior Adventure");
        window.setUndecorated(false);
        GamePanel panel = new GamePanel();   
        panel.setUpGioco();
        panel.startGameTimer();
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
      
     
        
       

        
        
    }
}