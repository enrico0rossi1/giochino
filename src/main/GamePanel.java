package main;

import javax.swing.*;

import audio.Sound;
import entities.*;
import gameworld.Map;
import gameworld.MapMemory;
import gameworld.TileManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    public final int character_size = 16;
    public final int scale = 3;
    public final int ingame_size = character_size * scale; // 48
    public final int pix_row = 12;  // for full screen 20
    public final int pix_cols = 20; // for full screen 40
    public final int screen_width = ingame_size * pix_cols;
    public final int screen_height = ingame_size * pix_row;

    // Impostazioni di mappa
    public final int worldCol = 50, worldRow = 50;
    public final int worldWidth = ingame_size * worldCol;
    public final int worldHeight = ingame_size * worldRow;

    // Impostazioni Schermo Intero
    int fullScreen_width = screen_width;
    int fullScreen_height = screen_height;
    BufferedImage gameScreen;
    public Graphics2D graphics2;
    public Graphics2D graphics3;
    public boolean fullScreenOn = false;

    Timer gameTimer;

    public KeyboardInput keyh = new KeyboardInput(this);
    public PlayerTools pTools = new PlayerTools(this);
    public Map start = new Map(this, "maps/StartingWoods", "maps/StartingWoodsDeco"); // mappa n 0
    public Map dungeon1 = new Map(this, "maps/DarkWoods", "maps/DarkWoodsDeco"); // mappa n 1
    public Map dungeon2 = new Map(this, "maps/Jungle", "maps/JungleDeco"); // mappa n 2
    public Map dungeon3 = new Map(this, "maps/Beach", "maps/BeachDeco"); // mappa n 3
    public MapMemory mapMemory = new MapMemory();
    public TileManager tileManager = new TileManager(this);
    public ScreenManager screenManager = new ScreenManager(this);
    public Player giocatore = new Player(this, keyh, screenManager);
    public CollisionManager CollisionManager = new CollisionManager(this);
    public GameEntity obj[] = new GameEntity[20];
    public GameEntity mon[] = new GameEntity[20];
    public GameEntity progressObj[]=new GameEntity[20];
    public GameEntity progressMon[]=new GameEntity[20];
    public GameEntity e = new GameEntity(this);
    public AssetPlacer assetPlacer = new AssetPlacer(this);
    public EventHandler eventHandler = new EventHandler(this);
    public ArrayList<GameEntity> entityList = new ArrayList<>();

    public UserInterface ui = new UserInterface(this);

    public Sound music = new Sound();
    Sound sfx = new Sound();

    public int gameState;
    public int titleState = 0;
    public int playState = 1;
    public int pauseState = 2;
    public int dialogueState = 3;
    public int optionsState = 4;
    public int gameOver = 5;
    public int endGame = 6;

    public int lastKill = 1;

    public long lastTime = System.nanoTime();
    public long currentTime;

    // FPS
    public double FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(new Color(173, 255, 47));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyh);
        this.setFocusable(true);

        gameTimer = new Timer(1000/(int)FPS, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
                drawToSizedScreen();
                screenManager.drawScreen();
            }
        });
    }

    public void setUpGioco() {
        gameState = titleState;
        assetPlacer.placeObject();
        assetPlacer.placeEnemy();

        playMusic(4);

        gameScreen = new BufferedImage(screen_width, screen_height, BufferedImage.TYPE_INT_ARGB_PRE);
        graphics2 = (Graphics2D) gameScreen.getGraphics();
        graphics3 = (Graphics2D) gameScreen.getGraphics();
    }

    public void startGameTimer() {
        gameTimer.start();
    }

    public void update() {
        if (gameState == playState) {
            giocatore.update();
            
            for (int i = 0; i < mon.length; i++) {
                if (mon[i] != null && mon[i].mapVerifier == eventHandler.currentMapIndex) {
                    mon[i].update();
                    if (mon[i].dead == true) {
                        mon[i] = null;
                    }
                }
            }
        }

        if (gameState == pauseState) {
            // Gestione stato di pausa
        }
    }

    public void drawToSizedScreen() {
        // Debug
        long drawStart = 0;
        if (keyh.z == true) {
            drawStart = System.nanoTime();
        }

        // SCHERMATA INIZIALE
        if (gameState == titleState) {
            ui.draw(graphics2);
        } else {
            // MAPPA
            mapMemory.loadToMapMemory(start);
            mapMemory.loadToMapMemory(dungeon1);
            mapMemory.loadToMapMemory(dungeon2);
            mapMemory.loadToMapMemory(dungeon3);
            Map currentMap = mapMemory.mapHandler[eventHandler.currentMapIndex];
            currentMap.draw(graphics3, graphics2, tileManager);
          
            
            if (currentMap.isComplete() && lastKill != 0) {
                assetPlacer.setObject("Key", 26, 26, 1, 0);
                lastKill--;
            }

            // AGGIUNGIAMO LE ENTITà ALLA LISTA
            for (int i = 0; i < obj.length; i++) {
                if (mon[i] != null) {
                    entityList.add(mon[i]);
                }
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            entityList.add(giocatore);

            // DISEGNIAMO LE ENTITà
            for (int i = 0; i < entityList.size(); i++) {
                if (entityList.get(i).mapVerifier == eventHandler.currentMapIndex) {
                    entityList.get(i).draw(graphics2);
                }
            }

            // SVUOTIAMO LA LISTA
            entityList.clear();

            // INTERFACCIA
            ui.draw(graphics2);

            // Debug
            if (keyh.z == true) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                graphics2.setColor(Color.WHITE);
                graphics2.drawString("drawTime: " + passed, 10, 400);
                System.out.println("drawTime: " + passed);
            }
        }
    }

    public void retry() {
        giocatore.setValoriPredefiniti();
        assetPlacer.restartPlaceObject();
        assetPlacer.restartPlaceEnemies();
        eventHandler.currentMapIndex = eventHandler.startingWoodsMap;
        eventHandler.eventRect[25][25].happened = false ;
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(int i) {
        music.stop();
    }

    public void playSFX(int i) {
        sfx.setFile(i);
        sfx.play();
    }
    
}
