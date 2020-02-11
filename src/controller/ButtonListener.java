
package controller;

import static controller.Main.animator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MainWindow;
import static view.MainWindow.quitButton;
import static view.MainWindow.southPanel;
import static view.MainWindow.startButton;

public class ButtonListener implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == MainWindow.startButton) {
            
            new Thread(animator).start();
            startButton.setBackground(Color.WHITE);
            quitButton.setBackground(Color.WHITE);
            southPanel.setBackground(Color.BLACK);
           
            
            
            MainWindow.startButton.setEnabled(false);     
          
        }
        else if (ae.getSource() == MainWindow.quitButton) {
            if (Main.animator.runTime) {
                Main.animator.runTime = false;
            } else {
                System.exit(0);
            }
        }
    }

}
