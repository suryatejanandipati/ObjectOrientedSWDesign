package controller;

import javax.swing.JFrame;
import model.GameData;
import view.GamePanel;
import view.MainWindow;

public class Main {

    public static GamePanel gamePanel;
    public static GameData gameData;
    public static Animation animator;
    public static int WIDTH = 700;
    public static int HEIGHT = 700;

    public static void main(String[] args) {
        gameData = new GameData();
        
        gamePanel = new GamePanel();

        animator = new Animation();
       
        JFrame game = new MainWindow();
        game.setLocation(100, 0);
        game.setResizable(false); 
        game.setSize(WIDTH, HEIGHT);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        game.setTitle("SuryaTejaN_termproject");

    }
}
