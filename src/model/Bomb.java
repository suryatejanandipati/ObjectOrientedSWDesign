package model;

import view.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Bomb extends GameFigure {

    private final int radius;
    private final Color color;
    private int dx = 3;
    private int dy = 3;
    

    public Bomb(float x, float y, int radius, Color color) {
        super(x, y);
        super.state = GameFigureState.BOMB_STATE_ADDED;
        this.radius = radius;
        this.color = color;
        
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        // Note: use drawOval() to draw outline only
        if(state == 20){
        g.fillOval((int)(x - radius), (int)(y - radius), 
                radius * 2, radius * 2);}
       else if(state == 9){
            g.fillOval((int)(x - radius - 7.0F), (int)(y - radius - 5.0F), radius * 4, radius * 4);
            g.fillOval((int)(x - radius + 5.0F), (int)(y - radius + 7.0F), radius * 4, radius * 4);
            g.fillOval((int)(x - radius + 7.0F), (int)(y - radius - 10.0F), radius * 4, radius * 4);
            state = 0;
        }
    }
    @Override
    public void update() {
        // ball bounces on the wall
      // if(state)
        super.x += dx;
        super.y += dy;

        if (super.x + radius > GamePanel.width) {
            dx = -dx;
            super.x = GamePanel.width - radius;
        } else if (super.x - radius < 0) {
            dx = -dx;
            super.x = radius;
        }

        if (super.y + radius > GamePanel.height) {
            dy = -dy;
            super.y = GamePanel.height - radius;
        } else if (super.y - radius < 0) {
            dy = -dy;
            super.y = radius;
        }
    }
    
    public void updateState() 
    {
        
    }
    
    
    @Override
    public Rectangle2D getCollisionBox()
    {
        return new Rectangle2D.Float(x-radius,y-radius,radius *2, radius *2);
    }
    
}
