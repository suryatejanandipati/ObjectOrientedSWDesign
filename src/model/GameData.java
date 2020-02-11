package model;

import controller.Main;
import view.GamePanel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;

public class GameData {

    private final int RADIUS = 6;
    public static List<GameFigureState> enemyFigures;
    public static List<GameFigureState> friendFigures;
    public static List<GameFigureState> newFigure;
    public static Shooter spaceShip;
    public static int level = 1;

    public GameData() {
        enemyFigures = new CopyOnWriteArrayList<>();
        friendFigures = new CopyOnWriteArrayList<>();
        newFigure = new CopyOnWriteArrayList<>();

        spaceShip = new Shooter(Main.WIDTH / 2, Main.HEIGHT - 130);

        newFigure.add(spaceShip);

    }
    public void add(int n) {
        for (int i = 0; i < n; i++) {
            float red = (float) Math.random();
            float green = (float) Math.random();
            float blue = (float) Math.random();
            if (red < 0.5) {
                red += 0.2;
            }
            if (green < 0.5) {
                green += 0.2;
            }
            if (blue < 0.5) {
                blue += 0.2;
            }
            
            Random generator = new Random();
            int bomb_n = generator.nextInt(50);
            int wd = generator.nextInt(Main.WIDTH);
            int h = generator.nextInt(Main.HEIGHT - 300);
            enemyFigures.add(new Comet(
                    wd,
                    h,
                    RADIUS,
                    null));
        }
        
    }

    
    public void addUFO(){
        Random generator = new Random();
        int ufo_n = generator.nextInt(8);
        for(int i = 0;i < ufo_n+1; i++){
            int wd = generator.nextInt(Main.WIDTH);
            int h = generator.nextInt(Main.HEIGHT - 300);
            enemyFigures.add(new UFO(wd,h));
        }
        
        
    }
    public void addAlien(){
       Random generator = new Random();
       int alien_n = generator.nextInt(3);
       
       for(int i=0;i<alien_n+1;i++)
        enemyFigures.add(new Alien(generator.nextInt(Main.WIDTH),generator.nextInt(Main.HEIGHT - 300)));
    }

  

    public void update() {
        
        if(enemyFigures.isEmpty() && level != 0) {
            
            if(level == 1) 
                Main.gameData.addUFO();
            else 
                Main.gameData.addAlien();
            for(int i = 0;i < 5; i++){
            enemyFigures.add(new UFO((int)(Math.random()*GamePanel.width),(int)(Math.random()*(GamePanel.height*0.77))));
            }
            for(int i = 0;i < 2; i++){
          enemyFigures.add(new Alien((int)(Math.random()*GamePanel.width),(int)(Math.random()*(GamePanel.height*0.77))));
        }
            Main.gameData.add(10);
           
        }
        
        newFigure.stream().forEach((g) -> {
            g.update();
        });

        ArrayList<GameFigureState> removeEnemies = new ArrayList<>();
        GameFigureState f;
        for (GameFigureState enemyFigure : enemyFigures) {
            f = enemyFigure;
            if (f.state == GameFigureState.STATE_DONE) {
                removeEnemies.add(f);
            }
            
            if(f.y > Main.HEIGHT) {
                removeEnemies.add(f);
            }
        }
        
        enemyFigures.removeAll(removeEnemies);

        enemyFigures.stream().forEach((g) -> {
            g.update();
        });

        ArrayList<GameFigureState> removeFriends = new ArrayList<>();
        for (GameFigureState friendFigure : friendFigures) {
            f = friendFigure;
            if (f.state == GameFigureState.STATE_DONE) {
                removeFriends.add(f);
            }
        }
        friendFigures.removeAll(removeFriends);

        friendFigures.stream().forEach((g) -> {
            g.update();
        });
        
    }
}
