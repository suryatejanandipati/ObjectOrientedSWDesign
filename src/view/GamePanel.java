package view;

import controller.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import model.GameData;
import model.GameFigureState;

public class GamePanel extends JPanel {

    private Graphics2D graphics;
    private Image dbImage = null;
    public static int width;
    public static int height;

    public void gameRender() {
        width = getSize().width;
        height = getSize().height;
        if (dbImage == null) {
       
            dbImage = createImage(width, height);
            if (dbImage == null) {
                System.out.println("Critical Error: dbImage is null");
                System.exit(1);
            } else {
                graphics = (Graphics2D) dbImage.getGraphics();
            }
        }
        graphics.clearRect(0, 0, width, height);
        graphics.setBackground(Color.BLACK);

        if (Main.animator.runTime) {
            GameData.newFigure.stream().forEach((f) -> {
                f.render(graphics);
            });

            GameData.enemyFigures.stream().forEach((f) -> {
                f.render(graphics);
            });

            GameData.friendFigures.stream().forEach((f) -> {
                f.render(graphics);
            });
           
        }
        if(GameFigureState.health <= 0){
            GameData.level = 0;
            GameData.enemyFigures.clear();
            GameData.friendFigures.clear();
            GameData.newFigure.clear();
            graphics.setFont(new Font("BERLIN SANS FB", Font.BOLD,40));
            graphics.setColor(Color.RED);
            graphics.drawString("YOU LOST!", 200,420);
            graphics.drawString("Game Over", 435,450);
            MainWindow.startButton.setEnabled(true); 
        }
        if(GameFigureState.enemiesKilled >= 10 && GameData.level != 0){
            GameData.level = 2;
        }
       
        
           if(GameFigureState.enemiesKilled == 40){
            GameData.level = 0;
            GameData.enemyFigures.clear();
            GameData.friendFigures.clear();
            GameData.newFigure.clear();
            graphics.setFont(new Font("BERLIN SANS FB", Font.BOLD,20));
            graphics.setColor(Color.RED);
            graphics.drawString("You have Won!", 200,420);
            graphics.drawString("Game Over", 435,450);
            MainWindow.startButton.setEnabled(true); 
        }
        
        
        graphics.setFont(new Font("BERLIN SANS FB", Font.BOLD,24));
        graphics.setColor(Color.RED);
        graphics.drawString("Killed: ", 20, 35);
        graphics.drawString(Integer.toString(GameFigureState.enemiesKilled),100, 35);
        
        graphics.setColor(Color.RED);
        graphics.drawString("Score : ",300, 29);
        graphics.drawString(Integer.toString(GameFigureState.enemiesKilled * 100),390, 30);
        
        graphics.setColor(Color.RED);
        graphics.drawString("Health Level:", 480, 35);
        graphics.drawString(Integer.toString(GameFigureState.health),650, 35);
        
        graphics.setColor(Color.RED);
        graphics.drawString("Game Level:", 480, 60);
        graphics.drawString(Integer.toString(GameData.level),660, 60);
            
            
    }
   
    public void print() {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync(); 
            if (g != null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }
}
