package main;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;

import Player.Giocatore;


public class UI {

    Pannello gp;
    Font arial_30;

    public UI (Pannello gp) {

        this.gp = gp;
       arial_30 = new Font ("Arial", Font.BOLD,30);

    }

    public void draw (Graphics2D g2) {

        g2.setFont(arial_30);
        g2.setColor(Color.red);
        g2.drawString("Key = "+Giocatore.numKeys, 50, 50);
    }
}
