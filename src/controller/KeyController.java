package controller;

import java.awt.Color;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_0;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_SPACE;
import java.awt.event.KeyListener;
import model.Bullet;
import model.GameData;
import model.Shooter;
import model.laser;

public class KeyController implements KeyListener {
    private int x;
    private int y;
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        
        Object pressedKey = e.getKeyCode();
        
        Shooter spaceShip = (Shooter) (GameData.newFigure.get(0));

        
            if(pressedKey.equals(VK_UP)){
                if(spaceShip.y > 100){                
                spaceShip.translate(0,-40);
                }
                
            }
            else if(pressedKey.equals(VK_DOWN)){
                if(spaceShip.y < 770){
                    spaceShip.translate(0,40);
                }
                 
            }
            else if(pressedKey.equals(VK_LEFT)){
                if(spaceShip.x  > 60){
                    spaceShip.translate(-40,0);
                }
            }
            else if(pressedKey.equals(VK_RIGHT)){
                if(spaceShip.x < 850){
                    spaceShip.translate(40,0);
                }
            }
            else if(pressedKey.equals(VK_SPACE)){
               Bullet b = new Bullet(
                spaceShip.getXofMissileShoot(),
                spaceShip.getYofMissileShoot(),
                 
                x,-y,
                Color.RED);
               

               GameData.friendFigures.add(b);
            }
            else if(pressedKey.equals(VK_0)){
               laser b = new laser(
                spaceShip.getXofMissileShoot(),
                spaceShip.getYofMissileShoot(),
                x,-y,
                Color.RED);
               

               GameData.friendFigures.add(b);
            }
    }

    @Override
    public void keyTyped(KeyEvent e) {
         
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
