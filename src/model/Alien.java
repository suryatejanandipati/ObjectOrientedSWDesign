package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import view.GamePanel;

public class Alien extends GameFigureState {
    
    private final int WIDTH = 150;
    private final int HEIGHT = 150;
    private final Color color = Color.yellow;
    private final int UNITTRAVEL = 12;
     private Image alienImage;
     private Image expImage;
    private int direction = 1;
    private boolean once = true;
    private double exp = 0.0;
    private int[] travelDirection = {-1, 0, 1};
    
    public Alien(float x, float y) {
        super(x, y);
        super.state = GameFigureState.STATE_SAFE;
        
        alienImage = null;
        expImage = null;
        
        try {
            alienImage = ImageIO.read(getClass().getResource("alien.png"));
            expImage = ImageIO.read(getClass().getResource("exp.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open the alien.png");
            
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        
        g.drawImage(alienImage,(int)super.x, 
                (int)super.y, WIDTH,HEIGHT,null);
        
        if(state == GameFigureState.STATE_DANGER){
            alienImage = null;
            
            g.drawImage(expImage,(int)super.x,(int)super.y,(int)(WIDTH*exp),(int)(HEIGHT*exp),null);
               
        }
        
    }

    @Override
    public void update() {
        if(state == GameFigureState.STATE_SAFE){
            if (direction > 0) {
            
            super.x += UNITTRAVEL;
            if (super.x + WIDTH - 25> GamePanel.width) {
                super.y -= 100;
                direction = -1;
                
            }
        } else {
            
            super.x -= UNITTRAVEL;
            if (super.x <= 0) {
                direction = 1;
                super.y += 100;
                }
            }
        }
        else if(state == GameFigureState.STATE_DANGER){
            
            if(exp < 1.0) 
                exp += 0.15;      
            else           
                state = GameFigureState.STATE_DONE;
        }
    }
    
    
    @Override
    public void goNextState() {
        selfState.goNext(this);
    }
    
     public float getXofMissileShoot() {
        return super.x/2;
    }
    
    public float getYofMissileShoot() {
        return super.y/2;
    }

    
    @Override
    public Rectangle2D getCollisionBox(){
        return new Rectangle2D.Float(x , y, (float)WIDTH - 80, (float)HEIGHT-25);
    }
}
 
