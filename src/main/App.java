package main;

import javax.swing.JFrame;


public class App {
    public static void main(String[] args) throws Exception {
        JFrame finestra = new JFrame();
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setResizable(false);
        finestra.setTitle("giochino");

        Pannello pannello1= new Pannello();
        finestra.add(pannello1);
        finestra.pack();
        finestra.setLocationRelativeTo(null);
        finestra.setVisible(true);

        pannello1.startThreadGioco();

        
        
        
    }
}