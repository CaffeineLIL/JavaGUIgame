package core;

import status_basis.UI;
import javax.swing.*;
import java.awt.GridLayout;

public class Test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Image Loading Test");
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(2, 2, 2, 2));
                
                frame.add(panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);

                UI ui = new UI();
                
                panel.add(ui);
                frame.setVisible(true);
            }
        });
    }
}
