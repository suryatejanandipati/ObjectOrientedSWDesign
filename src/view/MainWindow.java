package view;

import controller.ButtonListener;
import controller.KeyController;
import controller.Main;
import controller.MouseController;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

    public static JButton startButton;
    public static JButton quitButton;
    public static JPanel southPanel;
    
    public MainWindow() {

        Container c = getContentPane();       

        c.add(Main.gamePanel, "Center");
       
        southPanel = new JPanel();
        
        startButton = new JButton("Start");
        southPanel.add(startButton);
        
        quitButton = new JButton("Quit");
        southPanel.add(quitButton);
        c.add(southPanel, "South");

        ButtonListener buttonListener = new ButtonListener();
        startButton.addActionListener(buttonListener);
        quitButton.addActionListener(buttonListener);

        MouseController mouseController = new MouseController();
        Main.gamePanel.addMouseListener(mouseController);

        KeyController keyListener = new KeyController();
        Main.gamePanel.addKeyListener(keyListener);
        Main.gamePanel.setFocusable(true);
        
        startButton.setFocusable(false);
        quitButton.setFocusable(false);
    }

}
