package controller;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import model.GameData;
import model.GameFigureState;

public class Animation implements Runnable {

    public boolean runTime = true;
    private final int FRAMES_PER_SECOND = 50;
    
    @Override
    public void run() {

        while (runTime) {
            long startTime = System.currentTimeMillis();

            processCollisions();
            Main.gameData.update();
            Main.gamePanel.gameRender();
            Main.gamePanel.print();

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

    private void processCollisions() {
       
        GameData.enemyFigures.stream().forEach((GameFigureState enemyFigure) -> {
            GameData.newFigure.stream().filter((newFigure) -> (enemyFigure.state == GameFigureState.STATE_SAFE)).filter((newFigure) -> (enemyFigure.getCollisionBox().intersects(newFigure.getCollisionBox()))).map((_item) -> {
                GameFigureState.health--;
                GameFigureState.enemiesKilled++;
                return _item;
            }).forEach((_item) -> {
                enemyFigure.state = GameFigureState.STATE_DANGER;
            });
        });
        
        for (GameFigureState enemyFigure : GameData.enemyFigures) {
            GameData.friendFigures.stream().filter(new Predicat(enemyFigure)).filter((friendFigure) -> (enemyFigure.getCollisionBox().intersects(friendFigure.getCollisionBox()))).map((_item) -> {
                enemyFigure.state = GameFigureState.STATE_DANGER;
                return _item;
            }).forEach((_item) -> {
                GameFigureState.enemiesKilled++;
            });
        }

        
    }

    private static class Predicat implements Predicate<GameFigureState> {

        private final GameFigureState enemyFigure;

        public Predicat(GameFigureState enemyFigure) {
            this.enemyFigure = enemyFigure;
        }

        public boolean test(GameFigureState friendFigure) {
            return enemyFigure.state == GameFigureState.STATE_SAFE;
        }
    }

}
