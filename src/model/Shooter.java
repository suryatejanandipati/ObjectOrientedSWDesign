package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Shooter extends GameFigureState {

    private Image launcherImage;

    public Shooter(int x, int y) {
        super(x, y);
        super.state = GameFigureState.STATE_SAFE;
        launcherImage = null;
        
        try {
            launcherImage = ImageIO.read(getClass().getResource("launcher.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open launcher.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(launcherImage, (int)super.x -90 , (int)super.y -100 , 
                160, 160, null);
        
    }

    @Override
    public void update() {
   
    }

    public void translate(int dx, int dy) {
       if(x<50){
         super.x=0;
       dx=50;
       }
       if(x>650){
           super.x=0;
           dx=550;
       }
       
      if(y<80){
         super.y=0;
       dy=80; 
      }
      if(y>620){
         super.y=0;
       dy=620; 
      }
       
        super.x += dx;
        super.y += dy;
    }
    
    public float getXofMissileShoot() {
        return super.x -90 + 78;
    }
    
    public float getYofMissileShoot() {
        return super.y-100;
    }

    
    @Override
     public java.awt.geom.Rectangle2D getCollisionBox()
  {
    return new Rectangle2D.Float(x - 90, y - 100, 150.0F, 150.0F);
  }
}
