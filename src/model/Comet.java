package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Comet extends GameFigureState {
    private final int WIDTH = 50;
    private final int HEIGHT = 40;
    private int radius;
    private final Color color;
    private int dx = 3;
    private int dy = 3;
    private final int bombExplodeSize;
    private boolean bombSizeDecrease = false;
    Image meteoriteImage;
    

    public Comet(float x, float y, int radius, Color color) {
        super(x, y);
        super.state = GameFigureState.STATE_SAFE;
        
        selfState = new SDPSafe();
        this.radius = radius;
        this.color = color;
        bombExplodeSize = (radius * 3);
        
        
        meteoriteImage = null;
        
        try {
            meteoriteImage = ImageIO.read(getClass().getResource("Comet.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open meteroid.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
     
        
        if(state == GameFigureState.STATE_SAFE || selfState == new SDPSafe()){
            g.drawImage(meteoriteImage,(int)super.x, 
                (int)super.y, WIDTH,HEIGHT,null);

        }
        else if(state  == GameFigureState.STATE_DANGER || selfState == new SDPDanger()){
            
             g.fillOval((int)(x - radius), (int)(y - radius), 
                radius * 2, radius * 2);
            
            g.fillOval((int)(x - radius + 15), (int)(y - radius), 
                radius * 2, radius * 2);
            
             g.fillOval((int)(x - radius), (int)(y - radius + 15), 
                radius * 2, radius * 2);
            
        }
    }

    @Override
    public void update() {
        
        
        if( state == GameFigureState.STATE_SAFE || selfState == new SDPSafe() ){
            super.x += dx;
            super.y += dy;
        }
        else if(state == GameFigureState.STATE_DANGER || selfState == new SDPDanger()){
            if((radius < bombExplodeSize) && (!bombSizeDecrease)){
                radius += 2;
            }
            else{
                bombSizeDecrease = true;
                if(radius > 0){
                    radius -= 1;
                }
                else{
                    state = GameFigureState.STATE_DONE;
                   goNextState();
                }
            }
        }
        
        dx = -dx;
        
       
    }
    @Override
    public void goNextState() {
        selfState.goNext(this);
    }
    
   
    @Override
    public java.awt.geom.Rectangle2D getCollisionBox()
  {
     return new Rectangle2D.Float(x - (float)WIDTH/2, y - (float)HEIGHT/2, (float)WIDTH, (float)HEIGHT);
  }
    
}
