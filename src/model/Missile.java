package model;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Missile extends GameFigureState {

    
   
    private float mx; 
    private float my; 
    
    
    private static final int SIZE = 5;
    private static final int BIG_EXPLOSION = 50;
    public Color color;
    public Point2D.Float target;

    private static final int TRAVEL_DISTANCE = 4; 

    private int size = SIZE;

    
    public Missile(float sx, float sy, float tx, float ty, Color color) {
        super(sx, sy);
        super.state = GameFigureState.STATE_SAFE;
        selfState = new SDPSafe();
        this.target = new Point2D.Float(tx, ty);
        this.color = color;
        double angle = Math.atan2(Math.abs(ty - sy), Math.abs(tx - sx));
        mx = (float) (TRAVEL_DISTANCE * Math.cos(angle));
        my = (float) (TRAVEL_DISTANCE * Math.sin(angle));
        
        if (tx >= sx && ty <= sy) { 
            my = -my; 
        } else if (tx <= sx && ty <= sy) { 
            mx = -mx;
            my = -my;
        } else if (tx <= sx && ty >= sy) { 
            mx = -mx;
        } else { 
          
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.drawOval((int) (super.x - size / 2),
                (int) (super.y - size / 2),
                size, size);
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
    public void updateSize() {
        size += 2;
    }
    public void updateLocation() {
        
        super.x += mx;
        super.y += my;
    }

    

    public void updateState() {
        if (state == GameFigureState.STATE_SAFE || selfState == new SDPSafe()) {
            double distance = target.distance(super.x, super.y);
            boolean targetReached = distance <= 2.0;
            if (targetReached) {
                state = GameFigureState.STATE_DANGER;
                goNextState();
            }
        } else if (state == GameFigureState.STATE_DANGER || selfState == new SDPDanger()) {
            if (size >= BIG_EXPLOSION) {
                state = GameFigureState.STATE_DONE;
                goNextState();
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
