package model;

import java.awt.Graphics2D;

public abstract class GameFigure implements CollisionBox {

    
    
    public float x;
    public float y;
    
    
    public int state;


    public GameFigure(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // how to render on the canvas
    public abstract void render(Graphics2D g);

    // changes per frame
    public abstract void update();

    
    
}
