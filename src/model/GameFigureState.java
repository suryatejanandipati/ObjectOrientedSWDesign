package model;

import java.awt.Graphics2D;

public abstract class GameFigureState implements CollisionBox {

    public float x;
    public float y;
    
    public AllState selfState;
    
    public int state;
    
    public static int enemiesKilled;
    public static int health = 20;
    

    public static final int STATE_DONE = 0;
    public static final int STATE_SAFE = 1;
    public static final int STATE_DANGER = 2;
    
    public void setState(AllState state) {
        this.selfState = state;
    }
    
    public void goNextState() {
        selfState.goNext((GameFigureState) selfState);
    }

    public GameFigureState(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
  
  public abstract void render(Graphics2D paramGraphics2D);
  
  public abstract void update();

}
