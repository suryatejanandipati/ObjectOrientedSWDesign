package controller;

import java.util.concurrent.TimeUnit;
import static model.GameData.shooter;
import model.GameFigure;
import model.GameFigureState;
import model.Missile;

public class Animator implements Runnable {

    public boolean running = true;
    private final int FRAMES_PER_SECOND = 40;

    @Override
    public void run() {

        while (running) {
            long startTime = System.currentTimeMillis();
            
            pCollisions();

            Main.gameData.update();
            Main.gamePanel.gameRender();
            Main.gamePanel.printScreen();

            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {

                }
            }
        }
        System.exit(0);
    }    
        
    private void pCollisions() {
            for (GameFigure e : Main.gameData.enemyFigures)
        {
            for(GameFigure f : Main.gameData.friendFigures)
            {
                if((e.getCollisionBox().intersects(f.getCollisionBox())))
                {
                    e.state =GameFigureState.STATE_HIT;  
                    f.state=GameFigureState.MISSILE_STATE_EXPLODED;                      
            }
        }
            
    }

    }
}
