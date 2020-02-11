package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Bullet extends GameFigureState {

    
    private static final int SIZE = 144;
    private static final int MAX_EXPLOSION_SIZE = 100;
    private float dx; 
    private float dy;

    Image bulletImage;
    
    public Color color;
    public Point2D.Float target;

    private static final int UNIT_TRAVEL_DISTANCE = 15; 

    private int size = SIZE;

    public Bullet(float sx, float sy, float tx, float ty, Color color) {
        super(sx, sy);
        super.state = GameFigureState.STATE_SAFE;
        selfState = new SDPSafe();
        this.target = new Point2D.Float(tx, ty);
        this.color = color;
        double angle = Math.atan2(Math.abs(ty - sy), Math.abs(tx - sx));
        dx = (float) (UNIT_TRAVEL_DISTANCE * Math.cos(angle) );
        dy = (float) (UNIT_TRAVEL_DISTANCE * Math.sin(angle) );
        
        if (tx >= sx && ty <= sy) { 
            dy = -dy;
        } else if (tx <= sx && ty <= sy) {
            dx = -dx;
            dy = -dy;
        } else if (tx <= sx && ty >= sy) {
            dx = -dx;
        } else {
        }
        bulletImage = null;
        
        try {
            bulletImage = ImageIO.read(getClass().getResource("bullet.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open bullet.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.drawImage(bulletImage,(int)(super.x - size / 2), 
                (int)(super.y - size / 2), size*2,size*4,null);
    }

    @Override
    public void update() {
        updateState();
        if (state == GameFigureState.STATE_SAFE || selfState == new SDPSafe()) {
            updateLocation();
        } else if (state == GameFigureState.STATE_DANGER || selfState == new SDPDanger()) {
            updateSize();
        }
    }

    public void updateLocation() {
      
        super.y += dy;
    }

    public void updateSize() {
        size -= 1;
    }

    public void updateState() {
        if (state == GameFigureState.STATE_SAFE || selfState == new SDPSafe()) {
            double distance = target.distance(super.x, super.y);
            boolean targetReached = distance <= 2.0;
            if (targetReached) {
                state = GameFigureState.STATE_DANGER;
                selfState = new SDPDanger();
            }
        } else if (state == GameFigureState.STATE_DANGER || selfState == new SDPDanger()) {
            if (size >= MAX_EXPLOSION_SIZE) {
                state = GameFigureState.STATE_DONE;
                selfState = new SDPDone();
            }
        }
    }
    
    @Override
    public void goNextState() {
        selfState.goNext(this);
    }

    
    @Override
    public Rectangle2D getCollisionBox()
    {
        return new Rectangle2D.Float(x - size / 2, y - size / 2, size, size);
    }

}
