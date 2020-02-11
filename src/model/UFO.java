
package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import view.GamePanel;

public class UFO extends GameFigureState {
    
    private final int WIDTH = 40;
    private final int HEIGHT = 10;
    private final Color color = Color.yellow;
    private final int UNIT_TRAVEL = 5;
     private Image ufoImage;
    private int direction = 1; 
    private final int bombExplodeSize;
    private boolean bombSizeDecrease = false;
    private int radius = 6;
    
    public UFO(float x, float y) {
        super(x, y);
        super.state = GameFigureState.STATE_SAFE;
        selfState = new SDPSafe();
        bombExplodeSize = radius * 3;
        ufoImage = null;
        
        try {
            ufoImage = ImageIO.read(getClass().getResource("ufo.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open ufo.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        if(state == GameFigureState.STATE_SAFE || selfState == new SDPSafe()){
            g.setColor(color);
            g.drawImage(ufoImage,(int)super.x, 
                    (int)super.y, WIDTH*2,HEIGHT*4,null);
        
        } else if(state  == GameFigureState.STATE_DANGER || selfState == new SDPDanger()){
            g.setColor(Color.BLUE);
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
        if(state == GameFigureState.STATE_SAFE || selfState == new SDPSafe()){
            if (direction > 0) {

            super.x += UNIT_TRAVEL;
            if (super.x + WIDTH*2 > GamePanel.width) {
                direction = -1;
            }
        } else {

            super.x -= UNIT_TRAVEL;
            if (super.x <= 0) {
                direction = 1;
                }
            }
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
    }
    
    @Override
    public void goNextState() {
        selfState.goNext(this);
    }
    
    
    @Override
    public Rectangle2D getCollisionBox(){
        return new Rectangle2D.Float(x - (float)WIDTH/2, y - (float)HEIGHT/4, (float)WIDTH, (float)HEIGHT);
    }
}
 
