// File: core/Main.java
package core;

import javax.swing.*;
import player.Player;

public class Main extends JFrame {
    public Main() {
        // JFrame 창 생성
        setTitle("캐릭터 움직임 구현");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
       

        Player player = new Player();
        add(player);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
