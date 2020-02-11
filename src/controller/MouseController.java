package controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.GameData;
import model.Missile;
import model.Shooter;

public class MouseController extends MouseAdapter {

    private int px;
    private int py;

    @Override
    public void mousePressed(MouseEvent me) {
        px = me.getX();
        py = me.getY();

        Shooter shooter = (Shooter) GameData.newFigure.get(0);     

        Missile m = new Missile(
                shooter.getXofMissileShoot(),
                shooter.getYofMissileShoot(),
                px, py,
                Color.RED);

        GameData.friendFigures.add(m);

    }

}
