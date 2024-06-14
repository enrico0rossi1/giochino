package main;

import javax.swing.JFrame;


public class App {

    public static JFrame finestra;
    public static void main(String[] args) throws Exception {
       
       
        finestra = new JFrame();
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setResizable(false);
        finestra.setTitle("Warrior Adventure");
        finestra.setUndecorated(false);
        Pannello pannello1 = new Pannello();
        finestra.add(pannello1);
        finestra.pack();
        finestra.setLocationRelativeTo(null);
        finestra.setVisible(true);
      
        pannello1.setUpGioco();
        pannello1.startThreadGioco();
        
       

        
        
    }
}