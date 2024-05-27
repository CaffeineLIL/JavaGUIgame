package core;

import status_basis.UI;
import javax.swing.*;
import java.awt.GridLayout;

public class Test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Image Loading Test");
             
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);

                UI ui = new UI();
                
                frame.add(ui);
                frame.setVisible(true);
            }
        });
    }
}
	